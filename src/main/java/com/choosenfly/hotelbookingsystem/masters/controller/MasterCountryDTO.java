package com.choosenfly.hotelbookingsystem.masters.controller;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

public class MasterCountryDTO extends BaseEntity {

    private Long id;

    private String name;

    private Boolean isDeleted = false;

    private Long marketTypeId;

    private Long regionId;

    private String countryCode;
    
    // Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "MasterCountryDTO [id=" + id + ", name=" + name + ", isDeleted=" + isDeleted + ", marketTypeId="
				+ marketTypeId + ", regionId=" + regionId + ", countryCode=" + countryCode + "]";
	}

    
     
   
   
}