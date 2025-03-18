package com.choosenfly.hotelbookingsystem.entities.hotel.linked;

import com.choosenfly.hotelbookingsystem.entities.hotel.HotelContactDetails;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMailType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_contactdetails_mailtype")
public class LinkedHotelContactDetailsMailType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long hotelContactDetailsId;
	
	@ManyToOne
	@JoinColumn(name = "hotel_contactdetails_id")
	private HotelContactDetails hotelContactDetails;
	
	
	@ManyToOne
	@JoinColumn(name = "master_mail_id")
	private MasterMailType masterMailType;



	public LinkedHotelContactDetailsMailType() {
	
	}


	public LinkedHotelContactDetailsMailType(HotelContactDetails hotelContactDetails, MasterMailType masterMailType) {
		// TODO Auto-generated constructor stub
		
		this.hotelContactDetails = hotelContactDetails;
        this.masterMailType = masterMailType;
	}


	public Long getHotelContactDetailsId() {
		return hotelContactDetailsId;
	}


	public void setHotelContactDetailsId(Long hotelContactDetailsId) {
		this.hotelContactDetailsId = hotelContactDetailsId;
	}


	public HotelContactDetails getHotelContactDetails() {
		return hotelContactDetails;
	}


	public void setHotelContactDetails(HotelContactDetails hotelContactDetails) {
		this.hotelContactDetails = hotelContactDetails;
	}


	public MasterMailType getMasterMailType() {
		return masterMailType;
	}


	public void setMasterMailType(MasterMailType masterMailType) {
		this.masterMailType = masterMailType;
	}


	@Override
	public String toString() {
		return "LinkedHotelContactDetailsMailType [hotelContactDetailsId=" + hotelContactDetailsId
				+ ", hotelContactDetails=" + hotelContactDetails + ", masterMailType=" + masterMailType + "]";
	}


	
	
	
	
}
