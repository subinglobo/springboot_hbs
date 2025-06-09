package com.choosenfly.hotelbookingsystem.service.hotel.search;

import java.util.List;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;

public interface HotelSearchApiCaller {

	String getApiKey();
	
    List<HotelSearchResult> callApi(HotelSearchRequest request);
	
}
