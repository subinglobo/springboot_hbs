package com.choosenfly.hotelbookingsystem.masters.dto;


import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;

public class MasterPlaceDTO extends BaseEntity {

    private Long id;

    private Long stateId;

    private Long countryId;
    
    private String country;

    private String name;

    private String placeCode;
    
    private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "MasterPlaceDTO [id=" + id + ", stateId=" + stateId + ", countryId=" + countryId + ", country=" + country
				+ ", name=" + name + ", placeCode=" + placeCode + ", isDeleted=" + isDeleted + "]";
	}

	

	

	

	

	

  
}