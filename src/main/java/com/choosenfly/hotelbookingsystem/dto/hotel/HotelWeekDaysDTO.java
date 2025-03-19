package com.choosenfly.hotelbookingsystem.dto.hotel;

public class HotelWeekDaysDTO {
	private Long id;
	private Long hotelId; // Reference to Hotel
	private Boolean wdSunday;
	private Boolean wdMonday;
	private Boolean wdTuesday;
	private Boolean wdWednesday;
	private Boolean wdThursday;
	private Boolean wdFriday;
	private Boolean wdSaturday;
	
	private Boolean wedSunday;
	private Boolean wedMonday;
	private Boolean wedTuesday;
	private Boolean wedWednesday;
	private Boolean wedThursday;
	private Boolean wedFriday;
	private Boolean wedSaturday;

	// Getters and setters
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

	public Boolean getWdSunday() {
		return wdSunday;
	}

	public void setWdSunday(Boolean wdSunday) {
		this.wdSunday = wdSunday;
	}

	public Boolean getWdMonday() {
		return wdMonday;
	}

	public void setWdMonday(Boolean wdMonday) {
		this.wdMonday = wdMonday;
	}

	public Boolean getWdTuesday() {
		return wdTuesday;
	}

	public void setWdTuesday(Boolean wdTuesday) {
		this.wdTuesday = wdTuesday;
	}

	public Boolean getWdWednesday() {
		return wdWednesday;
	}

	public void setWdWednesday(Boolean wdWednesday) {
		this.wdWednesday = wdWednesday;
	}

	public Boolean getWdThursday() {
		return wdThursday;
	}

	public void setWdThursday(Boolean wdThursday) {
		this.wdThursday = wdThursday;
	}

	public Boolean getWdFriday() {
		return wdFriday;
	}

	public void setWdFriday(Boolean wdFriday) {
		this.wdFriday = wdFriday;
	}

	public Boolean getWdSaturday() {
		return wdSaturday;
	}

	public void setWdSaturday(Boolean wdSaturday) {
		this.wdSaturday = wdSaturday;
	}

	public Boolean getWedSunday() {
		return wedSunday;
	}

	public void setWedSunday(Boolean wedSunday) {
		this.wedSunday = wedSunday;
	}

	public Boolean getWedMonday() {
		return wedMonday;
	}

	public void setWedMonday(Boolean wedMonday) {
		this.wedMonday = wedMonday;
	}

	public Boolean getWedTuesday() {
		return wedTuesday;
	}

	public void setWedTuesday(Boolean wedTuesday) {
		this.wedTuesday = wedTuesday;
	}

	public Boolean getWedWednesday() {
		return wedWednesday;
	}

	public void setWedWednesday(Boolean wedWednesday) {
		this.wedWednesday = wedWednesday;
	}

	public Boolean getWedThursday() {
		return wedThursday;
	}

	public void setWedThursday(Boolean wedThursday) {
		this.wedThursday = wedThursday;
	}

	public Boolean getWedFriday() {
		return wedFriday;
	}

	public void setWedFriday(Boolean wedFriday) {
		this.wedFriday = wedFriday;
	}

	public Boolean getWedSaturday() {
		return wedSaturday;
	}

	public void setWedSaturday(Boolean wedSaturday) {
		this.wedSaturday = wedSaturday;
	}

	@Override
	public String toString() {
		return "HotelWeekDaysDTO [id=" + id + ", hotelId=" + hotelId + ", wdSunday=" + wdSunday + ", wdMonday="
				+ wdMonday + ", wdTuesday=" + wdTuesday + ", wdWednesday=" + wdWednesday + ", wdThursday=" + wdThursday
				+ ", wdFriday=" + wdFriday + ", wdSaturday=" + wdSaturday + ", wedSunday=" + wedSunday + ", wedMonday="
				+ wedMonday + ", wedTuesday=" + wedTuesday + ", wedWednesday=" + wedWednesday + ", wedThursday="
				+ wedThursday + ", wedFriday=" + wedFriday + ", wedSaturday=" + wedSaturday + "]";
	}
}
