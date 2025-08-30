package com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_compulsorysupplyments_rate")
public class CompulsorySupplymentsRate extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplymentrate_id")
	private long supplymentrateId;

	@ManyToOne
	@JoinColumn(name = "supplyment_id",nullable = false)
	private CompulsorySupplyments compulsorySupplyments;
	
    @ManyToOne
    @JoinColumn(name = "hotel_room_category_id", nullable = false)
    private HotelRoomCategory roomCategory;
	
    @ManyToOne
    @JoinColumn(name = "occupancy_id", nullable = false)
    private HotelOccupancy hotelOccupancy;

	@Column(length=100)
	private Double rate;
	
	@Column(name="adult_rate")
	private Double adultRate;

	@Column(name="child_rate")
	private Double childRate;

	public long getSupplymentrateId() {
		return supplymentrateId;
	}

	public void setSupplymentrateId(long supplymentrateId) {
		this.supplymentrateId = supplymentrateId;
	}

	public CompulsorySupplyments getCompulsorySupplyments() {
		return compulsorySupplyments;
	}

	public void setCompulsorySupplyments(CompulsorySupplyments compulsorySupplyments) {
		this.compulsorySupplyments = compulsorySupplyments;
	}

	public HotelRoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(HotelRoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	public HotelOccupancy getHotelOccupancy() {
		return hotelOccupancy;
	}

	public void setHotelOccupancy(HotelOccupancy hotelOccupancy) {
		this.hotelOccupancy = hotelOccupancy;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	
	public Double getAdultRate() {
		return adultRate;
	}

	public void setAdultRate(Double adultRate) {
		this.adultRate = adultRate;
	}

	public Double getChildRate() {
		return childRate;
	}

	public void setChildRate(Double childRate) {
		this.childRate = childRate;
	}

	
	@Override
	public String toString() {
		return "CompulsorySupplymentsRate [supplymentrateId=" + supplymentrateId + ", compulsorySupplyments="
				+ compulsorySupplyments + ", roomCategory=" + roomCategory + ", hotelOccupancy=" + hotelOccupancy
				+ ", rate=" + rate + ", adultRate=" + adultRate + ", childRate=" + childRate + "]";
	}
	
	
	
	
}
