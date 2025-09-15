package com.choosenfly.hotelbookingsystem.api.hotelroom.service;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.common.HotelRoomSearchServiceInterface;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx.IwtxHotelRoomSearchService;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.x3.X3HotelRoomSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Legacy service for handling hotel room search operations
 * @deprecated Use /api/hotel-rooms/search endpoint with provider-specific services instead
 */
@Service
@Deprecated
public class HotelRoomSearchService {

    private static final Logger logger = LoggerFactory.getLogger(HotelRoomSearchService.class);

    private final IwtxHotelRoomSearchService iwtxHotelRoomSearchService;
    private final X3HotelRoomSearchService x3HotelRoomSearchService;

    @Autowired
    public HotelRoomSearchService(IwtxHotelRoomSearchService iwtxHotelRoomSearchService, 
                                 X3HotelRoomSearchService x3HotelRoomSearchService) {
        this.iwtxHotelRoomSearchService = iwtxHotelRoomSearchService;
        this.x3HotelRoomSearchService = x3HotelRoomSearchService;
    }

    /**
     * Search for hotel rooms based on the provided criteria
     * @deprecated Use /api/hotel-rooms/search endpoint instead
     * 
     * @param request Hotel room search request
     * @return Hotel room search response
     */
    @Deprecated
    public HotelRoomSearchResponse searchHotelRooms(HotelRoomSearchRequest request) throws Exception {
        logger.warn("Using deprecated HotelRoomSearchService. Please migrate to /api/hotel-rooms/search endpoint");
        logger.info("Processing hotel room search for API ID: {}", request.getApiId());

        // Validate request
        validateSearchRequest(request);

        // Route to appropriate service based on apiId - let exceptions propagate to exception handlers
        HotelRoomSearchServiceInterface service = getServiceByApiId(request.getApiId());
        return service.searchHotelRooms(request);
    }

    /**
     * Get the appropriate service implementation based on API ID
     * 
     * @param apiId The API provider ID (12 = IWTX, 15 = X3)
     * @return The corresponding service implementation
     * @throws IllegalArgumentException if API ID is not supported
     */
    private HotelRoomSearchServiceInterface getServiceByApiId(Integer apiId) {
        if (apiId == null) {
            throw new IllegalArgumentException("API ID is required");
        }
        
        switch (apiId) {
            case 11: // Legacy IWTX API ID
            case 12: // New IWTX API ID
                logger.info("Routing request to IWTX service");
                return iwtxHotelRoomSearchService;
            case 15: // X3 API ID
                logger.info("Routing request to X3 service");
                return x3HotelRoomSearchService;
            default:
                throw new IllegalArgumentException("Unsupported API ID: " + apiId + 
                    ". Supported values are 11/12 (IWTX) and 15 (X3)");
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
