package com.choosenfly.hotelbookingsystem.inventory.dto;

import java.util.List;

public class HotelMailCentreDTO {

	
	private Long hotelContactDetailsId;
	
	private List<Long> mailCentreIds;

	public Long getHotelContactDetailsId() {
		return hotelContactDetailsId;
	}

	public void setHotelContactDetailsId(Long hotelContactDetailsId) {
		this.hotelContactDetailsId = hotelContactDetailsId;
	}

	public List<Long> getMailCentreIds() {
		return mailCentreIds;
	}

	public void setMailCentreIds(List<Long> mailCentreIds) {
		this.mailCentreIds = mailCentreIds;
	}

	@Override
	public String toString() {
		return "HotelMailCentreDTO [hotelContactDetailsId=" + hotelContactDetailsId + ", mailCentreIds=" + mailCentreIds
				+ "]";
	}
	
	
	
}
