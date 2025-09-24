package com.choosenfly.hotelbookingsystem.registration.cab.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cab_provider")
public class CabProvider extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabprovider_id")
    private Long cabProviderId;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "emailid")
    private String emailId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_active")
    private Boolean isActive;

    // One provider can have many cabs
    @OneToMany(mappedBy = "cabProvider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cab> cabs;
    
    @OneToMany(mappedBy = "cabProvider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CabRates>cabRates;

	public Long getCabProviderId() {
		return cabProviderId;
	}

	public void setCabProviderId(Long cabProviderId) {
		this.cabProviderId = cabProviderId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Cab> getCabs() {
		return cabs;
	}

	public void setCabs(List<Cab> cabs) {
		this.cabs = cabs;
	}

	public List<CabRates> getCabRates() {
		return cabRates;
	}

	public void setCabRates(List<CabRates> cabRates) {
		this.cabRates = cabRates;
	}

	
	@Override
	public String toString() {
		return "CabProvider [cabProviderId=" + cabProviderId + ", providerName=" + providerName + ", contactPerson="
				+ contactPerson + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", isActive=" + isActive
				+ ", cabs=" + cabs + ", cabRates=" + cabRates + "]";
	}
    
    
    
}
