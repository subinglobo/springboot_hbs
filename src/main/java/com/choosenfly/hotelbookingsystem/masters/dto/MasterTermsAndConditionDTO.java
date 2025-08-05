package com.choosenfly.hotelbookingsystem.masters.dto;

public class MasterTermsAndConditionDTO {

    private Long termsAndConditionsId;
    
    private Long countryId;

    private String description;
    
    private Integer descriptionType;

    private Long stateId;
    
    private String tagline;
    
    private String termsCode;

	public Long getTermsAndConditionsId() {
		return termsAndConditionsId;
	}

	public void setTermsAndConditionsId(Long termsAndConditionsId) {
		this.termsAndConditionsId = termsAndConditionsId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Integer getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(Integer descriptionType) {
		this.descriptionType = descriptionType;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTermsCode() {
		return termsCode;
	}

	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	}

	@Override
	public String toString() {
		return "MasterTermsAndConditionDTO [termsAndConditionsId=" + termsAndConditionsId + ", countryId=" + countryId
				+ ", description=" + description + ", descriptionType=" + descriptionType + ", stateId=" + stateId
				+ ", tagline=" + tagline + ", termsCode=" + termsCode + "]";
	}
    
    
}
