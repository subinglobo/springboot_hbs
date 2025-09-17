package com.choosenfly.hotelbookingsystem.registration.employee.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDTO {

    private Long employeeId;
    
    @NotBlank(message = "employeeCode is required")
    private String employeeCode;
    @NotBlank(message = "designation is required")
    private String designation;
    private Boolean active;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    private String markupType;
    private MultipartFile employeeProfile; // Or byte[] if binary data
    
    private String imagePath;
    private Date dob;
    // Optionally link contact details if you want
    @Valid
    @NotNull(message = "Contact details are required")
    private EmployeeContactDetailsDTO contactDetails;
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMarkupType() {
		return markupType;
	}
	public void setMarkupType(String markupType) {
		this.markupType = markupType;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public EmployeeContactDetailsDTO getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(EmployeeContactDetailsDTO contactDetails) {
		this.contactDetails = contactDetails;
	}
	
	
	public MultipartFile getEmployeeProfile() {
		return employeeProfile;
	}
	public void setEmployeeProfile(MultipartFile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}
	
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", employeeCode=" + employeeCode + ", designation="
				+ designation + ", active=" + active + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", markupType=" + markupType + ", employeeProfile=" + employeeProfile + ", imagePath=" + imagePath
				+ ", dob=" + dob + ", contactDetails=" + contactDetails + "]";
	}
    
    
}
