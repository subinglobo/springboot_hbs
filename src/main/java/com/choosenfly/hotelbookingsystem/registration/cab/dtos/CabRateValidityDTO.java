package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

public class CabRateValidityDTO {

	private Long cabValidityId; // "5"

	private String validityFrom; // "31/10/2025"

	private String validityTo; // "10/11/2025"

	

	public Long getCabValidityId() {
		return cabValidityId;
	}

	public void setCabValidityId(Long cabValidityId) {
		this.cabValidityId = cabValidityId;
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
		return "CabRateValidityDTO [cabValidityId=" + cabValidityId + ", validityFrom=" + validityFrom + ", validityTo="
				+ validityTo + "]";
	}
	   
	
}
