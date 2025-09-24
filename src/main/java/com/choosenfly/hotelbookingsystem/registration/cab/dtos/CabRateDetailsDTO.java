package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

public class CabRateDetailsDTO {

	private Integer minpax; // "1"

	private Integer maxpax; // "6"

	private Long locationId; // "10"

	private Double sicRate; // "1221"

	private Double privateRate; // "2232"

	private boolean luggage; // true

	private String hourDetails; // ""

	private Long cabRatesdetailsId; // "8" or ""

	private String travelType;

	

	public Integer getMinpax() {
		return minpax;
	}



	public void setMinpax(Integer minpax) {
		this.minpax = minpax;
	}



	public Integer getMaxpax() {
		return maxpax;
	}



	public void setMaxpax(Integer maxpax) {
		this.maxpax = maxpax;
	}



	public Long getLocationId() {
		return locationId;
	}



	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}



	public Double getSicRate() {
		return sicRate;
	}



	public void setSicRate(Double sicRate) {
		this.sicRate = sicRate;
	}



	public Double getPrivateRate() {
		return privateRate;
	}



	public void setPrivateRate(Double privateRate) {
		this.privateRate = privateRate;
	}



	public boolean isLuggage() {
		return luggage;
	}



	public void setLuggage(boolean luggage) {
		this.luggage = luggage;
	}



	public String getHourDetails() {
		return hourDetails;
	}



	public void setHourDetails(String hourDetails) {
		this.hourDetails = hourDetails;
	}



	public Long getCabRatesdetailsId() {
		return cabRatesdetailsId;
	}



	public void setCabRatesdetailsId(Long cabRatesdetailsId) {
		this.cabRatesdetailsId = cabRatesdetailsId;
	}



	public String getTravelType() {
		return travelType;
	}



	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}


	
	@Override
	public String toString() {
		return "CabRateDetailsDTO [minpax=" + minpax + ", maxpax=" + maxpax + ", locationId=" + locationId
				+ ", sicRate=" + sicRate + ", privateRate=" + privateRate + ", luggage=" + luggage + ", hourDetails="
				+ hourDetails + ", cabRatesdetailsId=" + cabRatesdetailsId + ", travelType=" + travelType + "]";
	}
	
	
}
