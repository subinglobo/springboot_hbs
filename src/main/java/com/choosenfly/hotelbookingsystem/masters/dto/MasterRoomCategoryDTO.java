package com.choosenfly.hotelbookingsystem.masters.dto;



import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class MasterRoomCategoryDTO extends BaseEntity {
	
	private Long roomCategoryId;

    private String roomCategory;

    private String categoryCode;
    
    private Boolean isDeleted;

	public Long getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(Long roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	public String getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "MasterRoomCategoryDTO [roomCategoryId=" + roomCategoryId + ", roomCategory=" + roomCategory
				+ ", categoryCode=" + categoryCode + ", isDeleted=" + isDeleted + "]";
	}




  
}