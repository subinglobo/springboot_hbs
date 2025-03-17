package com.choosenfly.hotelbookingsystem.dto;

public class HotelTermsAndConditionsDTO {
	
	private Long id;
	private Long hotelId; // Reference to Hotel
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "HotelTermsAndConditionsDTO [id=" + id + ", hotelId=" + hotelId + ", description=" + description + "]";
	}
}