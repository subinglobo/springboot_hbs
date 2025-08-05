package com.choosenfly.hotelbookingsystem.inventory.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockCheckInAndCheckOutDTO {

	private Long hotelId;
	private Long id;
	private Boolean isCheckin;
	private Boolean isCheckOut;
	private Long marketTypeId;
	private String marketTypeName; // optional, if you want to show the name as well
	private List<BlockCheckinCheckoutValidityDTO> validityList;
	
	

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsCheckin() {
		return isCheckin;
	}

	public void setIsCheckin(Boolean isCheckin) {
		this.isCheckin = isCheckin;
	}

	public Boolean getIsCheckOut() {
		return isCheckOut;
	}

	public void setIsCheckOut(Boolean isCheckOut) {
		this.isCheckOut = isCheckOut;
	}

	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public String getMarketTypeName() {
		return marketTypeName;
	}

	public void setMarketTypeName(String marketTypeName) {
		this.marketTypeName = marketTypeName;
	}

	public List<BlockCheckinCheckoutValidityDTO> getValidityList() {
		return validityList;
	}

	public void setValidityList(List<BlockCheckinCheckoutValidityDTO> validityList) {
		this.validityList = validityList;
	}

	@Override
	public String toString() {
		return "BlockCheckInAndCheckOutDTO [hotelId=" + hotelId + ", id=" + id + ", isCheckin=" + isCheckin
				+ ", isCheckOut=" + isCheckOut + ", marketTypeId=" + marketTypeId + ", marketTypeName=" + marketTypeName
				+ ", validityList=" + validityList + "]";
	}

	

}
