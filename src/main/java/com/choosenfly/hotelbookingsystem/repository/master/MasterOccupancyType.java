package com.choosenfly.hotelbookingsystem.repository.master;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_occupancy_type")
public class MasterOccupancyType extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "occupancy_type_id", nullable = false)
	private Long occupancyTypeId;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false; // Default value in Java

	@Column(name = "name", length = 100)
	private String name;

	public Long getOccupancyTypeId() {
		return occupancyTypeId;
	}

	public void setOccupancyTypeId(Long occupancyTypeId) {
		this.occupancyTypeId = occupancyTypeId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MasterOccupancyType [occupancyTypeId=" + occupancyTypeId + ", isDeleted=" + isDeleted + ", name=" + name
				+ "]";
	}

}
