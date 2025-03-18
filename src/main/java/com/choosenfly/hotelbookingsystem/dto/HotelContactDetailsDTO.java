package com.choosenfly.hotelbookingsystem.dto;

import java.util.List;

public class HotelContactDetailsDTO {
	private Long id;
	private Long hotelId; // Reference to Hotel
	private Long contactTypeId; // Reference to MasterContactType
	private String contactPerson;
	private String personalEmail;
	private String teleNumber;
	private String mobileNumber;
	private List<Long> mailTyIds;

	
	
	// Getters and setters
	public List<Long> getMailTyIds() {
		return mailTyIds;
	}

	public void setMailTyIds(List<Long> mailTyIds) {
		this.mailTyIds = mailTyIds;
	}

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

	public Long getContactTypeId() {
		return contactTypeId;
	}

	public void setContactTypeId(Long contactTypeId) {
		this.contactTypeId = contactTypeId;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getTeleNumber() {
		return teleNumber;
	}

	public void setTeleNumber(String teleNumber) {
		this.teleNumber = teleNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "HotelContactDetailsDTO [id=" + id + ", hotelId=" + hotelId + ", contactTypeId=" + contactTypeId
				+ ", contactPerson=" + contactPerson + ", personalEmail=" + personalEmail + ", teleNumber=" + teleNumber
				+ ", mobileNumber=" + mobileNumber + ", mailTyIds=" + mailTyIds + "]";
	}

	
}