package com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.common.HotelRoomSearchServiceInterface;
import com.choosenfly.hotelbookingsystem.api.hotelroom.mapper.IwtxResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IWTX implementation of hotel room search service
 */
@Service
public class IwtxHotelRoomSearchService implements HotelRoomSearchServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(IwtxHotelRoomSearchService.class);

    private final IwtxApiService iwtxApiService;
    private final IwtxResponseMapper iwtxResponseMapper;

    @Autowired
    public IwtxHotelRoomSearchService(IwtxApiService iwtxApiService, IwtxResponseMapper iwtxResponseMapper) {
        this.iwtxApiService = iwtxApiService;
        this.iwtxResponseMapper = iwtxResponseMapper;
    }

    @Override
    public HotelRoomSearchResponse searchHotelRooms(HotelRoomSearchRequest request) throws Exception {
        logger.info("Searching hotel rooms via IWTX API for hotel code: {}", request.getHotelCode());

        try {
            // Call IWTX API
            var iwtxResponse = iwtxApiService.searchHotelRooms(request);
            
            if (iwtxResponse == null) {
                logger.error("Received null response from IWTX API");
                throw new RuntimeException("No response received from IWTX API");
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
            throw new RuntimeException("IWTX API call failed: " + e.getMessage(), e);
        }
    }
}
