package com.choosenfly.hotelbookingsystem.masters.dto;

public class CityMappingSearchDTO {

	private String apiProvider ;		//platform
	
	private String apiCountryId ;
	
	private String apiCityId  ;

	public String getApiProvider() {
		return apiProvider;
	}

	public void setApiProvider(String apiProvider) {
		this.apiProvider = apiProvider;
	}

	public String getApiCountryId() {
		return apiCountryId;
	}

	public void setApiCountryId(String apiCountryId) {
		this.apiCountryId = apiCountryId;
	}

	public String getApiCityId() {
		return apiCityId;
	}

	public void setApiCityId(String apiCityId) {
		this.apiCityId = apiCityId;
	}

	@Override
	public String toString() {
		return "CityMappingSearchDTO [apiProvider=" + apiProvider + ", apiCountryId=" + apiCountryId + ", apiCityId="
				+ apiCityId + "]";
	}

	

	

	
	
	

}
