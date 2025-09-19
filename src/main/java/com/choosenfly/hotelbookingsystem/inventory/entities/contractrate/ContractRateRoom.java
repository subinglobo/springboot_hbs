package com.choosenfly.hotelbookingsystem.inventory.entities.contractrate;

import java.util.Date;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_contractrate_roomdetails")
public class ContractRateRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_detail_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "contractrate_id")
    private ContractRate contractRate;
    
    // Room-related relationships
    @ManyToOne
    @JoinColumn(name = "hotel_room_category_id", nullable = false)
    private HotelRoomCategory roomCategory;
    
    @ManyToOne
    @JoinColumn(name = "hotel_roomType_id", nullable = false)
    private HotelRoomType roomType;
    
    @ManyToOne
    @JoinColumn(name = "occupancy_id", nullable = false)
    private HotelOccupancy hotelOccupancy;
    
    @Column(name="rate")
	private Double rate;
  
    // Other rate fields
	@Column(name="adult_rate")
    private Double adultRate; 
    
	@Column(name="child_rate")
    private Double childRate;

	@Column(name="is_extrabed")
    private Boolean isExtraBed;
    
	@Column(name="is_meal")
    private Boolean isMeal;
    
	@Column(name="is_refundable")
    private Boolean isRefundable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContractRate getContractRate() {
		return contractRate;
	}

	public void setContractRate(ContractRate contractRate) {
		this.contractRate = contractRate;
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

	public Boolean getIsExtraBed() {
		return isExtraBed;
	}

	public void setIsExtraBed(Boolean isExtraBed) {
		this.isExtraBed = isExtraBed;
	}

	public Boolean getIsMeal() {
		return isMeal;
	}

	public void setIsMeal(Boolean isMeal) {
		this.isMeal = isMeal;
	}

	
	public Boolean getIsRefundable() {
		return isRefundable;
	}

	public void setIsRefundable(Boolean isRefundable) {
		this.isRefundable = isRefundable;
	}

	
	@Override
	public String toString() {
		return "ContractRateRoom [id=" + id + ", contractRate=" + contractRate + ", roomCategory=" + roomCategory
				+ ", roomType=" + roomType + ", hotelOccupancy=" + hotelOccupancy + ", rate=" + rate + ", adultRate="
				+ adultRate + ", childRate=" + childRate + ", isExtraBed=" + isExtraBed + ", isMeal=" + isMeal
				+ ", isRefundable=" + isRefundable + "]";
	}

	
    
}
