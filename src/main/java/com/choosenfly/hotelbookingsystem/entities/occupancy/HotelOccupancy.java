package com.choosenfly.hotelbookingsystem.entities.occupancy;

import java.util.List;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelRoom;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarketType;

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
@Table(name="hotel_occupancy")
public class HotelOccupancy extends BaseEntity {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "occupancy_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private MasterMarketType marketType;

    @OneToMany(mappedBy = "hotelOccupancy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OccupancyValidity> validityPeriods;

    @OneToMany(mappedBy = "hotelOccupancy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelRoom> HotelRoom;


    @Column(name = "is_deleted")
    private boolean deleted = false;

    @Column(name = "is_live")
    private boolean live = false;

    @Column(name = "is_validity")
    private boolean validity;

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

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	public List<OccupancyValidity> getValidityPeriods() {
		return validityPeriods;
	}

	public void setValidityPeriods(List<OccupancyValidity> validityPeriods) {
		this.validityPeriods = validityPeriods;
	}

	public List<HotelRoom> getHotelRoom() {
		return HotelRoom;
	}

	public void setHotelRoom(List<HotelRoom> hotelRoom) {
		HotelRoom = hotelRoom;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}

	@Override
	public String toString() {
		return "HotelOccupancy [id=" + id + ", hotel=" + hotel + ", marketType=" + marketType + ", deleted=" + deleted
				+ ", live=" + live + ", validity=" + validity + "]";
	}
    
    
}
