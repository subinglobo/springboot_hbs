package com.choosenfly.hotelbookingsystem.masters.entities;



import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.LinkedHotelAmenity;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateMarketType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_market_type", schema = "public")
public class MasterMarketType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "markettype_id", nullable = false)
    private Long marketTypeId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
    
	
    @OneToMany(mappedBy = "marketType", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ContractRateMarketType> contractRateMarketTypes ;
    
    @OneToMany(mappedBy = "marketType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SpecialRateMarketType>specialRateMarketTypes ;


	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
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
	public List<ContractRateMarketType> getContractRateMarketTypes() {
		return contractRateMarketTypes;
	}

	
	
	public List<SpecialRateMarketType> getSpecialRateMarketTypes() {
		return specialRateMarketTypes;
	}

	public void setSpecialRateMarketTypes(List<SpecialRateMarketType> specialRateMarketTypes) {
		this.specialRateMarketTypes = specialRateMarketTypes;
	}

	public void setContractRateMarketTypes(List<ContractRateMarketType> contractRateMarketTypes) {
		this.contractRateMarketTypes = contractRateMarketTypes;
	}

   
    
}