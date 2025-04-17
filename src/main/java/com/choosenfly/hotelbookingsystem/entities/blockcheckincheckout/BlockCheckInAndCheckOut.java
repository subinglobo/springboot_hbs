package com.choosenfly.hotelbookingsystem.entities.blockcheckincheckout;

import java.util.List;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
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
@Table(name = "block_checkin_checkout")
public class BlockCheckInAndCheckOut extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "block_id")
	private Long id;
	
	@Column(name = "is_checkin")
	private Boolean isCheckin;
	
	@Column(name = "is_checkout")
	private Boolean isCheckOut;
	
	@ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
	private MasterMarketType marketType;
	
	@ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	 @OneToMany(mappedBy = "blockCheckinCheckout", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BlockCheckinCheckoutValidity> validityList;

	 
	 
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsCheckin() {
		return isCheckin;
	}

	public void setIsCheckin(Boolean isCheckin) {
		this.isCheckin = isCheckin;
	}

	public Boolean getIsCheckOut() {
		return isCheckOut;
	}

	public void setIsCheckOut(Boolean isCheckOut) {
		this.isCheckOut = isCheckOut;
	}

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	public List<BlockCheckinCheckoutValidity> getValidityList() {
		return validityList;
	}

	public void setValidityList(List<BlockCheckinCheckoutValidity> validityList) {
		this.validityList = validityList;
	}

	@Override
	public String toString() {
		return "BlockCheckInAndCheckOut [id=" + id + ", isCheckin=" + isCheckin + ", isCheckOut=" + isCheckOut
				+ ", marketType=" + marketType + ", validityList=" + validityList + "]";
	}
	
	
	
}
