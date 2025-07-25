package com.choosenfly.hotelbookingsystem.entities.master;


import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@Table(name = "master_season_type", schema = "public")
public class MasterSeasonType extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "season_type_id", nullable = false)
	private Long seasonTypeId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_deleted") 
    private Boolean isDeleted;

	public Long getSeasonTypeId() {
		return seasonTypeId;
	}

	public void setSeasonTypeId(Long seasonTypeId) {
		this.seasonTypeId = seasonTypeId;
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
		return "MasterSeasonType [seasonTypeId=" + seasonTypeId + ", name=" + name + ", isDeleted=" + isDeleted + "]";
	}

	

	
    
}