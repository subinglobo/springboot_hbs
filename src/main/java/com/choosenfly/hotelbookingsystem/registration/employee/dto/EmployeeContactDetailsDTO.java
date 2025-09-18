package com.choosenfly.hotelbookingsystem.registration.employee.dto;

import jakarta.validation.constraints.NotBlank;

public class EmployeeContactDetailsDTO {

    private Long contactId;
    private String userType; 

    private String contactPerson;
    @NotBlank(message = "email is required")
    private String email;

    private String telexNumber;
    
    @NotBlank(message = "mobileNumber is required")
    private String mobileNumber;
    private String faxNumber;
    private String address;
    private String zipcode;
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelexNumber() {
		return telexNumber;
	}
	public void setTelexNumber(String telexNumber) {
		this.telexNumber = telexNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return "EmployeeContactDetailsDTO [contactId=" + contactId + ", userType=" + userType + ", contactPerson="
				+ contactPerson + ", email=" + email + ", telexNumber=" + telexNumber + ", mobileNumber=" + mobileNumber
				+ ", faxNumber=" + faxNumber + ", address=" + address + ", zipcode=" + zipcode + "]";
	}
    
    

}
