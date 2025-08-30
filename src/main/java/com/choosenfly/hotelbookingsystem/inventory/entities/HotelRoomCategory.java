package com.choosenfly.hotelbookingsystem.inventory.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplymentsRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StaypayRoom;
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

	@Column(name="no_of_rooms")
	private String noOfRooms;
	
	
    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<ContractRateRoom>  contractRateRooms;
    
    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<SpecialRateRoom> specialRateRooms;
    
    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<DiscountRoom> discountRooms;
    
    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<StaypayRoom> staypayRooms;
    
    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CompulsorySupplymentsRate> compulsorySupplymentsRates;

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

	

	
	public String getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(String noOfRooms) {
		this.noOfRooms = noOfRooms;
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

	
	
	public List<CompulsorySupplymentsRate> getCompulsorySupplymentsRates() {
		return compulsorySupplymentsRates;
	}

	public void setCompulsorySupplymentsRates(List<CompulsorySupplymentsRate> compulsorySupplymentsRates) {
		this.compulsorySupplymentsRates = compulsorySupplymentsRates;
	}

	
	@Override
	public String toString() {
		return "HotelRoomCategory [hotel_room_category_id=" + hotel_room_category_id + ", hotel=" + hotel
				+ ", roomCategory=" + roomCategory + ", name=" + name + ", noOfRooms=" + noOfRooms
				+ ", contractRateRooms=" + contractRateRooms + ", specialRateRooms=" + specialRateRooms
				+ ", discountRooms=" + discountRooms + ", staypayRooms=" + staypayRooms
				+ ", compulsorySupplymentsRates=" + compulsorySupplymentsRates + "]";
	}
	
	
}
