package com.choosenfly.hotelbookingsystem.inventory.occupancy.dto;

public class ListOccupanyDTO {

	private Long occupancyId;
	
	private String hotelName;
	
	private String MarketTypeName;
	
	private Boolean isLive;

	public Long getOccupancyId() {
		return occupancyId;
	}

	public void setOccupancyId(Long occupancyId) {
		this.occupancyId = occupancyId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getMarketTypeName() {
		return MarketTypeName;
	}

	public void setMarketTypeName(String marketTypeName) {
		MarketTypeName = marketTypeName;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	@Override
	public String toString() {
		return "ListOccupanyDTO [occupancyId=" + occupancyId + ", hotelName=" + hotelName + ", MarketTypeName="
				+ MarketTypeName + ", isLive=" + isLive + "]";
	}
	
	
	
}
