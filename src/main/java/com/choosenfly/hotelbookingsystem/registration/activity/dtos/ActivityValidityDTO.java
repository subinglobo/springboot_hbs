package com.choosenfly.hotelbookingsystem.registration.activity.dtos;

public class ActivityValidityDTO {

    private Long validityId;
    
    private String validityFrom; // keep as String if you receive "10/12/2025", else use LocalDate
    
    private String validityTo;

	public Long getValidityId() {
		return validityId;
	}

	public void setValidityId(Long validityId) {
		this.validityId = validityId;
	}

	public String getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
	}

	public String getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(String validityTo) {
		this.validityTo = validityTo;
	}

	@Override
	public String toString() {
		return "ActivityValidityDTO [validityId=" + validityId + ", validityFrom=" + validityFrom + ", validityTo="
				+ validityTo + "]";
	}
    
    
    
    
}
