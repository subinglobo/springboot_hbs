package com.choosenfly.hotelbookingsystem.inventory.hotel.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.availability.entities.HotelAvailability;
import com.choosenfly.hotelbookingsystem.inventory.hotel.linked.entities.LinkedHotelRoomAmenity;
import com.choosenfly.hotelbookingsystem.inventory.minimumlength.entities.MinimumLengthStay;
import com.choosenfly.hotelbookingsystem.inventory.occupancy.entities.RoomOccupancy;
import com.choosenfly.hotelbookingsystem.master.entities.MasterRoomCategory;
import com.choosenfly.hotelbookingsystem.master.entities.MasterRoomType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_room")
public class HotelRoom extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

	@ManyToOne
	@JoinColumn(name = "room_category_id", nullable = false)
	private MasterRoomCategory roomCategory;

	@Column(name = "room_name", length = 100)
	private String roomName;

	@OneToMany(mappedBy = "hotelRoom", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<LinkedHotelRoomAmenity> amenities;

	@OneToMany(mappedBy = "hotelRoom", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<RoomOccupancy> roomOccupancies;
	
	
	@OneToMany(mappedBy = "hotelRoom", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<HotelAvailability> availabilities;
	
	@OneToMany(mappedBy = "room", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<MinimumLengthStay> minimumLengthStay;

	@ManyToOne
	@JoinColumn(name = "room_type_id", nullable = false)
	private MasterRoomType roomType;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

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

	public MasterRoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(MasterRoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<LinkedHotelRoomAmenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<LinkedHotelRoomAmenity> amenities) {
		this.amenities = amenities;
	}

	public List<RoomOccupancy> getRoomOccupancies() {
		return roomOccupancies;
	}

	public void setRoomOccupancies(List<RoomOccupancy> roomOccupancies) {
		this.roomOccupancies = roomOccupancies;
	}

	public MasterRoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(MasterRoomType roomType) {
		this.roomType = roomType;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	

	public List<HotelAvailability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<HotelAvailability> availabilities) {
		this.availabilities = availabilities;
	}

	public List<MinimumLengthStay> getMinimumLengthStay() {
		return minimumLengthStay;
	}

	public void setMinimumLengthStay(List<MinimumLengthStay> minimumLengthStay) {
		this.minimumLengthStay = minimumLengthStay;
	}

	@Override
	public String toString() {
		return "HotelRoom [id=" + id + ", hotel=" + hotel + ", roomCategory=" + roomCategory + ", roomName=" + roomName
				+ ", amenities=" + amenities + ", roomOccupancies=" + roomOccupancies + ", roomType=" + roomType
				+ ", isDeleted=" + isDeleted + "]";
	}
	
	

	
	
}