package com.choosenfly.hotelbookingsystem.masters.dto;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

public class MasterOccupancyTypeDTO extends BaseEntity {

    private Long occupancyTypeId;

    private String occupancy;

    private Boolean isDeleted;

	public Long getOccupancyTypeId() {
		return occupancyTypeId;
	}

	public void setOccupancyTypeId(Long occupancyTypeId) {
		this.occupancyTypeId = occupancyTypeId;
	}

	public String getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
    
}