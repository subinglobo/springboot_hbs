package com.choosenfly.hotelbookingsystem.registration.employee.enitities;

import java.time.LocalDateTime;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="employee_contact_details")
public class EmployeeContactDetails extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_contact_id")
    private Long employeeContactId;
    
    private String email;
    
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    private String zipcode;

    @Column(name = "fax_number")
    private String faxNumber;
    
    @Column(name = "telex_number")
    private String telexNumber;
    

    private String address;

    // 🔗 One-to-One relation
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

	public Long getEmployeeContactId() {
		return employeeContactId;
	}

	public void setEmployeeContactId(Long employeeContactId) {
		this.employeeContactId = employeeContactId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getTelexNumber() {
		return telexNumber;
	}

	public void setTelexNumber(String telexNumber) {
		this.telexNumber = telexNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeContactDetails [employeeContactId=" + employeeContactId + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", zipcode=" + zipcode + ", faxNumber=" + faxNumber + ", telexNumber=" + telexNumber
				+ ", address=" + address + ", employee=" + employee + "]";
	}
    
    
    
    
    
    
}
