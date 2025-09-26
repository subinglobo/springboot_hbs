package com.choosenfly.hotelbookingsystem.inventory.entities.staypay;

import java.util.Date;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
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
@Table(name="hotel_staypay_room")
public class StaypayRoom extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="staypay_room_id")
	private Long staypayRoomId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="staypay_id")
	private StayPay stayPay;
	
    // Room-related relationships
    @ManyToOne
    @JoinColumn(name = "hotel_room_category_id", nullable = false)
    private HotelRoomCategory roomCategory;
    
    @ManyToOne
    @JoinColumn(name = "hotel_roomType_id", nullable = false)
    private HotelRoomType roomType;
	
	@Column(name="no_of_free")
	private Long noOffree;
	
	@Column(name="no_of_pay")
	private Long noOfpay;
	
	@Column(name="no_of_stay")
	private Long noOfStay;

	public Long getStaypayRoomId() {
		return staypayRoomId;
	}

	public void setStaypayRoomId(Long staypayRoomId) {
		this.staypayRoomId = staypayRoomId;
	}

	
	public StayPay getStayPay() {
		return stayPay;
	}

	public void setStayPay(StayPay stayPay) {
		this.stayPay = stayPay;
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

	public Long getNoOffree() {
		return noOffree;
	}

	public void setNoOffree(Long noOffree) {
		this.noOffree = noOffree;
	}

	public Long getNoOfpay() {
		return noOfpay;
	}

	public void setNoOfpay(Long noOfpay) {
		this.noOfpay = noOfpay;
	}

	public Long getNoOfStay() {
		return noOfStay;
	}

	public void setNoOfStay(Long noOfStay) {
		this.noOfStay = noOfStay;
	}

	
	@Override
	public String toString() {
		return "StaypayRoom [staypayRoomId=" + staypayRoomId + ", stayPay=" + stayPay + ", roomCategory=" + roomCategory
				+ ", roomType=" + roomType + ", noOffree=" + noOffree + ", noOfpay=" + noOfpay + ", noOfStay="
				+ noOfStay + "]";
	}
	
	

}
