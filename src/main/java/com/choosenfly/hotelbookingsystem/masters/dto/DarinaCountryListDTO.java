package com.choosenfly.hotelbookingsystem.masters.dto;

public class DarinaCountryListDTO {
	
	private Long id;
	
	private String countryCode;
	
	private String countryName;
	
	private Long countryId;
	
	private Long inhouseCountryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getInhouseCountryId() {
		return inhouseCountryId;
	}

	public void setInhouseCountryId(Long inhouseCountryId) {
		this.inhouseCountryId = inhouseCountryId;
	}

	@Override
	public String toString() {
		return "DarinaCountryListDTO [id=" + id + ", countryCode=" + countryCode + ", countryName=" + countryName
				+ ", countryId=" + countryId + ", inhouseCountryId=" + inhouseCountryId + "]";
	}
	
	


}
