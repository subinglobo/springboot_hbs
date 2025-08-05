package com.choosenfly.hotelbookingsystem.masters.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_package_category", schema = "public")
public class MasterPackageCategory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Package_category_id", nullable = false)
    private Long PackageCategoryId;

    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "code", length = 100)
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
		return "MasterPackageCategory [PackageCategoryId=" + PackageCategoryId + ", name=" + name + ", code=" + code
				+ "]";
	}

    
}
