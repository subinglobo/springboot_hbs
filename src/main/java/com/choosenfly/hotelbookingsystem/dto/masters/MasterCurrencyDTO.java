package com.choosenfly.hotelbookingsystem.dto.masters;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

public class MasterCurrencyDTO extends BaseEntity{
    
    private Long currencyId;

    private Boolean isDeleted;

    private String name;

    private String value;

    private String currencyCode;

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "MasterCurrencyDTO [currencyId=" + currencyId + ", isDeleted=" + isDeleted + ", name=" + name
				+ ", value=" + value + ", currencyCode=" + currencyCode + "]";
	}

    // Getters and Setters
    

    
}