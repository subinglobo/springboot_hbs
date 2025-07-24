package com.choosenfly.hotelbookingsystem.entities.master;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_package_type", schema = "public")
public class MasterPackageType extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Package_type_id", nullable = false)
    private Long PackageTypeId;

    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "code", length = 100)
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
		return "MasterPackageType [PackageTypeId=" + PackageTypeId + ", name=" + name + ", code=" + code + "]";
	}
    
    
}
