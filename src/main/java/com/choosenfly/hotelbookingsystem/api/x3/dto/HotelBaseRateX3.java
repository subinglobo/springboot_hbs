package com.choosenfly.hotelbookingsystem.api.x3.dto;

public class HotelBaseRateX3 {

	private String hotelCode;
	private Double baseRate;

	
	
	public HotelBaseRateX3(String hotelCode, Double baseRate) {
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
		return "HotelBaseRateX3 [hotelCode=" + hotelCode + ", baseRate=" + baseRate + "]";
	}

}
