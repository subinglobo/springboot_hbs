package com.choosenfly.hotelbookingsystem.inventory.dto.stopsale;

import java.time.LocalDateTime;

public class StopSaleValidityDTO {

    private Long stopSaleValidityId;
    
    private LocalDateTime validityFrom;
    
    private LocalDateTime validityTo;

    

	public Long getStopSaleValidityId() {
		return stopSaleValidityId;
	}

	public void setStopSaleValidityId(Long stopSaleValidityId) {
		this.stopSaleValidityId = stopSaleValidityId;
	}

	public LocalDateTime getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(LocalDateTime validityFrom) {
		this.validityFrom = validityFrom;
	}

	public LocalDateTime getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(LocalDateTime validityTo) {
		this.validityTo = validityTo;
	}

	@Override
	public String toString() {
		return "StopSaleValidityDTO [stopSaleValidityId=" + stopSaleValidityId + ", validityFrom=" + validityFrom
				+ ", validityTo=" + validityTo + "]";
	}
    
    
}
