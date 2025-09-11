package com.choosenfly.hotelbookingsystem.api.hotelroom.mapper;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxHotelSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxGroupedRoomResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.*;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.util.CancellationPolicyTextGenerator;
import com.choosenfly.hotelbookingsystem.api.hotelroom.util.CurrencyConverter;
import com.choosenfly.hotelbookingsystem.api.x3.repository.X3HotelsRepository;
import com.choosenfly.hotelbookingsystem.api.x3.entities.X3Hotels;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper to convert X3 XML response to standardized JSON response format
 */
@Component
public class X3ResponseMapper {

    private static final Logger logger = LoggerFactory.getLogger(X3ResponseMapper.class);
    private final X3HotelsRepository x3HotelsRepository;

    public X3ResponseMapper(X3HotelsRepository x3HotelsRepository) {
        this.x3HotelsRepository = x3HotelsRepository;
    }

    /**
     * Map X3 hotel search response to list of hotel responses
     * 
     * @param x3Response X3 hotel search response
     * @param originalRequest Original search request for booking details
     * @return List of hotel responses in standardized format
     */
    public List<HotelResponse> mapToHotelResponses(IwtxHotelSearchResponse x3Response, HotelRoomSearchRequest originalRequest) {
        logger.debug("Mapping X3 response to hotel responses");

        if (x3Response == null || x3Response.getHotels() == null || 
            x3Response.getHotels().getHotelList() == null) {
            logger.warn("X3 response is null or contains no hotels");
            return new ArrayList<>();
        }

        List<HotelResponse> hotelResponses = new ArrayList<>();

        for (IwtxHotelSearchResponse.IwtxHotel x3Hotel : x3Response.getHotels().getHotelList()) {
            try {
                HotelResponse hotelResponse = mapToHotelResponse(x3Hotel, originalRequest);
                if (hotelResponse != null) {
                    hotelResponses.add(hotelResponse);
                }
            } catch (Exception e) {
                logger.error("Error mapping hotel: {}", x3Hotel.getHotelName(), e);
                // Continue processing other hotels
            }
        }

        logger.info("Successfully mapped {} hotels from X3 response", hotelResponses.size());
        return hotelResponses;
    }

    /**
     * Map single X3 hotel to hotel response
     * 
     * @param x3Hotel X3 hotel
     * @param originalRequest Original search request for booking details
     * @return Hotel response
     */
    private HotelResponse mapToHotelResponse(IwtxHotelSearchResponse.IwtxHotel x3Hotel, HotelRoomSearchRequest originalRequest) {
        if (x3Hotel == null) {
            return null;
        }

        HotelResponse hotelResponse = new HotelResponse();
        
        // Map basic hotel information
        hotelResponse.setHotelId(x3Hotel.getHotelId());
        hotelResponse.setHotelName(x3Hotel.getHotelName());
        hotelResponse.setStarRating(x3Hotel.getStarRating());
        hotelResponse.setPropertyType(x3Hotel.getPropertyType());
        hotelResponse.setChain(x3Hotel.getChain());
        hotelResponse.setCity(x3Hotel.getCity());
        hotelResponse.setTimeZone(x3Hotel.getTimeZone());

        // Map geo location
        if (x3Hotel.getGeoLocation() != null) {
            GeoLocation geoLocation = new GeoLocation(
                x3Hotel.getGeoLocation().getLongitude(),
                x3Hotel.getGeoLocation().getLatitude()
            );
            hotelResponse.setGeoLocation(geoLocation);
        }

        // Map booking details from original request
        if (originalRequest != null) {
            hotelResponse.setCheckInDate(originalRequest.getCheckInDate());
            hotelResponse.setCheckOutDate(originalRequest.getCheckOutDate());
            hotelResponse.setNationality(originalRequest.getNationality());
            hotelResponse.setNumberOfRooms(originalRequest.getRooms() != null ? originalRequest.getRooms().size() : 0);
            
            // Calculate total number of guests and generate breakdown text
            int totalGuests = 0;
            int totalAdults = 0;
            int totalChildren = 0;
            
            if (originalRequest.getRooms() != null) {
                for (var room : originalRequest.getRooms()) {
                    int adults = room.getAdults() != null ? room.getAdults() : 0;
                    int children = room.getChildren() != null ? room.getChildren() : 0;
                    totalAdults += adults;
                    totalChildren += children;
                    totalGuests += adults + children;
                }
            }
            
            hotelResponse.setNumberOfGuests(totalGuests);
            
            // Generate guest breakdown text
            StringBuilder guestBreakdown = new StringBuilder();
            if (totalAdults > 0) {
                guestBreakdown.append(totalAdults).append(totalAdults == 1 ? " Adult" : " Adults");
            }
            if (totalChildren > 0) {
                if (guestBreakdown.length() > 0) {
                    guestBreakdown.append(" and ");
                }
                guestBreakdown.append(totalChildren).append(totalChildren == 1 ? " Child" : " Children");
            }
            hotelResponse.setGuestBreakdown("(" + guestBreakdown.toString() + ")");
            
            hotelResponse.setDestination(x3Hotel.getCity()); // Use hotel city as destination
        }

        // Fetch hotel address and phone from X3Hotels database using hotel code
        try {
            X3Hotels hotelEntity = x3HotelsRepository.findByIwtxCode(x3Hotel.getHotelId());
            if (hotelEntity != null) {
                hotelResponse.setHotelAddress(hotelEntity.getAddress() != null ? 
                    hotelEntity.getAddress() : "Address not available");
                hotelResponse.setHotelPhoneNumber(hotelEntity.getPhone() != null ? 
                    hotelEntity.getPhone() : "Phone not available");
            } else {
                hotelResponse.setHotelAddress("Hotel not found in X3 database");
                hotelResponse.setHotelPhoneNumber("Phone not available");
            }
        } catch (Exception e) {
            logger.warn("Error fetching hotel details from X3 database for hotel code: {}", x3Hotel.getHotelId(), e);
            hotelResponse.setHotelAddress("Address not available");
            hotelResponse.setHotelPhoneNumber("Phone not available");
        }

        // Map room categories - use grouped rooms if available, otherwise fall back to individual rooms
        List<RoomCategoryResponse> roomCategories = mapToRoomCategoriesFromGrouped(x3Hotel);
        hotelResponse.setRoomCategories(roomCategories);

        return hotelResponse;
    }

    /**
     * Map X3 hotel rooms to room category responses using grouped rooms structure
     * 
     * @param x3Hotel X3 hotel containing room details
     * @return List of room category responses
     */
    private List<RoomCategoryResponse> mapToRoomCategoriesFromGrouped(IwtxHotelSearchResponse.IwtxHotel x3Hotel) {
        // First try to use grouped rooms if available
        if (x3Hotel.getGroupedRooms() != null && !x3Hotel.getGroupedRooms().isEmpty()) {
            return mapGroupedRoomsToRoomCategories(x3Hotel.getGroupedRooms());
        }
        
        // Fall back to individual room details if grouped rooms not available
        return mapIndividualRoomsToCategories(x3Hotel);
    }
    
    /**
     * Map grouped rooms to room category responses
     * 
     * @param groupedRooms List of grouped room responses
     * @return List of room category responses
     */
    private List<RoomCategoryResponse> mapGroupedRoomsToRoomCategories(List<IwtxGroupedRoomResponse> groupedRooms) {
        List<RoomCategoryResponse> roomCategories = new ArrayList<>();
        
        for (IwtxGroupedRoomResponse groupedRoom : groupedRooms) {
            RoomCategoryResponse roomCategory = new RoomCategoryResponse();
            
            // Map room category information
            roomCategory.setRoomCategory(groupedRoom.getRoomCategory());
            roomCategory.setRoomTypeCode(groupedRoom.getRoomTypeCode());
            roomCategory.setBaseRoomType(groupedRoom.getBaseRoomType());
            
            // Map available rates
            List<RateOptionResponse> rateOptions = new ArrayList<>();
            if (groupedRoom.getAvailableRates() != null) {
                for (IwtxGroupedRoomResponse.IwtxRateOption rateOption : groupedRoom.getAvailableRates()) {
                    RateOptionResponse rateResponse = new RateOptionResponse();
                    
                    // Map room category and rate information with currency conversion from USD to AED
                    rateResponse.setRoomCategory(groupedRoom.getRoomCategory());
                    rateResponse.setRoomTypeCode(groupedRoom.getRoomTypeCode());
                    rateResponse.setRoomTypeDescription(groupedRoom.getBaseRoomType());
                    rateResponse.setMealPlan(rateOption.getMealPlan());
                    rateResponse.setMealPlanCode(rateOption.getMealPlanCode());
                    rateResponse.setCurrency("AED"); // Convert currency to AED
                    rateResponse.setRate(CurrencyConverter.convertUsdToAed(rateOption.getRate()));
                    rateResponse.setTotalRate(CurrencyConverter.convertUsdToAed(rateOption.getTotalRate()));
                    rateResponse.setRateBeforeTax(CurrencyConverter.convertUsdToAed(rateOption.getRateBeforeTax()));
                    rateResponse.setRecommendedRetailPrice(CurrencyConverter.convertUsdToAed(rateOption.getRecommendedRetailPrice()));
                    rateResponse.setRoomStatus(rateOption.getRoomStatus());
                    rateResponse.setNonRefundable(rateOption.isNonRefundable());
                    rateResponse.setContractLabel(rateOption.getContractLabel());
                    rateResponse.setContractTokenId(rateOption.getContractTokenId());
                    
                    // Map cancellation policies with currency conversion
                    if (rateOption.getCancellationPolicies() != null) {
                        List<CancellationPolicy> policies = rateOption.getCancellationPolicies().stream()
                            .map(policy -> new CancellationPolicy(
                                formatDate(policy.getFromDate()),
                                formatDate(policy.getToDate()),
                                policy.getPercentOrAmount(),
                                convertCancellationValue(policy.getValue(), policy.getPercentOrAmount())
                            ))
                            .collect(Collectors.toList());
                        rateResponse.setCancellationPolicies(policies);
                        
                        // Set individual policy text for each cancellation policy
                        for (CancellationPolicy policy : policies) {
                            String policyText = CancellationPolicyTextGenerator.generateIndividualPolicyText(policy);
                            policy.setPolicyText(policyText);
                        }
                        
                        // Set refund status based on nonRefundable flag
                        rateResponse.setRefundStatus(rateOption.isNonRefundable() ? "NON REFUNDABLE" : "FLEXIBLE");
                    }
                    
                    rateOptions.add(rateResponse);
                }
            }
            
            roomCategory.setAvailableRates(rateOptions);
            roomCategories.add(roomCategory);
        }
        
        return roomCategories;
    }
    
    /**
     * Map individual rooms to room categories (fallback method)
     * 
     * @param x3Hotel X3 hotel containing room details
     * @return List of room category responses
     */
    private List<RoomCategoryResponse> mapIndividualRoomsToCategories(IwtxHotelSearchResponse.IwtxHotel x3Hotel) {
        // For fallback, create individual room categories from room details
        // This maintains backward compatibility
        List<RoomResponse> individualRooms = mapToRoomResponses(x3Hotel);
        List<RoomCategoryResponse> roomCategories = new ArrayList<>();
        
        // Group individual rooms by room type code
        for (RoomResponse room : individualRooms) {
            // Find existing category or create new one
            RoomCategoryResponse existingCategory = roomCategories.stream()
                .filter(cat -> cat.getRoomTypeCode().equals(room.getRoomTypeCode()))
                .findFirst()
                .orElse(null);
                
            if (existingCategory == null) {
                existingCategory = new RoomCategoryResponse();
                existingCategory.setRoomCategory(room.getRoomType());
                existingCategory.setRoomTypeCode(room.getRoomTypeCode());
                existingCategory.setBaseRoomType(room.getRoomType());
                existingCategory.setAvailableRates(new ArrayList<>());
                roomCategories.add(existingCategory);
            }
            
            // Convert room to rate option
            RateOptionResponse rateOption = new RateOptionResponse();
            rateOption.setRoomCategory(room.getRoomType());
            rateOption.setRoomTypeCode(room.getRoomTypeCode());
            rateOption.setRoomTypeDescription(room.getRoomType());
            rateOption.setMealPlan(room.getMealPlan());
            rateOption.setMealPlanCode(room.getMealPlanCode());
            rateOption.setCurrency("AED"); // Convert currency to AED
            rateOption.setRate(room.getRate() != null ? CurrencyConverter.convertUsdToAed(room.getRate()) : null);
            rateOption.setTotalRate(room.getTotalRate() != null ? CurrencyConverter.convertUsdToAed(room.getTotalRate()) : null);
            rateOption.setRateBeforeTax(room.getRateBeforeTax() != null ? CurrencyConverter.convertUsdToAed(room.getRateBeforeTax()) : null);
            rateOption.setRecommendedRetailPrice(room.getRecommendedRetailPrice() != null ? CurrencyConverter.convertUsdToAed(room.getRecommendedRetailPrice()) : null);
            rateOption.setRoomStatus(room.getRoomStatus());
            rateOption.setNonRefundable(room.isNonRefundable());
            rateOption.setContractLabel(room.getContractLabel());
            rateOption.setContractTokenId(room.getContractTokenId());
            // Convert cancellation policies to AED
            if (room.getCancellationPolicies() != null) {
                List<CancellationPolicy> convertedPolicies = room.getCancellationPolicies().stream()
                    .map(policy -> new CancellationPolicy(
                        policy.getFromDate(),
                        policy.getToDate(),
                        policy.getPercentOrAmount(),
                        convertCancellationValue(policy.getValue(), policy.getPercentOrAmount())
                    ))
                    .collect(Collectors.toList());
                rateOption.setCancellationPolicies(convertedPolicies);
                
                // Set individual policy text for each cancellation policy
                for (CancellationPolicy policy : convertedPolicies) {
                    String policyText = CancellationPolicyTextGenerator.generateIndividualPolicyText(policy);
                    policy.setPolicyText(policyText);
                }
                
                // Set refund status based on nonRefundable flag
                rateOption.setRefundStatus(room.isNonRefundable() ? "NON REFUNDABLE" : "FLEXIBLE");
            }
            
            existingCategory.getAvailableRates().add(rateOption);
        }
        
        return roomCategories;
    }

    /**
     * Map X3 hotel rooms to room responses (fallback method)
     * 
     * @param x3Hotel X3 hotel containing room details
     * @return List of room responses
     */
    private List<RoomResponse> mapToRoomResponses(IwtxHotelSearchResponse.IwtxHotel x3Hotel) {
        if (x3Hotel.getRoomTypeDetails() == null || 
            x3Hotel.getRoomTypeDetails().getRooms() == null ||
            x3Hotel.getRoomTypeDetails().getRooms().getRoomList() == null) {
            return new ArrayList<>();
        }

        return x3Hotel.getRoomTypeDetails().getRooms().getRoomList().stream()
            .map(this::mapToRoomResponse)
            .filter(room -> room != null)
            .collect(Collectors.toList());
    }

    /**
     * Map single X3 room to room response
     * 
     * @param x3Room X3 room detail
     * @return Room response
     */
    private RoomResponse mapToRoomResponse(IwtxHotelSearchResponse.IwtxRoomDetail x3Room) {
        if (x3Room == null) {
            return null;
        }

        RoomResponse roomResponse = new RoomResponse();
        
        // Map basic room information
        roomResponse.setRoomNo(x3Room.getRoomNo());
        roomResponse.setRoomType(x3Room.getRoomType());
        roomResponse.setRoomTypeCode(x3Room.getRoomTypeCode());
        roomResponse.setMealPlan(x3Room.getMealPlan());
        roomResponse.setMealPlanCode(x3Room.getMealPlanCode());
        roomResponse.setCurrency(x3Room.getCurrCode());
        roomResponse.setRoomStatus(x3Room.getRoomStatus());
        roomResponse.setContractLabel(x3Room.getContractLabel());
        roomResponse.setContractTokenId(x3Room.getContractTokenId());

        // Map pricing information
        roomResponse.setRate(x3Room.getRate());
        roomResponse.setTotalRate(x3Room.getTotalRate());
        roomResponse.setRateBeforeTax(x3Room.getRateBeforeTax());
        roomResponse.setRecommendedRetailPrice(x3Room.getRecommendedRetailPrice());

        // Map non-refundable flag
        roomResponse.setNonRefundable("Y".equalsIgnoreCase(x3Room.getNonRefundable()));

        // Map cancellation policies
        List<CancellationPolicy> cancellationPolicies = mapToCancellationPolicies(x3Room);
        roomResponse.setCancellationPolicies(cancellationPolicies);

        return roomResponse;
    }

    /**
     * Map X3 cancellation policy details to cancellation policies
     * 
     * @param x3Room X3 room detail containing cancellation policies
     * @return List of cancellation policies
     */
    private List<CancellationPolicy> mapToCancellationPolicies(IwtxHotelSearchResponse.IwtxRoomDetail x3Room) {
        if (x3Room.getCancellationPolicyDetails() == null ||
            x3Room.getCancellationPolicyDetails().getCancellations() == null) {
            return new ArrayList<>();
        }

        return x3Room.getCancellationPolicyDetails().getCancellations().stream()
            .map(this::mapToCancellationPolicy)
            .filter(policy -> policy != null)
            .collect(Collectors.toList());
    }

    /**
     * Map single X3 cancellation to cancellation policy
     * 
     * @param x3Cancellation X3 cancellation
     * @return Cancellation policy
     */
    private CancellationPolicy mapToCancellationPolicy(IwtxHotelSearchResponse.IwtxCancellation x3Cancellation) {
        if (x3Cancellation == null) {
            return null;
        }

        return new CancellationPolicy(
            formatDate(x3Cancellation.getFromDate()),
            formatDate(x3Cancellation.getToDate()),
            x3Cancellation.getPercentOrAmt(),
            convertCancellationValue(x3Cancellation.getValue(), x3Cancellation.getPercentOrAmt())
        );
    }

    /**
     * Convert cancellation policy value from USD to AED if it's an amount
     * 
     * @param value Original value
     * @param percentOrAmount Type indicator ("P" for percentage, "A" for amount)
     * @return Converted value (AED if amount, unchanged if percentage)
     */
    private BigDecimal convertCancellationValue(BigDecimal value, String percentOrAmount) {
        if (value == null) {
            return null;
        }
        
        // If it's a percentage, don't convert
        if ("P".equalsIgnoreCase(percentOrAmount)) {
            return value;
        }
        
        // If it's an amount, convert from USD to AED
        return CurrencyConverter.convertUsdToAed(value);
    }

    /**
     * Format date from YYYYMMDD to YYYY-MM-DD format
     * 
     * @param dateStr Date string in YYYYMMDD format
     * @return Date string in YYYY-MM-DD format
     */
    private String formatDate(String dateStr) {
        if (dateStr == null || dateStr.length() != 8) {
            return dateStr;
        }

        try {
            return dateStr.substring(0, 4) + "-" + 
                   dateStr.substring(4, 6) + "-" + 
                   dateStr.substring(6, 8);
        } catch (Exception e) {
            logger.warn("Error formatting date: {}", dateStr, e);
            return dateStr;
        }
    }
}
