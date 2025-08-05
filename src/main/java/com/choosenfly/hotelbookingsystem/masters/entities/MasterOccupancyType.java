package com.choosenfly.hotelbookingsystem.masters.entities;


import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@Table(name = "master_occupancy_type", schema = "public")
public class MasterOccupancyType extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "occupancy_type_id", nullable = false)
	private Long occupancyTypeId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_deleted") 
    private Boolean isDeleted;

	public Long getOccupancyTypeId() {
		return occupancyTypeId;
	}

	public void setOccupancyTypeId(Long occupancyTypeId) {
		this.occupancyTypeId = occupancyTypeId;
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
		return "MasterOccupancyType [occupancyTypeId=" + occupancyTypeId + ", name=" + name + ", isDeleted=" + isDeleted
				+ "]";
	}

    // Getters and Setters
    
}