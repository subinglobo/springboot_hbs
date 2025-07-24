package com.choosenfly.hotelbookingsystem.dto.masters;

public class MasterPackageTypeDTO {

    private Long PackageTypeId;
    
    private String name;
    
    private String code;

	public Long getPackageTypeId() {
		return PackageTypeId;
	}

	public void setPackageTypeId(Long packageTypeId) {
		PackageTypeId = packageTypeId;
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
		return "MasterPackageTypeDTO [PackageTypeId=" + PackageTypeId + ", name=" + name + ", code=" + code + "]";
	}

    
    
}
