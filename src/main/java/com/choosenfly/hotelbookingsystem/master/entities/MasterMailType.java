package com.choosenfly.hotelbookingsystem.master.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.hotel.linked.entities.LinkedHotelContactDetailsMailType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_mailtype")
public class MasterMailType {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "mail_type")
	private String mailType;
	

	@OneToMany(mappedBy = "masterMailType")
    private List<LinkedHotelContactDetailsMailType> hotelContacts;

	
	
	public List<LinkedHotelContactDetailsMailType> getHotelContacts() {
		return hotelContacts;
	}

	public void setHotelContacts(List<LinkedHotelContactDetailsMailType> hotelContacts) {
		this.hotelContacts = hotelContacts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	@Override
	public String toString() {
		return "MasterMailType [id=" + id + ", mailType=" + mailType + ", hotelContacts=" + hotelContacts + "]";
	}

	
	
}
