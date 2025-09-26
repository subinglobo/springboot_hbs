package com.choosenfly.hotelbookingsystem.api.hotelroom.controller;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.common.HotelRoomSearchServiceInterface;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.inhouse.InhouseHotelRoomSearchService;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx.IwtxHotelRoomSearchService;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.x3.X3HotelRoomSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * Unified REST Controller for hotel room search operations across multiple providers
 */
@RestController
@RequestMapping("/api/hotel-rooms")
@CrossOrigin(origins = "*")
public class UnifiedHotelRoomSearchController {

    private static final Logger logger = LoggerFactory.getLogger(UnifiedHotelRoomSearchController.class);

    private final IwtxHotelRoomSearchService iwtxHotelRoomSearchService;
    private final X3HotelRoomSearchService x3HotelRoomSearchService;
    private final InhouseHotelRoomSearchService inhouseHotelRoomSearchService;

    @Autowired
    public UnifiedHotelRoomSearchController(IwtxHotelRoomSearchService iwtxHotelRoomSearchService, 
                                          X3HotelRoomSearchService x3HotelRoomSearchService,
                                          InhouseHotelRoomSearchService inhouseHotelRoomSearchService) {
        this.iwtxHotelRoomSearchService = iwtxHotelRoomSearchService;
        this.x3HotelRoomSearchService = x3HotelRoomSearchService;
        this.inhouseHotelRoomSearchService = inhouseHotelRoomSearchService;
    }

    /**
     * Unified search endpoint for hotel rooms across multiple providers
     * 
     * @param request Hotel room search request containing search criteria and API ID
     * @return ResponseEntity containing hotel room search results
     */
    @PostMapping("/search")
    public ResponseEntity<HotelRoomSearchResponse> searchHotelRooms(
            @Valid @RequestBody HotelRoomSearchRequest request) throws Exception {
        
        logger.info("Received unified hotel room search request: {}", request);
        
        // Validate API ID
        if (request.getApiId() == null) {
            logger.error("API ID is required");
            throw new IllegalArgumentException("API ID is required");
        }

        // Get the appropriate service based on API ID
        HotelRoomSearchServiceInterface service = getServiceByApiId(request.getApiId());
        
        // Perform search - let exceptions propagate to exception handlers
        HotelRoomSearchResponse response = service.searchHotelRooms(request);
        
        logger.info("Hotel room search completed successfully. Found {} hotels", 
            response.getHotels() != null ? response.getHotels().size() : 0);
        
        return ResponseEntity.ok(response);
    }

    
    /**
     * Get the appropriate service implementation based on API ID
     * 
     * @param apiId The API provider ID (1 = In-house, 12 = IWTX, 15 = X3)
     * @return The corresponding service implementation
     * @throws IllegalArgumentException if API ID is not supported
     */
    private HotelRoomSearchServiceInterface getServiceByApiId(Integer apiId) {
        if (apiId == null) {
            throw new IllegalArgumentException("API ID is required");
        }
        
        switch (apiId) {
            case 1:
                logger.info("Routing request to In-house service");
                return inhouseHotelRoomSearchService;
            case 12:
                logger.info("Routing request to IWTX service");
                return iwtxHotelRoomSearchService;
            case 15:
                logger.info("Routing request to X3 service");
                return x3HotelRoomSearchService;
            default:
                throw new IllegalArgumentException("Unsupported API ID: " + apiId + 
                    ". Supported values are 1 (In-house), 12 (IWTX) and 15 (X3)");
        }
    }
}
