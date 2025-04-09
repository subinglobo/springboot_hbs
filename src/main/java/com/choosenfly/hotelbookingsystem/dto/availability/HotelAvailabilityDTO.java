package com.choosenfly.hotelbookingsystem.dto.availability;

import java.util.List;
import java.util.Set;

import com.choosenfly.hotelbookingsystem.entities.availability.HotelAvailability.AvailabilityType;
import com.choosenfly.hotelbookingsystem.entities.availability.HotelAvailability.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelAvailabilityDTO {

	private Long id;

	private Long hotelId;

	private Long marketTypeId;
	private Long hotelRoomId;
	private Integer noOfRooms;
	private Integer releaseDay;
	private AvailabilityType availabilityType;
	private List<AvailabilityValidityDTO> availabilityValidities;
	private Set<DayOfWeek> checkinAllowedDays;

	public Set<DayOfWeek> getCheckinAllowedDays() {
		return checkinAllowedDays;
	}

	public void setCheckinAllowedDays(Set<DayOfWeek> checkinAllowedDays) {
		this.checkinAllowedDays = checkinAllowedDays;
	}

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

	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public Long getHotelRoomId() {
		return hotelRoomId;
	}

	public void setHotelRoomId(Long hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}

	public Integer getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getReleaseDay() {
		return releaseDay;
	}

	public void setReleaseDay(Integer releaseDay) {
		this.releaseDay = releaseDay;
	}

	public AvailabilityType getAvailabilityType() {
		return availabilityType;
	}

	public void setAvailabilityType(AvailabilityType availabilityType) {
		this.availabilityType = availabilityType;
	}

	public List<AvailabilityValidityDTO> getAvailabilityValidities() {
		return availabilityValidities;
	}

	public void setAvailabilityValidities(List<AvailabilityValidityDTO> availabilityValidities) {
		this.availabilityValidities = availabilityValidities;
	}

	@Override
	public String toString() {
		return "HotelAvailabilityDTO [id=" + id + ", hotelId=" + hotelId + ", marketTypeId=" + marketTypeId
				+ ", hotelRoomId=" + hotelRoomId + ", noOfRooms=" + noOfRooms + ", releaseDay=" + releaseDay
				+ ", availabilityType=" + availabilityType + ", availabilityValidities=" + availabilityValidities
				+ ", checkinAllowedDays=" + checkinAllowedDays + "]";
	}

}
