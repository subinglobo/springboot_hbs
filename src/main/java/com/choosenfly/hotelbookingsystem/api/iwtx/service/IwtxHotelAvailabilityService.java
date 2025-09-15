package com.choosenfly.hotelbookingsystem.api.iwtx.service;

import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.HotelAvailabilityRequest;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response.HotelSearchResponse;

public interface IwtxHotelAvailabilityService {
    
    /**
     * Check hotel room availability and pricing from IWTX API
     * 
     * @param request The hotel search request containing room details, dates, and hotel information
     * @return HotelSearchResponse containing availability and pricing information
     * @throws Exception if API call fails or response cannot be processed
     */
    HotelSearchResponse checkHotelAvailability(HotelSearchRequest request) throws Exception;
    
    /**
     * Check hotel room availability using simplified request (profile auto-populated)
     * 
     * @param request The simplified hotel availability request
     * @return HotelSearchResponse containing availability and pricing information
     * @throws Exception if API call fails or response cannot be processed
     */
    HotelSearchResponse checkHotelAvailability(HotelAvailabilityRequest request) throws Exception;
    
    /**
     * Create a sample hotel search request for testing purposes
     * 
     * @param hotelCode The hotel code to search for
     * @param startDate Check-in date in YYYYMMDD format
     * @param endDate Check-out date in YYYYMMDD format
     * @param nationality Guest nationality code
     * @return Sample HotelSearchRequest
     */
    HotelSearchRequest createSampleRequest(String hotelCode, String startDate, String endDate, String nationality);
}
