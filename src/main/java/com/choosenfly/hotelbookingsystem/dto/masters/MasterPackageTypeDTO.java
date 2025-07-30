package com.choosenfly.hotelbookingsystem.dto.masters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MasterPackageTypeDTO {

    private Long PackageTypeId;
    
    @NotBlank(message = "name is rquired")
    private String name;
    
    @NotBlank(message = "code is rquired")
    @Size(min =2 , max =10 , message = "Code must be 2–10 characters"  )
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
