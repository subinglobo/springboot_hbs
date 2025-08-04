package com.choosenfly.hotelbookingsystem.inventory.minimumlength.dto;

import java.util.List;

public class MinimumLengthDTO {

	
	private Long id;
	private Long hotelId;
	private Long marketTypeId;
	private List<MinimumLengthValidityDTO> validityPeriods;
	private List<MinimumLengthStayDTO> hotelRooms;
	private Boolean deleted;
	private Boolean live;
	private Boolean validity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public Long getMarketTypeId() {
		return marketTypeId;
	}
	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}
	public List<MinimumLengthValidityDTO> getValidityPeriods() {
		return validityPeriods;
	}
	public void setValidityPeriods(List<MinimumLengthValidityDTO> validityPeriods) {
		this.validityPeriods = validityPeriods;
	}
	public List<MinimumLengthStayDTO> getHotelRooms() {
		return hotelRooms;
	}
	public void setHotelRooms(List<MinimumLengthStayDTO> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Boolean getLive() {
		return live;
	}
	public void setLive(Boolean live) {
		this.live = live;
	}
	public Boolean getValidity() {
		return validity;
	}
	public void setValidity(Boolean validity) {
		this.validity = validity;
	}
	@Override
	public String toString() {
		return "MinimumLengthDTO [id=" + id + ", hotelId=" + hotelId + ", marketTypeId=" + marketTypeId
				+ ", validityPeriods=" + validityPeriods + ", hotelRooms=" + hotelRooms + ", deleted=" + deleted
				+ ", live=" + live + ", validity=" + validity + "]";
	}
	
	
	
}
