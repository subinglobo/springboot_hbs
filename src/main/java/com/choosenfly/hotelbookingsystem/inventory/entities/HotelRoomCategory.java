package com.choosenfly.hotelbookingsystem.inventory.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomCategory;

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
@Table(name = "hotel_roomcategory")
public class HotelRoomCategory extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 11)
	private long hotel_room_category_id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_category_id")
    private MasterRoomCategory roomCategory;

	@Column(length=100)
	private String name;

	@Column(length=100)
	private String no_of_rooms;
	
	
    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ContractRateRoom>  contractRateRooms;

	public long getHotel_room_category_id() {
		return hotel_room_category_id;
	}

	public void setHotel_room_category_id(long hotel_room_category_id) {
		this.hotel_room_category_id = hotel_room_category_id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public MasterRoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(MasterRoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo_of_rooms() {
		return no_of_rooms;
	}

	public void setNo_of_rooms(String no_of_rooms) {
		this.no_of_rooms = no_of_rooms;
	}

	
	public List<ContractRateRoom> getContractRateRooms() {
		return contractRateRooms;
	}

	public void setContractRateRooms(List<ContractRateRoom> contractRateRooms) {
		this.contractRateRooms = contractRateRooms;
	}

	@Override
	public String toString() {
		return "HotelRoomCategory [hotel_room_category_id=" + hotel_room_category_id + ", hotel=" + hotel
				+ ", roomCategory=" + roomCategory + ", name=" + name + ", no_of_rooms=" + no_of_rooms + "]";
	}
	
	
}
