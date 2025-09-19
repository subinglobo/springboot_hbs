package com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;

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
@Table(name = "hotel_compulsorysupplyments")
public class CompulsorySupplyments extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplyment_id")
	private Long supplymentId;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

	@Column(name = "supplyment_code")
	private String supplymentCode;
	
	@Column(length=100)
	private String supplyments;

	@Column(name="is_live")
	private boolean isLive= false;
	
	@OneToMany(mappedBy = "compulsorySupplyments", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CompulsorySupplyValidity> compulsorySupplyValidities;
	
	@OneToMany(mappedBy = "compulsorySupplyments", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CompulsorySupplymentsRate> compulsorySupplymentsRates;
	
	@OneToMany(mappedBy = "compulsorySupplyments", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CompulsorySupplyMarketType> compulsorySupplyMarketTypes;


	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getSupplymentCode() {
		return supplymentCode;
	}

	public void setSupplymentCode(String supplymentCode) {
		this.supplymentCode = supplymentCode;
	}

	public String getSupplyments() {
		return supplyments;
	}

	public void setSupplyments(String supplyments) {
		this.supplyments = supplyments;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	
	public List<CompulsorySupplyValidity> getCompulsorySupplyValidities() {
		return compulsorySupplyValidities;
	}

	public void setCompulsorySupplyValidities(List<CompulsorySupplyValidity> compulsorySupplyValidities) {
		this.compulsorySupplyValidities = compulsorySupplyValidities;
	}

	
	public List<CompulsorySupplymentsRate> getCompulsorySupplymentsRates() {
		return compulsorySupplymentsRates;
	}

	public void setCompulsorySupplymentsRates(List<CompulsorySupplymentsRate> compulsorySupplymentsRates) {
		this.compulsorySupplymentsRates = compulsorySupplymentsRates;
	}

	
	public List<CompulsorySupplyMarketType> getCompulsorySupplyMarketTypes() {
		return compulsorySupplyMarketTypes;
	}

	public void setCompulsorySupplyMarketTypes(List<CompulsorySupplyMarketType> compulsorySupplyMarketTypes) {
		this.compulsorySupplyMarketTypes = compulsorySupplyMarketTypes;
	}

	
	public void setSupplymentId(Long supplymentId) {
		this.supplymentId = supplymentId;
	}

	
	public Long getSupplymentId() {
		return supplymentId;
	}

	@Override
	public String toString() {
		return "CompulsorySupplyments [supplymentId=" + supplymentId + ", hotel=" + hotel + ", supplymentCode="
				+ supplymentCode + ", supplyments=" + supplyments + ", isLive=" + isLive
				+ ", compulsorySupplyValidities=" + compulsorySupplyValidities + ", compulsorySupplymentsRates="
				+ compulsorySupplymentsRates + ", compulsorySupplyMarketTypes=" + compulsorySupplyMarketTypes + "]";
	}
	
	

}
