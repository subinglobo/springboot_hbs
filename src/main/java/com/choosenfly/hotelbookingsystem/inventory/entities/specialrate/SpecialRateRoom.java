package com.choosenfly.hotelbookingsystem.inventory.entities.specialrate;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_specialrate_room")
public class SpecialRateRoom extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="specialrate_room_id")
	private Long specialRateRoomId;

	@ManyToOne
	@JoinColumn(name ="special_rate_id",nullable = false )
	private SpecialRate specialRate;
	
    @ManyToOne
    @JoinColumn(name = "hotel_room_category_id", nullable = false)
    private HotelRoomCategory roomCategory;

    @ManyToOne
    @JoinColumn(name = "hotel_roomType_id", nullable = false)
    private HotelRoomType roomType;
	
    @ManyToOne
    @JoinColumn(name = "occupancy_id", nullable = false)
    private HotelOccupancy hotelOccupancy;
	
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "hotel_meal_id")
	private Long hotelMealId;
	
	@Column(name = "is_extrabed")
	private Boolean isExtraBed;
	
	@Column(name = "is_meal")
	private boolean isMeal;
	
	@Column(name = "adult_rate")
	private Double adultrate;
	
	@Column(name = "child_rate")
	private Double childrate;

	public Long getSpecialRateRoomId() {
		return specialRateRoomId;
	}

	public void setSpecialRateRoomId(Long specialRateRoomId) {
		this.specialRateRoomId = specialRateRoomId;
	}

	public SpecialRate getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(SpecialRate specialRate) {
		this.specialRate = specialRate;
	}

	public HotelRoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(HotelRoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	public HotelRoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(HotelRoomType roomType) {
		this.roomType = roomType;
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

	public Long getHotelMealId() {
		return hotelMealId;
	}

	public void setHotelMealId(Long hotelMealId) {
		this.hotelMealId = hotelMealId;
	}

	public Boolean getIsExtraBed() {
		return isExtraBed;
	}

	public void setIsExtraBed(Boolean isExtraBed) {
		this.isExtraBed = isExtraBed;
	}

	public boolean isMeal() {
		return isMeal;
	}

	public void setMeal(boolean isMeal) {
		this.isMeal = isMeal;
	}

	
	
	public Double getAdultrate() {
		return adultrate;
	}

	public void setAdultrate(Double adultrate) {
		this.adultrate = adultrate;
	}

	public Double getChildrate() {
		return childrate;
	}

	public void setChildrate(Double childrate) {
		this.childrate = childrate;
	}

	@Override
	public String toString() {
		return "SpecialRateRoom [specialRateRoomId=" + specialRateRoomId + ", specialRate=" + specialRate
				+ ", roomCategory=" + roomCategory + ", roomType=" + roomType + ", hotelOccupancy=" + hotelOccupancy
				+ ", rate=" + rate + ", hotelMealId=" + hotelMealId + ", isExtraBed=" + isExtraBed + ", isMeal="
				+ isMeal + ", adultrate=" + adultrate + ", childrate=" + childrate + "]";
	}
	
	

}
