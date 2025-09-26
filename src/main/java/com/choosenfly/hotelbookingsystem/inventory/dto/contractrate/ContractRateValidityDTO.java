package com.choosenfly.hotelbookingsystem.inventory.dto.contractrate;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContractRateValidityDTO {
	
	private Long contractValidityId;

    private String validityFrom;
    
    private String validityTo;

    





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







	public Long getContractValidityId() {
		return contractValidityId;
	}







	public void setContractValidityId(Long contractValidityId) {
		this.contractValidityId = contractValidityId;
	}







	@Override
	public String toString() {
		return "ContractRateValidityDTO [contractValidityId=" + contractValidityId + ", validityFrom=" + validityFrom
				+ ", validityTo=" + validityTo + "]";
	}
    
    
}
