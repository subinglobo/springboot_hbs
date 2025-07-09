package com.choosenfly.hotelbookingsystem.entities.master;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_designation", schema = "public")
public class MasterDesignation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "designation_id", nullable = false)
    private Long designationId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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

	@Override
	public String toString() {
		return "MasterDesignation [designationId=" + designationId + ", name=" + name + ", isDeleted=" + isDeleted
				+ "]";
	}

    
    
}