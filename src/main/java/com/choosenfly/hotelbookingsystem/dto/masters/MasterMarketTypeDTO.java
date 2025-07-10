package com.choosenfly.hotelbookingsystem.dto.masters;

public class MasterMarketTypeDTO  {

    private Long marketTypeId;

    private String name;

    private Boolean isDeleted;

	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
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

   
    
}