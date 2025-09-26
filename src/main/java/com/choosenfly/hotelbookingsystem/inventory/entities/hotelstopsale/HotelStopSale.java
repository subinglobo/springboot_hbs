package com.choosenfly.hotelbookingsystem.inventory.entities.hotelstopsale;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stop_sales")
public class HotelStopSale extends BaseEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stopsale_id")
    private Long hotelStopSaleId;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name ="room_category_id" , nullable = false)
    private MasterRoomCategory roomCategoryId;
    
    @ManyToOne
    @JoinColumn(name ="markettype_id")
    private MasterMarketType  marketTypeId;
    
    private Boolean freeSale;
    
    private Boolean block;
    
    private Boolean roomAllocation;

    private Boolean isLive;
    
	 @OneToMany(mappedBy = "stopSale", cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
	private List<StopSaleValiditty> validityList;

	public Long getHotelStopSaleId() {
		return hotelStopSaleId;
	}

	public void setHotelStopSaleId(Long hotelStopSaleId) {
		this.hotelStopSaleId = hotelStopSaleId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public MasterRoomCategory getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(MasterRoomCategory roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

//	public MasterMarketType getMarketTypeId() {
//		return marketTypeId;
//	}
//
//	public void setMarketTypeId(MasterMarketType marketTypeId) {
//		this.marketTypeId = marketTypeId;
//	}

	public Boolean getFreeSale() {
		return freeSale;
	}

	public void setFreeSale(Boolean freeSale) {
		this.freeSale = freeSale;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	

	public Boolean getRoomAllocation() {
		return roomAllocation;
	}

	public void setRoomAllocation(Boolean roomAllocation) {
		this.roomAllocation = roomAllocation;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	
	public List<StopSaleValiditty> getValidityList() {
		return validityList;
	}

	public void setValidityList(List<StopSaleValiditty> validityList) {
		this.validityList = validityList;
	}

	
	public MasterMarketType getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(MasterMarketType marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	
	@Override
	public String toString() {
		return "HotelStopSale [hotelStopSaleId=" + hotelStopSaleId + ", hotelId=" + (hotel != null ? hotel.getHotelId() : null) + ", roomCategoryId="
				+ (roomCategoryId != null ? roomCategoryId.getRoomCategoryId() : null) + ", marketTypeId=" + (marketTypeId != null ? marketTypeId.getMarketTypeId() : null) + ", freeSale=" + freeSale + ", block=" + block
				+ ", roomAllocation=" + roomAllocation + ", isLive=" + isLive + ", validityCount=" + (validityList != null ? validityList.size() : 0) + "]";
	}

	

    
}
