package com.choosenfly.hotelbookingsystem.masters.dto;

public class MasterPackageCategoryDTO {

    private Long PackageCategoryId;
    
    private String name;
    
    private String code;

	public Long getPackageCategoryId() {
		return PackageCategoryId;
	}

	public void setPackageCategoryId(Long packageCategoryId) {
		PackageCategoryId = packageCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MasterPackageCategoryDTO [PackageCategoryId=" + PackageCategoryId + ", name=" + name + ", code=" + code
				+ "]";
	}
    
    
}
