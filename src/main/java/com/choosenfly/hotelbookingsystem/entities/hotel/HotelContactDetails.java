package com.choosenfly.hotelbookingsystem.entities.hotel;

import java.util.List;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;
import com.choosenfly.hotelbookingsystem.entities.hotel.linked.LinkedHotelContactDetailsMailType;
import com.choosenfly.hotelbookingsystem.entities.master.MasterContactType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_contact_details")
public class HotelContactDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "contact_type_id")
    private MasterContactType contactType;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "tele_number")
    private String teleNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @OneToMany(mappedBy = "hotelContactDetails", cascade = CascadeType.ALL ,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LinkedHotelContactDetailsMailType> mailType;
    
    

	public List<LinkedHotelContactDetailsMailType> getMailType() {
		return mailType;
	}

	public void setMailType(List<LinkedHotelContactDetailsMailType> mailType) {
		this.mailType = mailType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}



	public MasterContactType getContactType() {
		return contactType;
	}

	public void setContactType(MasterContactType contactType) {
		this.contactType = contactType;
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
		return "HotelContactDetails [id=" + id + ", hotel=" + hotel + ", contactType=" + contactType
				+ ", contactPerson=" + contactPerson + ", personalEmail=" + personalEmail + ", teleNumber=" + teleNumber
				+ ", mobileNumber=" + mobileNumber + "]";
	}

	

	


}