package com.choosenfly.hotelbookingsystem.masters.dto;



import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MasterCurrencyDTO extends BaseEntity{
    
    private Long currencyId;

    private Boolean isDeleted;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Value cannot be null")
    @NotBlank(message = "Value cannot be empty")
    private String value;

    @NotNull(message = "Currency code cannot be null")
    @NotBlank(message = "Currency code cannot be empty")
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