package com.choosenfly.hotelbookingsystem.api.hotelroom.service;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx.IwtxApiService;
import com.choosenfly.hotelbookingsystem.api.hotelroom.mapper.IwtxResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling hotel room search operations
 */
@Service
public class HotelRoomSearchService {

    private static final Logger logger = LoggerFactory.getLogger(HotelRoomSearchService.class);

    private final IwtxApiService iwtxApiService;
    private final IwtxResponseMapper iwtxResponseMapper;

    @Autowired
    public HotelRoomSearchService(IwtxApiService iwtxApiService, IwtxResponseMapper iwtxResponseMapper) {
        this.iwtxApiService = iwtxApiService;
        this.iwtxResponseMapper = iwtxResponseMapper;
    }

    /**
     * Search for hotel rooms based on the provided criteria
     * 
     * @param request Hotel room search request
     * @return Hotel room search response
     */
    public HotelRoomSearchResponse searchHotelRooms(HotelRoomSearchRequest request) {
        logger.info("Processing hotel room search for API ID: {}", request.getApiId());

        try {
            // Validate request
            validateSearchRequest(request);

            // Route to appropriate API based on apiId
            switch (request.getApiId()) {
                case 11:
                    return searchViaIwtx(request);
                default:
                    logger.error("Unsupported API ID: {}", request.getApiId());
                    return HotelRoomSearchResponse.error("API not implemented. Only API ID 11 (IWTX) is currently supported.");
            }

        } catch (Exception e) {
            logger.error("Error during hotel room search", e);
            return HotelRoomSearchResponse.error("Search failed: " + e.getMessage());
        }
    }

    /**
     * Search hotel rooms via IWTX API
     * 
     * @param request Hotel room search request
     * @return Hotel room search response
     */
    private HotelRoomSearchResponse searchViaIwtx(HotelRoomSearchRequest request) {
        logger.info("Searching hotel rooms via IWTX API for hotel code: {}", request.getHotelCode());

        try {
            // Call IWTX API
            var iwtxResponse = iwtxApiService.searchHotelRooms(request);
            
            if (iwtxResponse == null) {
                logger.error("Received null response from IWTX API");
                return HotelRoomSearchResponse.error("No response received from external API");
            }

            // Map IWTX response to common format
            var hotels = iwtxResponseMapper.mapToHotelResponses(iwtxResponse, request);
            
            if (hotels == null || hotels.isEmpty()) {
                logger.info("No hotels found for the search criteria");
                return HotelRoomSearchResponse.success(hotels);
            }

            logger.info("Successfully mapped {} hotels from IWTX response", hotels.size());
            return HotelRoomSearchResponse.success(hotels);

        } catch (Exception e) {
            logger.error("Error calling IWTX API", e);
            return HotelRoomSearchResponse.error("External API call failed: " + e.getMessage());
        }
    }

    /**
     * Validate the hotel room search request
     * 
     * @param request Hotel room search request to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateSearchRequest(HotelRoomSearchRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Search request cannot be null");
        }

        if (request.getCheckInDate() == null || request.getCheckInDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Check-in date is required");
        }

        if (request.getCheckOutDate() == null || request.getCheckOutDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Check-out date is required");
        }

        if (request.getHotelCode() == null || request.getHotelCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel code is required");
        }

        if (request.getRooms() == null || request.getRooms().isEmpty()) {
            throw new IllegalArgumentException("At least one room is required");
        }

        if (request.getNationality() == null || request.getNationality().trim().isEmpty()) {
            throw new IllegalArgumentException("Nationality is required");
        }

        if (request.getApiId() == null) {
            throw new IllegalArgumentException("API ID is required");
        }

        // Validate date format (basic validation)
        if (!request.getCheckInDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Check-in date must be in YYYY-MM-DD format");
        }

        if (!request.getCheckOutDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Check-out date must be in YYYY-MM-DD format");
        }

        // Validate nationality format (2-letter ISO code)
        if (!request.getNationality().matches("[A-Z]{2}")) {
            throw new IllegalArgumentException("Nationality must be a 2-letter ISO country code");
        }

        // Validate rooms
        request.getRooms().forEach(room -> {
            if (room.getAdults() == null || room.getAdults() < 1) {
                throw new IllegalArgumentException("Each room must have at least 1 adult");
            }
            
            // Validate adult ages
            if (room.getAdultAges() != null && room.getAdultAges().size() != room.getAdults()) {
                throw new IllegalArgumentException("Number of adult ages must match number of adults");
            }
            
            // Validate child ages
            if (room.getChildren() != null && room.getChildren() > 0) {
                if (room.getChildAges() == null || room.getChildAges().size() != room.getChildren()) {
                    throw new IllegalArgumentException("Number of child ages must match number of children");
                }
            }
        });

        logger.debug("Hotel room search request validation passed");
    }
}
