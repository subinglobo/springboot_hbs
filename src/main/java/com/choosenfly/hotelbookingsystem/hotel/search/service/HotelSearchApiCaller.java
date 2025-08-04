package com.choosenfly.hotelbookingsystem.hotel.search.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;

public interface HotelSearchApiCaller {

	String getApiKey();
	
    List<HotelSearchResult> callApi(HotelSearchRequest request);
	
}
