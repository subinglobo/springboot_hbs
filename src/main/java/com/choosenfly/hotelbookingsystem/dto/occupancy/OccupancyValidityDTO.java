package com.choosenfly.hotelbookingsystem.dto.occupancy;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OccupancyValidityDTO {
	private Long id;
	private Long hotelOccupancyId;
	private LocalDateTime validityFrom;
	private LocalDateTime validityTo;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelOccupancyId() {
		return hotelOccupancyId;
	}

	public void setHotelOccupancyId(Long hotelOccupancyId) {
		this.hotelOccupancyId = hotelOccupancyId;
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
}
