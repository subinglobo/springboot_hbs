package com.choosenfly.hotelbookingsystem.api.hotelroom.service.common;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;

/**
 * Common interface for hotel room search services across different providers
 */
public interface HotelRoomSearchServiceInterface {
    
    /**
     * Search for hotel rooms based on the provided criteria
     * 
     * @param request Hotel room search request containing search criteria
     * @return Hotel room search response with available rooms and pricing
     * @throws Exception if search operation fails
     */
    HotelRoomSearchResponse searchHotelRooms(HotelRoomSearchRequest request) throws Exception;
}
