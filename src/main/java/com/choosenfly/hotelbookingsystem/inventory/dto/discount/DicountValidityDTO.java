package com.choosenfly.hotelbookingsystem.inventory.dto.discount;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DicountValidityDTO {

    private Long discountValidityId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityFrom;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date validityTo;
    
    private String isType;

    

	

	public Long getDiscountValidityId() {
		return discountValidityId;
	}

	public void setDiscountValidityId(Long discountValidityId) {
		this.discountValidityId = discountValidityId;
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
    
    
}
