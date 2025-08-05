package com.choosenfly.hotelbookingsystem.masters.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "master_visa_details")
public class MasterVisaInformation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visa_id")
    private Long visaId;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private MasterCountry country;

    @ManyToOne
    @JoinColumn(name = "passport_country_id", nullable = false)
    private MasterCountry PassportCountry;

    @Column(name = "passport_code")
    private String passportCode;

    @Column(name = "visa_description")
    @NotBlank(message = "cannot be null or empty")
    private String visaDescription;

	public Long getVisaId() {
		return visaId;
	}

	public void setVisaId(Long visaId) {
		this.visaId = visaId;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	public MasterCountry getPassportCountry() {
		return PassportCountry;
	}

	public void setPassportCountry(MasterCountry passportCountry) {
		PassportCountry = passportCountry;
	}

	public String getPassportCode() {
		return passportCode;
	}

	public void setPassportCode(String passportCode) {
		this.passportCode = passportCode;
	}

	public String getVisaDescription() {
		return visaDescription;
	}

	public void setVisaDescription(String visaDescription) {
		this.visaDescription = visaDescription;
	}

	@Override
	public String toString() {
		return "MasterVisaInformation [visaId=" + visaId + ", country=" + country + ", PassportCountry="
				+ PassportCountry + ", passportCode=" + passportCode + ", visaDescription=" + visaDescription + "]";
	}
    
    
}
