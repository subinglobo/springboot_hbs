package com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CompulsorySupplyValidityDTO {

    private Long supplymentValidityId;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityFrom;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityTo;

    
    
	public Long getSupplymentValidityId() {
		return supplymentValidityId;
	}



	public void setSupplymentValidityId(Long supplymentValidityId) {
		this.supplymentValidityId = supplymentValidityId;
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

	

	@Override
	public String toString() {
		return "CompulsorySupplyValidityDTO [supplymentValidityId=" + supplymentValidityId + ", validityFrom="
				+ validityFrom + ", validityTo=" + validityTo + "]";
	}
    
    
}
