package com.choosenfly.hotelbookingsystem.dto.availability;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailabilityValidityDTO {

	private Long id;
	private Long hotelAvailabilityId;
	private LocalDateTime validityFrom;
	private LocalDateTime validityTo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHotelAvailabilityId() {
		return hotelAvailabilityId;
	}
	public void setHotelAvailabilityId(Long hotelAvailabilityId) {
		this.hotelAvailabilityId = hotelAvailabilityId;
	}
	public LocalDateTime getValidityFrom() {
		return validityFrom;
	}
	public void setValidityFrom(LocalDateTime validityFrom) {
		this.validityFrom = validityFrom;
	}
	public LocalDateTime getValidityTo() {
		return validityTo;
	}
	public void setValidityTo(LocalDateTime validityTo) {
		this.validityTo = validityTo;
	}
	@Override
	public String toString() {
		return "AvailabilityValidityDTO [id=" + id + ", hotelAvailabilityId=" + hotelAvailabilityId + ", validityFrom="
				+ validityFrom + ", validityTo=" + validityTo + "]";
	}
	
	
}
