package com.choosenfly.hotelbookingsystem.dto.iwtx;

public class HotelBaseRate {

	private String hotelCode;
	private Double baseRate;

	
	
	public HotelBaseRate(String hotelCode, Double baseRate) {
		super();
		this.hotelCode = hotelCode;
		this.baseRate = baseRate;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Double getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(Double baseRate) {
		this.baseRate = baseRate;
	}

	@Override
	public String toString() {
		return "HotelBaseRate [hotelCode=" + hotelCode + ", baseRate=" + baseRate + "]";
	}

}
