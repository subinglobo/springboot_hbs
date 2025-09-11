package com.choosenfly.hotelbookingsystem.api.hotelroom.controller;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.common.HotelRoomSearchServiceInterface;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx.IwtxHotelRoomSearchService;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.x3.X3HotelRoomSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * Unified REST Controller for hotel room search operations across multiple providers
 */
@RestController
@RequestMapping("/api/unified/hotel-rooms")
public class UnifiedHotelRoomSearchController {

    private static final Logger logger = LoggerFactory.getLogger(UnifiedHotelRoomSearchController.class);

    private final IwtxHotelRoomSearchService iwtxHotelRoomSearchService;
    private final X3HotelRoomSearchService x3HotelRoomSearchService;

    @Autowired
    public UnifiedHotelRoomSearchController(IwtxHotelRoomSearchService iwtxHotelRoomSearchService, 
                                          X3HotelRoomSearchService x3HotelRoomSearchService) {
        this.iwtxHotelRoomSearchService = iwtxHotelRoomSearchService;
        this.x3HotelRoomSearchService = x3HotelRoomSearchService;
    }

    /**
     * Unified search endpoint for hotel rooms across multiple providers
     * 
     * @param request Hotel room search request containing search criteria and API ID
     * @return ResponseEntity containing hotel room search results
     */
    @PostMapping("/hotel-rooms/search")
    public ResponseEntity<HotelRoomSearchResponse> searchHotelRooms(
            @Valid @RequestBody HotelRoomSearchRequest request) {
        
        logger.info("Received unified hotel room search request: {}", request);
        
        try {
            // Validate API ID
            if (request.getApiId() == null) {
                logger.error("API ID is required");
                return ResponseEntity.badRequest()
                    .body(HotelRoomSearchResponse.error("API ID is required"));
            }

            // Get the appropriate service based on API ID
            HotelRoomSearchServiceInterface service = getServiceByApiId(request.getApiId());
            
            // Perform search
            HotelRoomSearchResponse response = service.searchHotelRooms(request);
            
            if (response.isSuccess()) {
                logger.info("Hotel room search completed successfully. Found {} hotels", 
                    response.getHotels() != null ? response.getHotels().size() : 0);
                return ResponseEntity.ok(response);
            } else {
                logger.error("Hotel room search failed: {}", response.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (IllegalArgumentException e) {
            logger.error("Invalid API ID: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(HotelRoomSearchResponse.error("Invalid API ID: " + e.getMessage()));
        } catch (Exception e) {
            logger.error("Unexpected error during hotel room search", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HotelRoomSearchResponse.error("An unexpected error occurred: " + e.getMessage()));
        }
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
            case 12:
                logger.info("Routing request to IWTX service");
                return iwtxHotelRoomSearchService;
            case 15:
                logger.info("Routing request to X3 service");
                return x3HotelRoomSearchService;
            default:
                throw new IllegalArgumentException("Unsupported API ID: " + apiId + 
                    ". Supported values are 12 (IWTX) and 15 (X3)");
        }
    }
}
