package com.choosenfly.hotelbookingsystem.api.hotelroom.service.x3;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.common.HotelRoomSearchServiceInterface;
import com.choosenfly.hotelbookingsystem.api.hotelroom.mapper.X3ResponseMapper;
import com.choosenfly.hotelbookingsystem.api.x3.exception.X3ApiException;
import com.choosenfly.hotelbookingsystem.api.x3.exception.X3NoAvailabilityException;
import com.choosenfly.hotelbookingsystem.api.x3.exception.X3ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * X3 implementation of hotel room search service
 */
@Service
public class X3HotelRoomSearchService implements HotelRoomSearchServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(X3HotelRoomSearchService.class);

    private final X3ApiService x3ApiService;
    private final X3ResponseMapper x3ResponseMapper;

    @Autowired
    public X3HotelRoomSearchService(X3ApiService x3ApiService, X3ResponseMapper x3ResponseMapper) {
        this.x3ApiService = x3ApiService;
        this.x3ResponseMapper = x3ResponseMapper;
    }

    @Override
    public HotelRoomSearchResponse searchHotelRooms(HotelRoomSearchRequest request) throws Exception {
        logger.info("Searching hotel rooms via X3 API for hotel code: {}", request.getHotelCode());

        try {
            // Call X3 API
            var x3Response = x3ApiService.searchHotelRooms(request);
            
            if (x3Response == null) {
                logger.error("Received null response from X3 API");
                throw new RuntimeException("No response received from X3 API");
            }

            // Map X3 response to common format using X3 mapper
            var hotels = x3ResponseMapper.mapToHotelResponses(x3Response, request);
            
            if (hotels == null || hotels.isEmpty()) {
                logger.info("No hotels found for the search criteria");
                return HotelRoomSearchResponse.success(hotels);
            }

            logger.info("Successfully mapped {} hotels from X3 response", hotels.size());
            return HotelRoomSearchResponse.success(hotels);

        } catch (X3NoAvailabilityException e) {
            // Re-throw X3 no availability exception without wrapping
            throw e;
        } catch (X3ConfigurationException e) {
            // Re-throw X3 configuration exception without wrapping
            throw e;
        } catch (X3ApiException e) {
            // Re-throw X3 API exception without wrapping
            throw e;
        } catch (Exception e) {
            logger.error("Error calling X3 API", e);
            throw new RuntimeException("X3 API call failed: " + e.getMessage(), e);
        }
    }
}
