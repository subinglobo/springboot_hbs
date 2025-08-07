package com.choosenfly.hotelbookingsystem.inventory.entities;

import java.util.Date;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomType;

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
@Table(name = "hotel_roomType")
public class HotelRoomType extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 11)
	private long hotel_roomType_id;

	@Column(length=11)
	private long hotel_roomcategory_id;
	
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private MasterRoomType roomType;

    
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ContractRateRoom> contractRateRooms ;


	public long getHotel_roomType_id() {
		return hotel_roomType_id;
	}


	public void setHotel_roomType_id(long hotel_roomType_id) {
		this.hotel_roomType_id = hotel_roomType_id;
	}


	public long getHotel_roomcategory_id() {
		return hotel_roomcategory_id;
	}


	public void setHotel_roomcategory_id(long hotel_roomcategory_id) {
		this.hotel_roomcategory_id = hotel_roomcategory_id;
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public MasterRoomType getRoomType() {
		return roomType;
	}


	public void setRoomType(MasterRoomType roomType) {
		this.roomType = roomType;
	}


	public List<ContractRateRoom> getContractRateRooms() {
		return contractRateRooms;
	}


	public void setContractRateRooms(List<ContractRateRoom> contractRateRooms) {
		this.contractRateRooms = contractRateRooms;
	}


	@Override
	public String toString() {
		return "HotelRoomType [hotel_roomType_id=" + hotel_roomType_id + ", hotel_roomcategory_id="
				+ hotel_roomcategory_id + ", hotel=" + hotel + ", roomType=" + roomType + ", contractRateRooms="
				+ contractRateRooms + "]";
	}
    
    
    

}
