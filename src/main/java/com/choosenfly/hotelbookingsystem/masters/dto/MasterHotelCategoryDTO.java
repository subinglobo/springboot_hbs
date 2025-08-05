package com.choosenfly.hotelbookingsystem.masters.dto;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

public class MasterHotelCategoryDTO extends BaseEntity{
    
    private Long hotelCategoryId;

    private String hotelCategory;

    private String tagLine;
    
    private Boolean isDeleted;

	public Long getHotelCategoryId() {
		return hotelCategoryId;
	}

	public void setHotelCategoryId(Long hotelCategoryId) {
		this.hotelCategoryId = hotelCategoryId;
	}

	public String getHotelCategory() {
		return hotelCategory;
	}

	public void setHotelCategory(String hotelCategory) {
		this.hotelCategory = hotelCategory;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "MasterHotelCategoryDTO [hotelCategoryId=" + hotelCategoryId + ", hotelCategory=" + hotelCategory
				+ ", tagLine=" + tagLine + ", isDeleted=" + isDeleted + "]";
	}

	

    
}