package com.choosenfly.hotelbookingsystem.api.x3.dto.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotelSearchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelX3SearchRequest {
	
	@XmlElement
	private String OutputFormat;
	
	@XmlElement
	private ProfileX3Search Profile;
	
	@XmlElement
	private SearchCriteriaX3Search SearchCriteria;
	
	
	public String getOutputFormat() {
		return OutputFormat;
	}
	public void setOutputFormat(String outputFormat) {
		OutputFormat = outputFormat;
	}
	
	public ProfileX3Search getProfile() {
		return Profile;
	}
	public void setProfile(ProfileX3Search profile) {
		Profile = profile;
	}
	public SearchCriteriaX3Search getSearchCriteria() {
		return SearchCriteria;
	}
	public void setSearchCriteria(SearchCriteriaX3Search searchCriteria) {
		SearchCriteria = searchCriteria;
	}
	@Override
	public String toString() {
		return "HotelX3SearchRequest [OutputFormat=" + OutputFormat + ", Profile=" + Profile + ", SearchCriteria="
				+ SearchCriteria + "]";
	}
	
	

}
