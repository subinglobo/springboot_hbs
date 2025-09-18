package com.choosenfly.hotelbookingsystem.inventory.entities;

import java.util.Date;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StaypayRoom;
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
@Table(name = "hotel_room_type")
public class HotelRoomType extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotel_roomType_id")
	private long hotelRoomTypeId;

	@Column(name="hotel_roomcategory_id")
	private long hotelRoomcategoryId;
	
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private MasterRoomType roomType;

    
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ContractRateRoom> contractRateRooms ;
    
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<SpecialRateRoom> specialRateRooms ;


    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<DiscountRoom> discountRooms ;
    
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<StaypayRoom> staypayRooms ;


	public long getHotelRoomTypeId() {
		return hotelRoomTypeId;
	}


	public void setHotelRoomTypeId(long hotelRoomTypeId) {
		this.hotelRoomTypeId = hotelRoomTypeId;
	}


	public long getHotelRoomcategoryId() {
		return hotelRoomcategoryId;
	}


	public void setHotelRoomcategoryId(long hotelRoomcategoryId) {
		this.hotelRoomcategoryId = hotelRoomcategoryId;
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

	
	public List<SpecialRateRoom> getSpecialRateRooms() {
		return specialRateRooms;
	}


	public void setSpecialRateRooms(List<SpecialRateRoom> specialRateRooms) {
		this.specialRateRooms = specialRateRooms;
	}


	
	public List<DiscountRoom> getDiscountRooms() {
		return discountRooms;
	}


	public void setDiscountRooms(List<DiscountRoom> discountRooms) {
		this.discountRooms = discountRooms;
	}

	

	public List<StaypayRoom> getStaypayRooms() {
		return staypayRooms;
	}


	public void setStaypayRooms(List<StaypayRoom> staypayRooms) {
		this.staypayRooms = staypayRooms;
	}


	@Override
	public String toString() {
		return "HotelRoomType [hotelRoomTypeId=" + hotelRoomTypeId + ", hotelRoomcategoryId=" + hotelRoomcategoryId
				+ ", hotel=" + hotel + ", roomType=" + roomType + ", contractRateRooms=" + contractRateRooms
				+ ", specialRateRooms=" + specialRateRooms + ", discountRooms=" + discountRooms + ", staypayRooms="
				+ staypayRooms + "]";
	}
    
    
    

}
