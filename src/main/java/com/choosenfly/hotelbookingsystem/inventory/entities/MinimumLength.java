package com.choosenfly.hotelbookingsystem.inventory.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;

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
@Table(name = "minimum_length")
public class MinimumLength extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "market_id", nullable = false)
	private MasterMarketType marketType;

	@OneToMany(mappedBy = "minimumLength", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MinimumLengthValidity> validityPeriods;

	@OneToMany(mappedBy = "minimumLength", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MinimumLengthStay> minmumLengthStay;
	
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
    
    @Column
    private Boolean status;
    
    
    @Column
    private Boolean isDeleted;
    
    
    

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<MinimumLengthStay> getMinmumLengthStay() {
		return minmumLengthStay;
	}

	public void setMinmumLengthStay(List<MinimumLengthStay> minmumLengthStay) {
		this.minmumLengthStay = minmumLengthStay;
	}

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

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	public List<MinimumLengthValidity> getValidityPeriods() {
		return validityPeriods;
	}

	public void setValidityPeriods(List<MinimumLengthValidity> validityPeriods) {
		this.validityPeriods = validityPeriods;
	}

	@Override
	public String toString() {
		return "MinimumLength [id=" + id + ", marketType=" + marketType + ", validityPeriods=" + validityPeriods
				+ ", minmumLengthStay=" + minmumLengthStay + ", hotel=" + hotel + ", status=" + status + ", isDeleted="
				+ isDeleted + "]";
	}

	                  

	
	
	
}
