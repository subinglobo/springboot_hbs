package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotelSearchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelIwtxSearchRequest {
	
	@XmlElement
	private String OutputFormat;
	
	@XmlElement
	private ProfileiwtxSearch Profile;
	
	@XmlElement
	private SearchCriteriaIwtxSearch SearchCriteria;
	
	
	public String getOutputFormat() {
		return OutputFormat;
	}
	public void setOutputFormat(String outputFormat) {
		OutputFormat = outputFormat;
	}
	
	public ProfileiwtxSearch getProfile() {
		return Profile;
	}
	public void setProfile(ProfileiwtxSearch profile) {
		Profile = profile;
	}
	public SearchCriteriaIwtxSearch getSearchCriteria() {
		return SearchCriteria;
	}
	public void setSearchCriteria(SearchCriteriaIwtxSearch searchCriteria) {
		SearchCriteria = searchCriteria;
	}
	@Override
	public String toString() {
		return "HotelSearchRequest [OutputFormat=" + OutputFormat + ", Profile=" + Profile + ", SearchCriteria="
				+ SearchCriteria + "]";
	}
	
	

}
