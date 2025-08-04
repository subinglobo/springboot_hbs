package com.choosenfly.hotelbookingsystem.dto.masters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MasterVisaInformationDTO {

    private Long visaId;
    
    @NotNull(message = "countryId cannot be null or empty")
    private Long countryId;
    
    @NotNull(message = "passport countryId cannot be null or empty")
    private Long paxPassportCountryId;

    
    private String passportCode;
    
    @NotBlank(message = "decription cannot be null or empty")
    private String visaDescription;

	public Long getVisaId() {
		return visaId;
	}

	public void setVisaId(Long visaId) {
		this.visaId = visaId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getPaxPassportCountryId() {
		return paxPassportCountryId;
	}

	public void setPaxPassportCountryId(Long paxPassportCountryId) {
		this.paxPassportCountryId = paxPassportCountryId;
	}

	public String getPassportCode() {
		return passportCode;
	}

	public void setPassportCode(String passportCode) {
		this.passportCode = passportCode;
	}

	public String getVisaDescription() {
		return visaDescription;
	}

	public void setVisaDescription(String visaDescription) {
		this.visaDescription = visaDescription;
	}

	@Override
	public String toString() {
		return "MasterVisaInformationDTO [visaId=" + visaId + ", countryId=" + countryId + ", paxPassportCountryId="
				+ paxPassportCountryId + ", passportCode=" + passportCode + ", visaDescription=" + visaDescription
				+ "]";
	}
    
    
}
