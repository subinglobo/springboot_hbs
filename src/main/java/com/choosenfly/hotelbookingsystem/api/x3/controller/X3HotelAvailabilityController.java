package com.choosenfly.hotelbookingsystem.api.x3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.HotelAvailabilityRequest;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response.HotelSearchResponse;
import com.choosenfly.hotelbookingsystem.api.x3.service.X3HotelAvailabilityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/x3/hotel")
@Tag(name = "X3 Hotel Availability", description = "APIs for checking hotel room availability and pricing through X3")
public class X3HotelAvailabilityController {

    @Autowired
    private X3HotelAvailabilityService x3HotelAvailabilityService;

    @PostMapping("/availability")
    @Operation(summary = "Check hotel room availability and pricing", 
               description = "Check hotel room availability and pricing from X3 API. Profile credentials are automatically populated from configuration.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved hotel availability"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error or external API failure")
    })
    public ResponseEntity<?> checkHotelAvailability(
            @RequestBody HotelAvailabilityRequest request) {
        
        try {
            HotelSearchResponse response = x3HotelAvailabilityService.checkHotelAvailability(request);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking hotel availability: " + e.getMessage());
        }
    }

    @GetMapping("/availability/sample")
    @Operation(summary = "Get sample hotel availability request", 
               description = "Generate a sample hotel search request for testing purposes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully generated sample request"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    public ResponseEntity<?> getSampleRequest(
            @Parameter(description = "Hotel code to search for", example = "101-1256")
            @RequestParam(defaultValue = "101-1256") String hotelCode,
            
            @Parameter(description = "Check-in date in YYYYMMDD format", example = "20260125")
            @RequestParam(defaultValue = "20260125") String startDate,
            
            @Parameter(description = "Check-out date in YYYYMMDD format", example = "20260126")
            @RequestParam(defaultValue = "20260126") String endDate,
            
            @Parameter(description = "Guest nationality code", example = "AF")
            @RequestParam(defaultValue = "AF") String nationality) {
        
        try {
            HotelSearchRequest sampleRequest = x3HotelAvailabilityService.createSampleRequest(
                hotelCode, startDate, endDate, nationality);
            return ResponseEntity.ok(sampleRequest);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating sample request: " + e.getMessage());
        }
    }

    @PostMapping("/availability/test")
    @Operation(summary = "Test hotel availability with sample data", 
               description = "Test the hotel availability API using sample data for quick testing")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully tested hotel availability"),
        @ApiResponse(responseCode = "500", description = "Internal server error or external API failure")
    })
    public ResponseEntity<?> testHotelAvailability(
            @Parameter(description = "Hotel code to test", example = "101-1256")
            @RequestParam(defaultValue = "101-1256") String hotelCode,
            
            @Parameter(description = "Check-in date in YYYYMMDD format", example = "20260125")
            @RequestParam(defaultValue = "20260125") String startDate,
            
            @Parameter(description = "Check-out date in YYYYMMDD format", example = "20260126")
            @RequestParam(defaultValue = "20260126") String endDate,
            
            @Parameter(description = "Guest nationality code", example = "AF")
            @RequestParam(defaultValue = "AF") String nationality) {
        
        try {
            // Create sample request
            HotelSearchRequest request = x3HotelAvailabilityService.createSampleRequest(
                hotelCode, startDate, endDate, nationality);
            
            // Call availability API
            HotelSearchResponse response = x3HotelAvailabilityService.checkHotelAvailability(request);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error testing hotel availability: " + e.getMessage());
        }
    }
}
