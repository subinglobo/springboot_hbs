package com.choosenfly.hotelbookingsystem.dto.minimumlength;

public class MinimumLengthResponseDTO {

	
	private Long minimumLengthId;
	
	private Long hotelId;
	
	private String hotelName;
	
	private Long marketId;
	
	private String marketName;
	
	private Boolean status;

	public Long getMinimumLengthId() {
		return minimumLengthId;
	}

	public void setMinimumLengthId(Long minimumLengthId) {
		this.minimumLengthId = minimumLengthId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MinimumLengthResponseDTO [minimumLengthId=" + minimumLengthId + ", hotelId=" + hotelId + ", hotelName="
				+ hotelName + ", marketId=" + marketId + ", marketName=" + marketName + ", status=" + status + "]";
	}
	
	
}
