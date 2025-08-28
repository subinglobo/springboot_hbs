package com.choosenfly.hotelbookingsystem.api.hotelroom.controller;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.HotelRoomSearchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for hotel room search operations
 */
@RestController
@RequestMapping("/api/hotel-rooms")
@CrossOrigin(origins = "*")
public class HotelRoomSearchController {

    private static final Logger logger = LoggerFactory.getLogger(HotelRoomSearchController.class);

    private final HotelRoomSearchService hotelRoomSearchService;

    @Autowired
    public HotelRoomSearchController(HotelRoomSearchService hotelRoomSearchService) {
        this.hotelRoomSearchService = hotelRoomSearchService;
    }

    /**
     * Search for hotel rooms based on criteria
     * 
     * @param request Hotel room search request containing search criteria
     * @return ResponseEntity containing hotel room search results
     */
    @PostMapping("/search")
    public ResponseEntity<HotelRoomSearchResponse> searchHotelRooms(
            @Valid @RequestBody HotelRoomSearchRequest request) {
        
        logger.info("Received hotel room search request: {}", request);
        
        try {
            // Validate API ID
            if (request.getApiId() == null) {
                logger.error("API ID is required");
                return ResponseEntity.badRequest()
                    .body(HotelRoomSearchResponse.error("API ID is required"));
            }

            // Check if API is supported
            if (!request.getApiId().equals(11)) {
                logger.error("API ID {} is not implemented", request.getApiId());
                return ResponseEntity.badRequest()
                    .body(HotelRoomSearchResponse.error("API not implemented. Only API ID 11 (IWTX) is currently supported."));
            }

            // Perform search
            HotelRoomSearchResponse response = hotelRoomSearchService.searchHotelRooms(request);
            
            if (response.isSuccess()) {
                logger.info("Hotel room search completed successfully. Found {} hotels", 
                    response.getHotels() != null ? response.getHotels().size() : 0);
                return ResponseEntity.ok(response);
            } else {
                logger.error("Hotel room search failed: {}", response.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (IllegalArgumentException e) {
            logger.error("Invalid request parameters: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(HotelRoomSearchResponse.error("Invalid request: " + e.getMessage()));
        } catch (Exception e) {
            logger.error("Unexpected error during hotel room search", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HotelRoomSearchResponse.error("An unexpected error occurred: " + e.getMessage()));
        }
    }

    /**
     * Health check endpoint
     * 
     * @return ResponseEntity indicating service health
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Hotel Room Search Service is running");
    }
}
