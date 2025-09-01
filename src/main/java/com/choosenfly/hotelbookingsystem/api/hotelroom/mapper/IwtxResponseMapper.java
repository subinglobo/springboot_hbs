package com.choosenfly.hotelbookingsystem.api.hotelroom.mapper;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxHotelSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxGroupedRoomResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.*;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.util.CancellationPolicyTextGenerator;
import com.choosenfly.hotelbookingsystem.api.hotelroom.util.CurrencyConverter;
import com.choosenfly.hotelbookingsystem.api.iwtx.repository.IwtxHotelRepository;
import com.choosenfly.hotelbookingsystem.api.iwtx.entities.IwtxHotel;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper to convert IWTX XML response to standardized JSON response format
 */
@Component
public class IwtxResponseMapper {

    private static final Logger logger = LoggerFactory.getLogger(IwtxResponseMapper.class);
    private final IwtxHotelRepository iwtxHotelRepository;

    public IwtxResponseMapper(IwtxHotelRepository iwtxHotelRepository) {
        this.iwtxHotelRepository = iwtxHotelRepository;
    }

    /**
     * Map IWTX hotel search response to list of hotel responses
     * 
     * @param iwtxResponse IWTX hotel search response
     * @param originalRequest Original search request for booking details
     * @return List of hotel responses in standardized format
     */
    public List<HotelResponse> mapToHotelResponses(IwtxHotelSearchResponse iwtxResponse, HotelRoomSearchRequest originalRequest) {
        logger.debug("Mapping IWTX response to hotel responses");

        if (iwtxResponse == null || iwtxResponse.getHotels() == null || 
            iwtxResponse.getHotels().getHotelList() == null) {
            logger.warn("IWTX response is null or contains no hotels");
            return new ArrayList<>();
        }

        List<HotelResponse> hotelResponses = new ArrayList<>();

        for (IwtxHotelSearchResponse.IwtxHotel iwtxHotel : iwtxResponse.getHotels().getHotelList()) {
            try {
                HotelResponse hotelResponse = mapToHotelResponse(iwtxHotel, originalRequest);
                if (hotelResponse != null) {
                    hotelResponses.add(hotelResponse);
                }
            } catch (Exception e) {
                logger.error("Error mapping hotel: {}", iwtxHotel.getHotelName(), e);
                // Continue processing other hotels
            }
        }

        logger.info("Successfully mapped {} hotels from IWTX response", hotelResponses.size());
        return hotelResponses;
    }

    /**
     * Map single IWTX hotel to hotel response
     * 
     * @param iwtxHotel IWTX hotel
     * @param originalRequest Original search request for booking details
     * @return Hotel response
     */
    private HotelResponse mapToHotelResponse(IwtxHotelSearchResponse.IwtxHotel iwtxHotel, HotelRoomSearchRequest originalRequest) {
        if (iwtxHotel == null) {
            return null;
        }

        HotelResponse hotelResponse = new HotelResponse();
        
        // Map basic hotel information
        hotelResponse.setHotelId(iwtxHotel.getHotelId());
        hotelResponse.setHotelName(iwtxHotel.getHotelName());
        hotelResponse.setStarRating(iwtxHotel.getStarRating());
        hotelResponse.setPropertyType(iwtxHotel.getPropertyType());
        hotelResponse.setChain(iwtxHotel.getChain());
        hotelResponse.setCity(iwtxHotel.getCity());
        hotelResponse.setTimeZone(iwtxHotel.getTimeZone());

        // Map geo location
        if (iwtxHotel.getGeoLocation() != null) {
            GeoLocation geoLocation = new GeoLocation(
                iwtxHotel.getGeoLocation().getLongitude(),
                iwtxHotel.getGeoLocation().getLatitude()
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
            
            hotelResponse.setDestination(iwtxHotel.getCity()); // Use hotel city as destination
        }

        // Fetch hotel address and phone from database using hotel code
        try {
            IwtxHotel hotelEntity = iwtxHotelRepository.findByHotelCode(iwtxHotel.getHotelId());
            if (hotelEntity != null) {
                hotelResponse.setHotelAddress(hotelEntity.getHotelAddress() != null ? 
                    hotelEntity.getHotelAddress() : "Address not available");
                hotelResponse.setHotelPhoneNumber(hotelEntity.getHotelPhone() != null ? 
                    hotelEntity.getHotelPhone() : "Phone not available");
            } else {
                hotelResponse.setHotelAddress("Hotel not found in database");
                hotelResponse.setHotelPhoneNumber("Phone not available");
            }
        } catch (Exception e) {
            logger.warn("Error fetching hotel details from database for hotel code: {}", iwtxHotel.getHotelId(), e);
            hotelResponse.setHotelAddress("Address not available");
            hotelResponse.setHotelPhoneNumber("Phone not available");
        }

        // Map room categories - use grouped rooms if available, otherwise fall back to individual rooms
        List<RoomCategoryResponse> roomCategories = mapToRoomCategoriesFromGrouped(iwtxHotel);
        hotelResponse.setRoomCategories(roomCategories);

        return hotelResponse;
    }

    /**
     * Map IWTX hotel rooms to room category responses using grouped rooms structure
     * 
     * @param iwtxHotel IWTX hotel containing room details
     * @return List of room category responses
     */
    private List<RoomCategoryResponse> mapToRoomCategoriesFromGrouped(IwtxHotelSearchResponse.IwtxHotel iwtxHotel) {
        // First try to use grouped rooms if available
        if (iwtxHotel.getGroupedRooms() != null && !iwtxHotel.getGroupedRooms().isEmpty()) {
            return mapGroupedRoomsToRoomCategories(iwtxHotel.getGroupedRooms());
        }
        
        // Fall back to individual room details if grouped rooms not available
        return mapIndividualRoomsToCategories(iwtxHotel);
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
     * @param iwtxHotel IWTX hotel containing room details
     * @return List of room category responses
     */
    private List<RoomCategoryResponse> mapIndividualRoomsToCategories(IwtxHotelSearchResponse.IwtxHotel iwtxHotel) {
        // For fallback, create individual room categories from room details
        // This maintains backward compatibility
        List<RoomResponse> individualRooms = mapToRoomResponses(iwtxHotel);
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
     * Map IWTX hotel rooms to room responses (fallback method)
     * 
     * @param iwtxHotel IWTX hotel containing room details
     * @return List of room responses
     */
    private List<RoomResponse> mapToRoomResponses(IwtxHotelSearchResponse.IwtxHotel iwtxHotel) {
        if (iwtxHotel.getRoomTypeDetails() == null || 
            iwtxHotel.getRoomTypeDetails().getRooms() == null ||
            iwtxHotel.getRoomTypeDetails().getRooms().getRoomList() == null) {
            return new ArrayList<>();
        }

        return iwtxHotel.getRoomTypeDetails().getRooms().getRoomList().stream()
            .map(this::mapToRoomResponse)
            .filter(room -> room != null)
            .collect(Collectors.toList());
    }

    /**
     * Map single IWTX room to room response
     * 
     * @param iwtxRoom IWTX room detail
     * @return Room response
     */
    private RoomResponse mapToRoomResponse(IwtxHotelSearchResponse.IwtxRoomDetail iwtxRoom) {
        if (iwtxRoom == null) {
            return null;
        }

        RoomResponse roomResponse = new RoomResponse();
        
        // Map basic room information
        roomResponse.setRoomNo(iwtxRoom.getRoomNo());
        roomResponse.setRoomType(iwtxRoom.getRoomType());
        roomResponse.setRoomTypeCode(iwtxRoom.getRoomTypeCode());
        roomResponse.setMealPlan(iwtxRoom.getMealPlan());
        roomResponse.setMealPlanCode(iwtxRoom.getMealPlanCode());
        roomResponse.setCurrency(iwtxRoom.getCurrCode());
        roomResponse.setRoomStatus(iwtxRoom.getRoomStatus());
        roomResponse.setContractLabel(iwtxRoom.getContractLabel());
        roomResponse.setContractTokenId(iwtxRoom.getContractTokenId());

        // Map pricing information
        roomResponse.setRate(iwtxRoom.getRate());
        roomResponse.setTotalRate(iwtxRoom.getTotalRate());
        roomResponse.setRateBeforeTax(iwtxRoom.getRateBeforeTax());
        roomResponse.setRecommendedRetailPrice(iwtxRoom.getRecommendedRetailPrice());

        // Map non-refundable flag
        roomResponse.setNonRefundable("Y".equalsIgnoreCase(iwtxRoom.getNonRefundable()));

        // Map cancellation policies
        List<CancellationPolicy> cancellationPolicies = mapToCancellationPolicies(iwtxRoom);
        roomResponse.setCancellationPolicies(cancellationPolicies);

        return roomResponse;
    }

    /**
     * Map IWTX cancellation policy details to cancellation policies
     * 
     * @param iwtxRoom IWTX room detail containing cancellation policies
     * @return List of cancellation policies
     */
    private List<CancellationPolicy> mapToCancellationPolicies(IwtxHotelSearchResponse.IwtxRoomDetail iwtxRoom) {
        if (iwtxRoom.getCancellationPolicyDetails() == null ||
            iwtxRoom.getCancellationPolicyDetails().getCancellations() == null) {
            return new ArrayList<>();
        }

        return iwtxRoom.getCancellationPolicyDetails().getCancellations().stream()
            .map(this::mapToCancellationPolicy)
            .filter(policy -> policy != null)
            .collect(Collectors.toList());
    }

    /**
     * Map single IWTX cancellation to cancellation policy
     * 
     * @param iwtxCancellation IWTX cancellation
     * @return Cancellation policy
     */
    private CancellationPolicy mapToCancellationPolicy(IwtxHotelSearchResponse.IwtxCancellation iwtxCancellation) {
        if (iwtxCancellation == null) {
            return null;
        }

        return new CancellationPolicy(
            formatDate(iwtxCancellation.getFromDate()),
            formatDate(iwtxCancellation.getToDate()),
            iwtxCancellation.getPercentOrAmt(),
            convertCancellationValue(iwtxCancellation.getValue(), iwtxCancellation.getPercentOrAmt())
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
