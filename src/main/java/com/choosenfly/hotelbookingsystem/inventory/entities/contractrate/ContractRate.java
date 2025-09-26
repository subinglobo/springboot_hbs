package com.choosenfly.hotelbookingsystem.inventory.entities.contractrate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

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
@Table(name = "hotel_contractrate")
public class ContractRate extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractrate_id")
    private Long id;

    @Column(name="exclude_country")
    private String excludeCountry;
    
    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;
    
    @Column(name="is_alldays")
    private Boolean isAllDays;
    
    @Column(name="is_live")
    private Boolean isLive;
    
    @Column(name="is_validity")
    private Boolean isValidity;
    
    @Column(name="is_weekday")
    private Boolean isWeekDay;
    
    @Column(name="is_weekendday")
    private Boolean isWeekEndDay;

    @Column(name="rate_code")
    private String rateCode;
    
    @Column(name = "season_id")
    private Long seasonId;
    
    @OneToMany(mappedBy = "contractRate",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractRateMarketType> marketTypes ;
    
    @OneToMany(mappedBy = "contractRate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractRateValidity> validities ;
    
    @OneToMany(mappedBy = "contractRate",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractRateRoom> rooms ;
    
    @OneToMany(mappedBy = "contractRate",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractRateExcludeCountry> contractRateExcludeCountries;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getExcludeCountry() {
		return excludeCountry;
	}

	public void setExcludeCountry(String excludeCountry) {
		this.excludeCountry = excludeCountry;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Boolean getIsAllDays() {
		return isAllDays;
	}

	public void setIsAllDays(Boolean isAllDays) {
		this.isAllDays = isAllDays;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	public Boolean getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(Boolean isValidity) {
		this.isValidity = isValidity;
	}

	public Boolean getIsWeekDay() {
		return isWeekDay;
	}

	public void setIsWeekDay(Boolean isWeekDay) {
		this.isWeekDay = isWeekDay;
	}

	public Boolean getIsWeekEndDay() {
		return isWeekEndDay;
	}

	public void setIsWeekEndDay(Boolean isWeekEndDay) {
		this.isWeekEndDay = isWeekEndDay;
	}



	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public List<ContractRateMarketType> getMarketTypes() {
		return marketTypes;
	}

	public void setMarketTypes(List<ContractRateMarketType> marketTypes) {
		this.marketTypes = marketTypes;
	}

	public List<ContractRateValidity> getValidities() {
		return validities;
	}

	public void setValidities(List<ContractRateValidity> validities) {
		this.validities = validities;
	}

	
	public List<ContractRateRoom> getRooms() {
		return rooms;
	}

	public void setRooms(List<ContractRateRoom> rooms) {
		this.rooms = rooms;
	}

	
	public List<ContractRateExcludeCountry> getContractRateExcludeCountries() {
		return contractRateExcludeCountries;
	}

	public void setContractRateExcludeCountries(List<ContractRateExcludeCountry> contractRateExcludeCountries) {
		this.contractRateExcludeCountries = contractRateExcludeCountries;
	}
	
	

	@Override
	public String toString() {
		return "ContractRate [id=" + id + ", excludeCountry=" + excludeCountry + ", hotel=" + hotel + ", isAllDays="
				+ isAllDays + ", isLive=" + isLive + ", isValidity=" + isValidity + ", isWeekDay=" + isWeekDay
				+ ", isWeekEndDay=" + isWeekEndDay + ", rateCode=" + rateCode + ", seasonId=" + seasonId
				+ ", marketTypes=" + marketTypes + ", validities=" + validities + ", rooms=" + rooms
				+ ", contractRateExcludeCountries=" + contractRateExcludeCountries + "]";
	}
    
    
    // Getters and setters
    
    
}
