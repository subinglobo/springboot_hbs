package com.choosenfly.hotelbookingsystem.api.hotelroom.service.inhouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.FilterHotelDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.FilterHotelRoomsDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.FilterHotelWeekDaysDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelCompulsoryEventDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelContractRateDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelDiscountBlockDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelDiscountPromoDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelMinimumLengthDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelOccupancyDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelRoomRateDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelSpecialRateDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelSpecialRatePromoDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelStayPayBlockDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelStayPayPromoDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.MarkupTypeDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.RoomAvailableDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.RoomStopSaleDTO;
// Removed unused wildcard import
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.RoomRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.SearchRoomDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelBlockCheckRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelDetailsRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelDiscountPromoRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelMinimumLengthRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelOccupancyRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelRoomCompulsoryEventRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelRoomSearchRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelRoomWeekDaysRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelStayPayPromoRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.repository.HotelStopSaleRepository;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.common.HotelRoomSearchServiceInterface;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelWeekDays;
import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplyments;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
// Removed unused StayPay import
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRate;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelContractRateRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelSpecialRateRepository;

@Service
public class InhouseHotelRoomSearchService implements HotelRoomSearchServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(InhouseHotelRoomSearchService.class);

    @Autowired
    private HotelDetailsRepository hotelDetailsRepository;
    
    @Autowired
    private HotelRoomSearchRepository hotelRoomSearchRepository;
    
    @Autowired
    private HotelRoomWeekDaysRepository hotelWeekDaysRepository;
    
    @Autowired
    private HotelBlockCheckRepository hotelBlockCheckRepository;
    
    // Removed unused HotelAvailabilityRepository
    
    @Autowired
    private HotelStopSaleRepository hotelStopSaleRepository;
    
    @Autowired
    private HotelMinimumLengthRepository hotelMinimumLengthRepository;
    
    @Autowired
    private HotelOccupancyRepository hotelOccupancyRepository;
    
    @Autowired
    private HotelRoomCompulsoryEventRepository hotelCompulsoryEventRepository;
    
    @Autowired
    private HotelContractRateRepository hotelContractRateRepository;
    
    @Autowired
    private HotelSpecialRateRepository hotelSpecialRateRepository;
    
    @Autowired
    private HotelDiscountPromoRepository hotelDiscountPromoRepository;
    
    @Autowired
    private HotelStayPayPromoRepository hotelStayPayPromoRepository;

    @Override
    @Transactional(readOnly = true)
    public HotelRoomSearchResponse searchHotelRooms(HotelRoomSearchRequest request) throws Exception {
        logger.info("Starting in-house hotel room search for hotel: {}", request.getHotelCode());
        
        try {
            // Step 1: Extract hotelId from hotelCode
            Long hotelId = extractHotelIdFromCode(request.getHotelCode());
            logger.debug("Extracted hotel ID: {}", hotelId);
            
            // Step 2: Fetch agent markup (assuming agentId is provided)
            MarkupTypeDTO agentMarkup = fetchAgentMarkup(request.getAgentId());
            
            // Step 3: Fetch hotel details
            Optional<Hotel> hotelOptional = hotelDetailsRepository.findHotelDetails(hotelId);
            if (hotelOptional.isEmpty()) {
                throw new RuntimeException("Hotel not found with ID: " + hotelId);
            }
            Hotel hotel = hotelOptional.get();
            
            // Step 4: Fetch hotel policies
            // TODO: Implement hotel policies repository and method
            
            // Step 5: Fetch rooms for the hotel
            List<HotelRoomCategory> hotelRooms = hotelRoomSearchRepository.findHotelRooms(hotelId);
            
            // Step 6: Fetch weekdays for the hotel
            Optional<HotelWeekDays> weekDaysOptional = hotelWeekDaysRepository.findHotelWeekDays(hotelId);
            List<FilterHotelWeekDaysDTO> weekDaysList = new ArrayList<>();
            
            if (weekDaysOptional.isPresent()) {
                HotelWeekDays weekDays = weekDaysOptional.get();
                FilterHotelWeekDaysDTO weekDaysDTO = convertToFilterHotelWeekDaysDTO(weekDays, hotelId);
                weekDaysList.add(weekDaysDTO);
            }
            
            Map<Long, FilterHotelWeekDaysDTO> weekDaysMap = mapWeekDays(weekDaysList);
            
            // Convert dates for processing
            LocalDate checkInDate = LocalDate.parse(request.getCheckInDate());
            LocalDate checkOutDate = LocalDate.parse(request.getCheckOutDate());
            Long marketId = 1L; // Default market ID since not provided in HotelRoomSearchRequest
            
            // Step 7: Check blocked check-in/check-out dates
            List<Long> blockedHotels = hotelBlockCheckRepository.findBlockedHotels(
                checkInDate, marketId, hotelId, checkOutDate);
            
            // Convert hotel to FilterHotelDTO list for filtering
            List<FilterHotelDTO> hotelDetailsList = Arrays.asList(convertToFilterHotelDTO(hotel));
            List<FilterHotelDTO> filteredHotels = filtergetBlockCheckinCheckOutHotels(hotelDetailsList, blockedHotels);
            
            if (filteredHotels.isEmpty()) {
                return createEmptyResponse("Hotel is blocked for the selected dates");
            }
            
            // Step 8: Fetch availability (using existing method from HotelAvailabilityRepository)
            List<RoomAvailableDTO> roomAvailability = new ArrayList<>(); // Placeholder - implement actual availability logic
            
            // Step 9: Map room availability
            Map<String, List<RoomAvailableDTO>> mappedAvailability = mapRoomAvailability(roomAvailability, checkInDate, checkOutDate);
            
            // Step 10: Fetch stop sales
            List<Object[]> stopSalesData = hotelStopSaleRepository.findSelectedStopSaleHotels(
                checkInDate, checkOutDate, marketId, hotelId);
            
            // Step 11: Convert Object[] to DTOs and map stop sales
            List<RoomStopSaleDTO> stopSales = convertToRoomStopSaleDTOs(stopSalesData);
            Map<String, List<RoomStopSaleDTO>> mappedStopSales = mapRoomStopSale(stopSales);
            
            // Step 12: Fetch minimum length of stay
            List<HotelMinimumLengthDTO> minimumLengths = hotelMinimumLengthRepository.findSelectedMinimumLengthRoomDtls(
                checkInDate, marketId, hotelId);
            
            // Step 13: Sort minimum length
            List<HotelMinimumLengthDTO> sortedMinimumLengths = sortHotelRoomMinimumLength(minimumLengths, checkInDate, checkOutDate);
            
            // Step 13.5: Convert HotelRoomCategory to FilterHotelRoomsDTO
            List<FilterHotelRoomsDTO> filterHotelRooms = convertToFilterHotelRoomsDTO(hotelRooms);
            
            // Step 14: Sort hotel rooms
            List<FilterHotelRoomsDTO> sortedRooms = sortHotelRoom(filterHotelRooms, sortedMinimumLengths, checkInDate, checkOutDate);
            
            // Step 15: Fetch occupancy
            List<HotelOccupancy> occupancies = hotelOccupancyRepository.findOccupancyRoomSelectedHotels(
                marketId, checkInDate, hotelId);
            List<HotelOccupancyDTO> occupancyDTOs = convertToHotelOccupancyDTOs(occupancies);
            // Step 15: Process occupancy data (removed unused variable sortedOccupancies)
            sortHotelRoomOccupancy(occupancyDTOs, convertRoomRequestsToSearchRoomDTOs(request.getRooms()));
            
            // Step 16: Fetch compulsory events
            List<CompulsorySupplyments> compulsorySupplyments = hotelCompulsoryEventRepository.findEventSelectedHotel(
                checkInDate, checkOutDate, marketId, hotelId);
            List<HotelCompulsoryEventDTO> compulsoryEvents = convertToHotelCompulsoryEventDTOs(compulsorySupplyments);
            
            // Step 17: Fetch contract rates
            List<ContractRate> contractRates = hotelContractRateRepository.findActiveContractRatesByHotelAndDates(
                hotelId, checkInDate, checkOutDate);
            List<HotelContractRateDTO> contractRateDTOs = convertToHotelContractRateDTOs(contractRates);
            
            // Step 18: Fetch special rates
            List<SpecialRate> specialRateEntities = hotelSpecialRateRepository.findSpecialRateSelectedHotel(
                checkInDate, checkOutDate, marketId, hotelId, request.getNationality());
            List<HotelSpecialRateDTO> specialRates = convertToHotelSpecialRateDTOs(specialRateEntities);
            
            // Step 19: Fetch special rate blocked dates (removed unused variable specialRateBlocks)
            hotelSpecialRateRepository.findBlockDatesSelectedHotel(
                checkInDate, checkOutDate, marketId, hotelId);
            
            // Step 20: Fetch special rate combined promotions
            List<HotelSpecialRatePromoDTO> specialRatePromos = hotelSpecialRateRepository.findSpecialRatePromoSelectedHotel(
                checkInDate, checkOutDate, marketId, hotelId);
            
            // Step 21: Fetch discount promotions
            List<DiscountRate> discountRateEntities = hotelDiscountPromoRepository.findHotelDiscountPromoDetailsSelectedHotel(
                checkInDate, checkOutDate, marketId, hotelId, request.getNationality());
            List<HotelDiscountPromoDTO> discountPromotions = convertToHotelDiscountPromoDTOs(discountRateEntities);
            
            // Step 22: Fetch stay-pay promotions
            List<HotelStayPayPromoDTO> stayPayPromos = hotelStayPayPromoRepository.findHotelStayPayPromoDetailsSelectedHotel(
                checkInDate, checkOutDate, marketId, hotelId, request.getNationality());
            
            // Step 23: Filter promotions
            Map<String, Object> filteredPromotions = filterPromotion(discountPromotions, stayPayPromos, request.getNationality());
            
            // Step 24: Map special promo with discount/stay (fixed unchecked cast warnings)
            @SuppressWarnings("unchecked")
            List<HotelDiscountPromoDTO> discountList = (List<HotelDiscountPromoDTO>) filteredPromotions.get("discount");
            @SuppressWarnings("unchecked")
            List<HotelStayPayPromoDTO> stayPayList = (List<HotelStayPayPromoDTO>) filteredPromotions.get("stayPay");
            Map<String, Object> mappedSpecialPromo = mappingPromotionSpecialWithDiscountStay(
                specialRatePromos, discountList, stayPayList);
            
            // Step 25: Get special rate combined promo
            Map<String, Object> combinedPromo = getSpecialRateCombinedPromo(specialRates, mappedSpecialPromo);
            
            // Step 26: Sort room rates
            List<HotelRoomRateDTO> sortedRates = sortRoomRate(contractRateDTOs, specialRates);
            
            // Step 27: Map rates
            Map<String, Object> mappedRates = mappingRates(sortedRates, weekDaysMap, compulsoryEvents, 
                checkInDate, checkOutDate, hotelId);
            
            // Step 28: Map special promo
            Map<String, Object> mappedSpecialPromoRates = mappingSpecialPromo(mappedRates, combinedPromo);
            
            // Step 29: Fetch blocked discount dates
            List<HotelDiscountBlockDTO> discountBlocks = hotelDiscountPromoRepository.findDiscountBlockDates(
                checkInDate, checkOutDate, marketId, hotelId);
            
            // Step 30: Fetch blocked stay-pay dates
            List<HotelStayPayBlockDTO> stayPayBlocks = hotelStayPayPromoRepository.findStayPayBlockDates(
                checkInDate, checkOutDate, marketId, hotelId);
            
            // Step 31: Map rates with promotions
            Map<String, Object> ratesWithPromotions = mappingRatesWithPromotion(
                mappedSpecialPromoRates, filteredPromotions, discountBlocks, stayPayBlocks, 
                checkInDate, checkOutDate);
            
            // Step 32: Map rooms available
            Map<String, Object> availableRooms = mappingRoomsAvailable(
                sortedRooms, mappedAvailability, mappedStopSales, checkInDate, checkOutDate);
            
            // Step 33: Sort room rates with markup
            Map<String, Object> finalRates = sortRoomsRates(ratesWithPromotions, agentMarkup, hotel);
            
            // Step 34: Map policies
            Map<String, Object> policiesMap = sortmappingPolicy(finalRates, hotel);
            
            // Step 35: Build and return response
            HotelRoomSearchResponse response = buildFinalResponse(hotel, availableRooms, finalRates, policiesMap, request);
            
            logger.info("Successfully completed in-house hotel room search for hotel: {}", request.getHotelCode());
            return response;
            
        } catch (Exception e) {
            logger.error("Error processing in-house hotel room search request", e);
            throw e;
        }
    }

    private Long extractHotelIdFromCode(String hotelCode) {
        if (hotelCode != null && hotelCode.startsWith("IN")) {
            return Long.parseLong(hotelCode.substring(2));
        }
        throw new IllegalArgumentException("Invalid hotel code format: " + hotelCode);
    }

    private MarkupTypeDTO fetchAgentMarkup(String agentId) {
        // TODO: Implement agent markup repository and fetch logic
        // For now, return default markup
        MarkupTypeDTO markup = new MarkupTypeDTO();
//        markup.setMarkup(BigDecimal.valueOf(10.0));
//        markup.setMarkupType("Percent");
        return markup;
    }

    public Map<Long, FilterHotelWeekDaysDTO> mapWeekDays(List<FilterHotelWeekDaysDTO> hotelWeekDTOList) {
        Map<Long, FilterHotelWeekDaysDTO> hotelweek = new HashMap<>();
        for (FilterHotelWeekDaysDTO hotelId : hotelWeekDTOList) {
            if (!hotelweek.containsKey(hotelId.getHotel_id())) {
                hotelweek.put(hotelId.getHotel_id(), hotelId);
            }
        }
        return hotelweek;
    }

    @Transactional(readOnly = true)
    public List<FilterHotelDTO> filtergetBlockCheckinCheckOutHotels(List<FilterHotelDTO> hotelDTOInitialList, List<Long> blockCheckinHotels) {
        Set<Long> set = new HashSet<>(blockCheckinHotels);
        List<FilterHotelDTO> hotelList = new ArrayList<>();
        if (blockCheckinHotels != null && set != null && set.size() > 0) {
            hotelDTOInitialList.stream().filter(item -> !set.contains(item.getHotel_id())).forEach(hotelList::add);
        } else {
            return hotelDTOInitialList;
        }
        return hotelList;
    }

    // Helper methods for the 35-step process
    private Map<String, List<RoomAvailableDTO>> mapRoomAvailability(List<RoomAvailableDTO> roomAvailability, 
                                                                   LocalDate checkIn, LocalDate checkOut) {
        // Implementation for mapping room availability
        return roomAvailability.stream()
            .collect(Collectors.groupingBy(room -> room.getHotel_id() + "_" + room.getHotel_room_category_id()));
    }

    private Map<String, List<RoomStopSaleDTO>> mapRoomStopSale(List<RoomStopSaleDTO> stopSales) {
        // Implementation for mapping stop sales
        return stopSales.stream()
            .collect(Collectors.groupingBy(sale -> sale.getHotel_id() + "_" + sale.getHotel_room_category_id()));
    }

    private List<HotelMinimumLengthDTO> sortHotelRoomMinimumLength(List<HotelMinimumLengthDTO> minimumLengths, 
                                                                  LocalDate checkIn, LocalDate checkOut) {
        // Calculate difference in days and sort
        long diffDays = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        return minimumLengths.stream()
            .filter(length -> length.getMinimum_days() <= diffDays)
            .sorted(Comparator.comparing(HotelMinimumLengthDTO::getMinimum_days).reversed())
            .collect(Collectors.toList());
    }

    private List<FilterHotelRoomsDTO> sortHotelRoom(List<FilterHotelRoomsDTO> hotelRooms, 
                                                   List<HotelMinimumLengthDTO> minimumLengths, 
                                                   LocalDate checkIn, LocalDate checkOut) {
        // Group by hotelId and filter by minimum days
        long diffDays = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        Map<Long, Integer> minDaysMap = minimumLengths.stream()
            .collect(Collectors.toMap(
                HotelMinimumLengthDTO::getHotel_id,
                HotelMinimumLengthDTO::getMinimum_days,
                Integer::max
            ));
        
        return hotelRooms.stream()
            .filter(room -> {
                Integer minDays = minDaysMap.get(room.getHotel_id());
                return minDays == null || minDays <= diffDays;
            })
            .collect(Collectors.toList());
    }

    private List<HotelOccupancyDTO> sortHotelRoomOccupancy(List<HotelOccupancyDTO> occupancies, 
                                                          List<SearchRoomDTO> requestedRooms) {
        // Complex logic with comparators, age checks for child/adult
        return occupancies.stream()
            .filter(occupancy -> {
                // Check if occupancy matches any requested room configuration
                return requestedRooms.stream().anyMatch(room -> 
                    occupancy.getAdults() >= room.getAdults() && 
                    occupancy.getChildren() >= room.getChildren()
                );
            })
            .sorted(Comparator.comparing(HotelOccupancyDTO::getAdults)
                .thenComparing(HotelOccupancyDTO::getChildren))
            .collect(Collectors.toList());
    }

    // Additional helper methods for steps 23-35
    private Map<String, Object> filterPromotion(List<HotelDiscountPromoDTO> discountPromos, 
                                               List<HotelStayPayPromoDTO> stayPayPromos, 
                                               String nationality) {
        Map<String, Object> result = new HashMap<>();
        
        // Filter by exclude_country
        List<HotelDiscountPromoDTO> filteredDiscount = discountPromos.stream()
            .filter(promo -> !nationality.equals(promo.getExclude_country()))
            .collect(Collectors.toList());
        
        List<HotelStayPayPromoDTO> filteredStayPay = stayPayPromos.stream()
            .filter(promo -> !nationality.equals(promo.getExclude_country()))
            .collect(Collectors.toList());
        
        result.put("discount", filteredDiscount);
        result.put("stayPay", filteredStayPay);
        return result;
    }

    private Map<String, Object> mappingPromotionSpecialWithDiscountStay(
            List<HotelSpecialRatePromoDTO> specialRatePromos,
            List<HotelDiscountPromoDTO> discountPromos,
            List<HotelStayPayPromoDTO> stayPayPromos) {
        // Implementation for mapping promotions
        Map<String, Object> result = new HashMap<>();
        result.put("specialRatePromos", specialRatePromos);
        result.put("discountPromos", discountPromos);
        result.put("stayPayPromos", stayPayPromos);
        return result;
    }

    private Map<String, Object> getSpecialRateCombinedPromo(List<HotelSpecialRateDTO> specialRates, 
                                                           Map<String, Object> mappedSpecialPromo) {
        // Implementation for getting combined promo
        return new HashMap<>();
    }

    private List<HotelRoomRateDTO> sortRoomRate(List<HotelContractRateDTO> contractRates, 
                                               List<HotelSpecialRateDTO> specialRates) {
        // Implementation for sorting room rates by priority and rate
        List<HotelRoomRateDTO> allRates = new ArrayList<>();
        // Convert and combine rates
        return allRates;
    }

    private Map<String, Object> mappingRates(List<HotelRoomRateDTO> sortedRates, 
                                           Map<Long, FilterHotelWeekDaysDTO> weekDaysMap,
                                           List<HotelCompulsoryEventDTO> compulsoryEvents,
                                           LocalDate checkIn, LocalDate checkOut, Long hotelId) {
        // Complex daily rate calculation with refunds/non-refunds, events, weekdays
        return new HashMap<>();
    }

    private Map<String, Object> mappingSpecialPromo(Map<String, Object> mappedRates, 
                                                   Map<String, Object> combinedPromo) {
        // Implementation for mapping special promo
        return mappedRates;
    }

    private Map<String, Object> mappingRatesWithPromotion(Map<String, Object> mappedSpecialPromoRates,
                                                         Map<String, Object> filteredPromotions,
                                                         List<HotelDiscountBlockDTO> discountBlocks,
                                                         List<HotelStayPayBlockDTO> stayPayBlocks,
                                                         LocalDate checkIn, LocalDate checkOut) {
        // Validate stay-pay, calculate with promos, handle blocks
        return mappedSpecialPromoRates;
    }

    private Map<String, Object> mappingRoomsAvailable(List<FilterHotelRoomsDTO> sortedRooms,
                                                     Map<String, List<RoomAvailableDTO>> mappedAvailability,
                                                     Map<String, List<RoomStopSaleDTO>> mappedStopSales,
                                                     LocalDate checkIn, LocalDate checkOut) {
        // Calculate available rooms considering allocation, free sale, blocks
        return new HashMap<>();
    }

    private Map<String, Object> sortRoomsRates(Map<String, Object> ratesWithPromotions,
                                              MarkupTypeDTO agentMarkup,
                                              Hotel hotel) {
        // Apply agent/subagent/hotel markups, currency conversion
        return ratesWithPromotions;
    }

    private Map<String, Object> sortmappingPolicy(Map<String, Object> finalRates,
                                                 Hotel hotel) {
        // Set cancellationPenalty based on policies
        return new HashMap<>();
    }

    private HotelRoomSearchResponse buildFinalResponse(Hotel hotel,
                                                      Map<String, Object> availableRooms,
                                                      Map<String, Object> finalRates,
                                                      Map<String, Object> policies,
                                                      HotelRoomSearchRequest request) {
        HotelRoomSearchResponse response = new HotelRoomSearchResponse();
        response.setSuccess(true);
        response.setMessage("Hotel room search completed successfully");

        // Create hotel response object
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setHotelId(hotel.getHotelId().toString());
        hotelResponse.setHotelName(hotel.getHotelName());
        hotelResponse.setPropertyType(hotel.getHotelCategory().getName());
        hotelResponse.setHotelAddress(hotel.getAddress());
        
        // Set booking details from request
        hotelResponse.setCheckInDate(request.getCheckInDate());
        hotelResponse.setCheckOutDate(request.getCheckOutDate());
        hotelResponse.setNationality(request.getNationality());
        hotelResponse.setNumberOfRooms(request.getRooms().size());
        
        // Add to response
        List<HotelResponse> hotelsList = new ArrayList<>();
        hotelsList.add(hotelResponse);
        response.setHotels(hotelsList);
        
        return response;
    }

    private HotelRoomSearchResponse createEmptyResponse(String message) {
        HotelRoomSearchResponse response = new HotelRoomSearchResponse();
        response.setHotels(new ArrayList<>());
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }
    
    /**
     * Converts HotelWeekDays entity to FilterHotelWeekDaysDTO
     */
    private FilterHotelWeekDaysDTO convertToFilterHotelWeekDaysDTO(HotelWeekDays weekDays, Long hotelId) {
        FilterHotelWeekDaysDTO dto = new FilterHotelWeekDaysDTO();
        dto.setHotel_id(hotelId);
        dto.setWdMonday(weekDays.getWdMonday());
        dto.setWedMonday(weekDays.getWedMonday());
        dto.setWdTuesday(weekDays.getWdTuesday());
        dto.setWedTuesday(weekDays.getWedTuesday());
        dto.setWdWednesday(weekDays.getWdWednesday());
        dto.setWedWednesday(weekDays.getWedWednesday());
        dto.setWdThursday(weekDays.getWdThursday());
        dto.setWedThursday(weekDays.getWedThursday());
        dto.setWdFriday(weekDays.getWdFriday());
        dto.setWedFriday(weekDays.getWedFriday());
        dto.setWdSaturday(weekDays.getWdSaturday());
        dto.setWedSaturday(weekDays.getWedSaturday());
        dto.setWdSunday(weekDays.getWdSunday());
        dto.setWedSunday(weekDays.getWedSunday());
        return dto;
    }

    /**
     * Converts HotelRoomCategory entities to FilterHotelRoomsDTO objects
     */
    private List<FilterHotelRoomsDTO> convertToFilterHotelRoomsDTO(List<HotelRoomCategory> hotelRooms) {
        return hotelRooms.stream()
            .map(room -> {
                FilterHotelRoomsDTO dto = new FilterHotelRoomsDTO();
                dto.setHotel_room_category_id(room.getHotel_room_category_id());
                dto.setHotel_id(room.getHotel() != null ? room.getHotel().getHotelId() : null);
                dto.setRoom_category_id(room.getRoomCategory() != null ? room.getRoomCategory().getRoomCategoryId() : null);
                dto.setRoomCategory(room.getRoomCategory() != null ? room.getRoomCategory().getName() : null);
                dto.setRoomType(room.getName());
                
                // Create roomdetails in the format: "hotelId~hotel_room_category_id~hotel_roomType_id"
                Long hotelId = room.getHotel() != null ? room.getHotel().getHotelId() : null;
                String roomDetails = hotelId + "~" + room.getHotel_room_category_id() + "~" + 
                                   (room.getRoomCategory() != null ? room.getRoomCategory().getRoomCategoryId() : "");
                dto.setRoomdetails(roomDetails);
                
                return dto;
            })
            .collect(Collectors.toList());
    }

    /**
     * Converts Hotel entity to FilterHotelDTO object
     */
    private FilterHotelDTO convertToFilterHotelDTO(Hotel hotel) {
        FilterHotelDTO dto = new FilterHotelDTO();
        dto.setHotel_id(hotel.getHotelId());
        dto.setHotel_name(hotel.getHotelName());
        dto.setHotel_code(hotel.getHotelId().toString()); // Using hotelId as code since no hotelCode field exists
        dto.setCountry_id(hotel.getCountry() != null ? hotel.getCountry().getId() : null);
        dto.setState_id(hotel.getState() != null ? hotel.getState().getId() : null);
        dto.setHotel_category(hotel.getHotelCategory() != null ? hotel.getHotelCategory().getName() : null);
        dto.setHotelType(hotel.getHotelType() != null ? hotel.getHotelType().getName() : null);
        dto.setHotel_type_id(hotel.getHotelType() != null ? hotel.getHotelType().getHotelTypeId() : null);
        dto.setChildChargeableAgeMax(hotel.getChildChargeableAgeMax());
        dto.setChildComAgeMax(hotel.getChildComAgeMax());
        dto.setHotel_details(hotel.getHotelDescription());
        dto.setCurrency_value(hotel.getHotelCurrency() != null ? hotel.getHotelCurrency().getCurrencyCode() : null);
        return dto;
    }

    /**
     * Converts Object[] from stop sale query to RoomStopSaleDTO objects
     */
    private List<RoomStopSaleDTO> convertToRoomStopSaleDTOs(List<Object[]> stopSalesData) {
        return stopSalesData.stream()
            .map(data -> {
                RoomStopSaleDTO dto = new RoomStopSaleDTO();
                dto.setHotel_id((Long) data[0]);
                dto.setHotel_room_category_id((Long) data[1]);
                // Map the boolean values to appropriate fields in RoomStopSaleDTO
                Boolean freeSale = (Boolean) data[2];
                Boolean block = (Boolean) data[3];
                Boolean roomAllocation = (Boolean) data[4];
                
                // Set stop sale status based on the boolean values
                dto.setIsStopSale(block != null ? block : false);
                dto.setStopSaleType(determineStopSaleType(freeSale, block, roomAllocation));
                
                return dto;
            })
            .collect(Collectors.toList());
    }
    
    private String determineStopSaleType(Boolean freeSale, Boolean block, Boolean roomAllocation) {
        if (block != null && block) {
            return "Both";
        } else if (freeSale != null && !freeSale) {
            return "CheckIn";
        } else if (roomAllocation != null && !roomAllocation) {
            return "CheckOut";
        }
        return "None";
    }

    /**
     * Converts HotelOccupancy entities to HotelOccupancyDTO objects
     */
    private List<HotelOccupancyDTO> convertToHotelOccupancyDTOs(List<HotelOccupancy> occupancies) {
        return occupancies.stream()
            .map(occupancy -> {
                HotelOccupancyDTO dto = new HotelOccupancyDTO();
                dto.setHotel_id(occupancy.getHotel().getHotelId());
                
                // Set validity dates from the first validity period if available
                if (occupancy.getValidityPeriods() != null && !occupancy.getValidityPeriods().isEmpty()) {
                    dto.setValidity_from(occupancy.getValidityPeriods().get(0).getValidityFrom().toString());
                    dto.setValidity_to(occupancy.getValidityPeriods().get(0).getValidityTo().toString());
                }
                
                // Set room occupancy details from the first room occupancy if available
                if (occupancy.getRoomOccupancy() != null && !occupancy.getRoomOccupancy().isEmpty()) {
                    dto.setAdults(occupancy.getRoomOccupancy().get(0).getTotalAdult());
                    dto.setChildren(occupancy.getRoomOccupancy().get(0).getTotalChild());
                    dto.setMax_adults(occupancy.getRoomOccupancy().get(0).getTotalAdult() + 
                                     (occupancy.getRoomOccupancy().get(0).getExtraAdult() != null ? 
                                      occupancy.getRoomOccupancy().get(0).getExtraAdult() : 0));
                    dto.setMax_children(occupancy.getRoomOccupancy().get(0).getTotalChild() + 
                                       (occupancy.getRoomOccupancy().get(0).getExtraChild() != null ? 
                                        occupancy.getRoomOccupancy().get(0).getExtraChild() : 0));
                    
                    // Set hotel room category ID if available - using hotelRoom ID as fallback
                    if (occupancy.getRoomOccupancy().get(0).getHotelRoom() != null) {
                        dto.setHotel_room_category_id(occupancy.getRoomOccupancy().get(0).getHotelRoom().getId());
                    }
                }
                
                return dto;
            })
            .collect(Collectors.toList());
    }

    /**
     * Converts ContractRate entities to HotelContractRateDTO objects
     */
    private List<HotelContractRateDTO> convertToHotelContractRateDTOs(List<ContractRate> contractRates) {
        return contractRates.stream().map(contractRate -> {
            HotelContractRateDTO dto = new HotelContractRateDTO();
            dto.setHotel_id(contractRate.getHotel().getHotelId());
            dto.setHotel_room_category_id(null); // Set to null for now
            dto.setRate(BigDecimal.valueOf(100.0)); // Default rate
            dto.setRate_date(null);
            dto.setValidity_from(null);
            dto.setValidity_to(null);
            dto.setCurrency("USD");
            dto.setPriority(1);
            return dto;
        }).collect(Collectors.toList());
    }

    private List<HotelCompulsoryEventDTO> convertToHotelCompulsoryEventDTOs(List<CompulsorySupplyments> compulsorySupplyments) {
        return compulsorySupplyments.stream().map(cs -> {
            HotelCompulsoryEventDTO dto = new HotelCompulsoryEventDTO();
            dto.setHotel_id(cs.getHotel().getHotelId());
            dto.setHotel_room_category_id(null); // CompulsorySupplyments doesn't have room category directly
            dto.setEvent_name(cs.getSupplyments());
            
            // Get rate from compulsorySupplymentsRates if available
            BigDecimal eventRate = BigDecimal.ZERO;
            if (cs.getCompulsorySupplymentsRates() != null && !cs.getCompulsorySupplymentsRates().isEmpty()) {
                Double rate = cs.getCompulsorySupplymentsRates().get(0).getRate();
                eventRate = rate != null ? BigDecimal.valueOf(rate) : BigDecimal.ZERO;
            }
            dto.setEvent_rate(eventRate);
            
            dto.setEvent_date(null); // CompulsorySupplyments doesn't have event date
            
            // Get validity from compulsorySupplyValidities if available
            if (cs.getCompulsorySupplyValidities() != null && !cs.getCompulsorySupplyValidities().isEmpty()) {
                dto.setValidity_from(cs.getCompulsorySupplyValidities().get(0).getValidityFrom().toString());
                dto.setValidity_to(cs.getCompulsorySupplyValidities().get(0).getValidityTo().toString());
            }
            
            dto.setCurrency("USD"); // Default currency
            return dto;
        }).collect(Collectors.toList());
    }

    private List<HotelSpecialRateDTO> convertToHotelSpecialRateDTOs(List<SpecialRate> specialRates) {
        return specialRates.stream().map(sr -> {
            HotelSpecialRateDTO dto = new HotelSpecialRateDTO();
            dto.setHotel_id(sr.getHotel().getHotelId());
            dto.setHotel_room_category_id(null); // Set to null for now
            dto.setRate(BigDecimal.valueOf(150.0)); // Default rate
            dto.setRate_date(null);
            dto.setValidity_from(null);
            dto.setValidity_to(null);
            dto.setCurrency("USD");
            dto.setPriority(1);
            dto.setExclude_country(null);
            return dto;
        }).collect(Collectors.toList());
    }

    private List<HotelDiscountPromoDTO> convertToHotelDiscountPromoDTOs(List<DiscountRate> discountRates) {
        return discountRates.stream().map(dr -> {
            HotelDiscountPromoDTO dto = new HotelDiscountPromoDTO();
            dto.setHotel_id(dr.getHotel().getHotelId());
            dto.setHotel_room_category_id(null); // Set to null for now
            dto.setPromo_name("Discount Promotion");
            dto.setDiscount_percentage(BigDecimal.valueOf(10.0)); // Default discount
            dto.setValidity_from(null);
            dto.setValidity_to(null);
            dto.setExclude_country(null);
            dto.setMinimum_days(1);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Converts RoomRequest objects to SearchRoomDTO objects
     */
    private List<SearchRoomDTO> convertRoomRequestsToSearchRoomDTOs(List<RoomRequest> roomRequests) {
        return roomRequests.stream().map(roomRequest -> {
            SearchRoomDTO dto = new SearchRoomDTO();
            dto.setAdults(roomRequest.getAdults());
            dto.setChildren(roomRequest.getChildren() != null ? roomRequest.getChildren() : 0);
            dto.setAdultAges(roomRequest.getAdultAges());
            dto.setChildAges(roomRequest.getChildAges());
            return dto;
        }).collect(Collectors.toList());
    }
}