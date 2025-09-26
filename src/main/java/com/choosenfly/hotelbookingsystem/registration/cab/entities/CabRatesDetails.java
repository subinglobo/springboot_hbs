package com.choosenfly.hotelbookingsystem.registration.cab.entities;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cabrate_details")
public class CabRatesDetails extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabrates_details_id")
    private Long cabRatesDetailsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cabrate_id")
	private CabRates cabRate;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "hour_details")
    private String hourDetails;

    @Column(name = "luggage")
    private Boolean luggage;

    @Column(name = "minpax")
    private Integer minPax;

    @Column(name = "maxpax")
    private Integer maxPax;

    @Column(name = "private_rate")
    private Double privateRate;

    @Column(name = "sic_rate")
    private Double sicRate;

    @Column(name = "travel_type")
    private String travelType;

    @Column(name = "is_active")
    private Boolean isActive;

	public Long getCabRatesDetailsId() {
		return cabRatesDetailsId;
	}

	public void setCabRatesDetailsId(Long cabRatesDetailsId) {
		this.cabRatesDetailsId = cabRatesDetailsId;
	}

	
	public CabRates getCabRate() {
		return cabRate;
	}

	public void setCabRate(CabRates cabRate) {
		this.cabRate = cabRate;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getHourDetails() {
		return hourDetails;
	}

	public void setHourDetails(String hourDetails) {
		this.hourDetails = hourDetails;
	}

	

	public Boolean getLuggage() {
		return luggage;
	}

	public void setLuggage(Boolean luggage) {
		this.luggage = luggage;
	}

	public Integer getMinPax() {
		return minPax;
	}

	public void setMinPax(Integer minPax) {
		this.minPax = minPax;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public Double getPrivateRate() {
		return privateRate;
	}

	public void setPrivateRate(Double privateRate) {
		this.privateRate = privateRate;
	}

	public Double getSicRate() {
		return sicRate;
	}

	public void setSicRate(Double sicRate) {
		this.sicRate = sicRate;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	@Override
	public String toString() {
		return "CabRatesDetails [cabRatesDetailsId=" + cabRatesDetailsId + ", cabRate=" + cabRate + ", locationId="
				+ locationId + ", hourDetails=" + hourDetails + ", luggage=" + luggage + ", minPax=" + minPax
				+ ", maxPax=" + maxPax + ", privateRate=" + privateRate + ", sicRate=" + sicRate + ", travelType="
				+ travelType + ", isActive=" + isActive + "]";
	}

    
}
