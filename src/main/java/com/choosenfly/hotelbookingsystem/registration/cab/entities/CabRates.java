package com.choosenfly.hotelbookingsystem.registration.cab.entities;

import java.util.ArrayList;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

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
@Table(name = "cab_rates")
public class CabRates extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabrate_id")
    private Long cabRatesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cab_id", nullable = false)
    private Cab cab;

    @Column(name = "rate_code")
    private String rateCode;

    @Column(name = "is_active")
    private Boolean isActive;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabprovider_id", nullable = false) // read-only link
    private CabProvider cabProvider;
    
    @OneToMany(mappedBy = "cabRate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CabRateMarketType> marketTypes = new ArrayList<>();
    
    @OneToMany(mappedBy = "cabRate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CabRatesDetails> cabRatesDetails = new ArrayList<>();
    
    @OneToMany(mappedBy = "cabRate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CabRateValidity> cabRateValidities = new ArrayList<>();

	public Long getCabRatesId() {
		return cabRatesId;
	}

	public void setCabRatesId(Long cabRatesId) {
		this.cabRatesId = cabRatesId;
	}

	

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	public List<CabRateMarketType> getMarketTypes() {
		return marketTypes;
	}

	public void setMarketTypes(List<CabRateMarketType> marketTypes) {
		this.marketTypes = marketTypes;
	}

	
	public List<CabRatesDetails> getCabRatesDetails() {
		return cabRatesDetails;
	}

	public void setCabRatesDetails(List<CabRatesDetails> cabRatesDetails) {
		this.cabRatesDetails = cabRatesDetails;
	}

	public List<CabRateValidity> getCabRateValidities() {
		return cabRateValidities;
	}

	public void setCabRateValidities(List<CabRateValidity> cabRateValidities) {
		this.cabRateValidities = cabRateValidities;
	}
	
	
	
	public CabProvider getCabProvider() {
		return cabProvider;
	}

	public void setCabProvider(CabProvider cabProvider) {
		this.cabProvider = cabProvider;
	}

	
	@Override
	public String toString() {
		return "CabRates [cabRatesId=" + cabRatesId + ", cab=" + cab + ", rateCode=" + rateCode + ", isActive="
				+ isActive + ", cabProvider=" + cabProvider + ", marketTypes=" + marketTypes + ", cabRatesDetails="
				+ cabRatesDetails + ", cabRateValidities=" + cabRateValidities + "]";
	}
    
    
	
}
