package com.choosenfly.hotelbookingsystem.api.hotelroom.controller;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.HotelRoomSearchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for hotel room search operations
 * @deprecated Use UnifiedHotelRoomSearchController at /api/hotel-rooms/search instead
 */
@RestController
@RequestMapping("/api/hotel-rooms")
@CrossOrigin(origins = "*")
@Deprecated
public class HotelRoomSearchController {

    private static final Logger logger = LoggerFactory.getLogger(HotelRoomSearchController.class);

    private final HotelRoomSearchService hotelRoomSearchService;

    @Autowired
    public HotelRoomSearchController(HotelRoomSearchService hotelRoomSearchService) {
        this.hotelRoomSearchService = hotelRoomSearchService;
    }

    /**
     * Search for hotel rooms based on criteria
     * @deprecated Use /api/hotel-rooms/search endpoint from UnifiedHotelRoomSearchController instead
     * 
     * @param request Hotel room search request containing search criteria
     * @return ResponseEntity containing hotel room search results
     */
    @PostMapping("/search")
    @Deprecated
    public ResponseEntity<HotelRoomSearchResponse> searchHotelRooms(
            @Valid @RequestBody HotelRoomSearchRequest request) throws Exception {
        
        logger.warn("Using deprecated /api/hotel-rooms/search endpoint. Please migrate to UnifiedHotelRoomSearchController");
        logger.info("Received hotel room search request: {}", request);
        
        // Validate API ID
        if (request.getApiId() == null) {
            logger.error("API ID is required");
            throw new IllegalArgumentException("API ID is required");
        }

        // Perform search using legacy service - let exceptions propagate to exception handlers
        HotelRoomSearchResponse response = hotelRoomSearchService.searchHotelRooms(request);
        
        logger.info("Hotel room search completed successfully. Found {} hotels", 
            response.getHotels() != null ? response.getHotels().size() : 0);
        
        return ResponseEntity.ok(response);
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
