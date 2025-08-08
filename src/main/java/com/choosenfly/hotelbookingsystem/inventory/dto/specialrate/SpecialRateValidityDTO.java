package com.choosenfly.hotelbookingsystem.inventory.dto.specialrate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SpecialRateValidityDTO {

    private Long validity_id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityFrom;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityTo;
    
    private String isType;

	public Long getValidity_id() {
		return validity_id;
	}

	public void setValidity_id(Long validity_id) {
		this.validity_id = validity_id;
	}

	

	public Date getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(Date validityFrom) {
		this.validityFrom = validityFrom;
	}

	public Date getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(Date validityTo) {
		this.validityTo = validityTo;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	
	@Override
	public String toString() {
		return "SpecialRateValidityDTO [validity_id=" + validity_id + ", validityFrom=" + validityFrom + ", validityTo="
				+ validityTo + ", isType=" + isType + "]";
	}
    
    
}
