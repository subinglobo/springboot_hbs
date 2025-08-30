package com.choosenfly.hotelbookingsystem.inventory.dto.staypay;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StaypayValidityDTO {

    private Long promoValidityId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityTo;
    
    private int deleted;
    
    private String isType;   // "V" or "B"

    

	

	public Long getPromoValidityId() {
		return promoValidityId;
	}

	public void setPromoValidityId(Long promoValidityId) {
		this.promoValidityId = promoValidityId;
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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}
	

	@Override
	public String toString() {
		return "StaypayValidityDTO [promoValidityId=" + promoValidityId + ", validityFrom=" + validityFrom
				+ ", validityTo=" + validityTo + ", deleted=" + deleted + ", isType=" + isType + "]";
	}
    
    
}
