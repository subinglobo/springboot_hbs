package com.choosenfly.hotelbookingsystem.hotel.search.dto;

public class SearchMessage {

	private String searchId;
	private HotelSearchRequest searchRequest;
	private String apiKey;

	// Default constructor for deserialization
	public SearchMessage() {
	}

	public SearchMessage(String searchId, HotelSearchRequest searchRequest, String apiKey) {
		this.searchId = searchId;
		this.searchRequest = searchRequest;
		this.apiKey = apiKey;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public HotelSearchRequest getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(HotelSearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
