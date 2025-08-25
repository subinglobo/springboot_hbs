package com.choosenfly.hotelbookingsystem.masters.dto;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;

public class ApiCityMappingDTO {

	private Long id;

	private String apiProvider ;		//platform
	
	private Long masterCountryId;

	private Long masterCityId;
	
	private String apiCountryId ;
	
	private String apiCountryCode ;
	
	private String apiCityId  ;
	
	private String apiCityCode ;
	
	private String apiHotelCodeList ;
	
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiProvider() {
		return apiProvider;
	}

	public void setApiProvider(String apiProvider) {
		this.apiProvider = apiProvider;
	}

	public Long getMasterCountryId() {
		return masterCountryId;
	}

	public void setMasterCountryId(Long masterCountryId) {
		this.masterCountryId = masterCountryId;
	}

	public Long getMasterCityId() {
		return masterCityId;
	}

	public void setMasterCityId(Long masterCityId) {
		this.masterCityId = masterCityId;
	}

	public String getApiCountryId() {
		return apiCountryId;
	}

	public void setApiCountryId(String apiCountryId) {
		this.apiCountryId = apiCountryId;
	}

	public String getApiCountryCode() {
		return apiCountryCode;
	}

	public void setApiCountryCode(String apiCountryCode) {
		this.apiCountryCode = apiCountryCode;
	}

	public String getApiCityId() {
		return apiCityId;
	}

	public void setApiCityId(String apiCityId) {
		this.apiCityId = apiCityId;
	}

	public String getApiCityCode() {
		return apiCityCode;
	}

	public void setApiCityCode(String apiCityCode) {
		this.apiCityCode = apiCityCode;
	}

	public String getApiHotelCodeList() {
		return apiHotelCodeList;
	}

	public void setApiHotelCodeList(String apiHotelCodeList) {
		this.apiHotelCodeList = apiHotelCodeList;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "ApiCityMappingDTO [id=" + id + ", apiProvider=" + apiProvider + ", masterCountryId=" + masterCountryId
				+ ", masterCityId=" + masterCityId + ", apiCountryId=" + apiCountryId + ", apiCountryCode="
				+ apiCountryCode + ", apiCityId=" + apiCityId + ", apiCityCode=" + apiCityCode + ", apiHotelCodeList="
				+ apiHotelCodeList + ", isDeleted=" + isDeleted + "]";
	}

	

	
	
	

}
