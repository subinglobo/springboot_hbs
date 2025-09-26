package com.choosenfly.hotelbookingsystem.inventory.entities.contractrate;

import java.util.Date;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_contractrate_markettype")
public class ContractRateMarketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractrate_markettype_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractrate_id")
    private ContractRate contractRate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "markettype_id", nullable = false)
    private MasterMarketType marketType;
    
    private String modifiedBy;
    private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContractRate getContractRate() {
		return contractRate;
	}

	public void setContractRate(ContractRate contractRate) {
		this.contractRate = contractRate;
	}

	

	

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "ContractRateMarketType [id=" + id + ", contractRate=" + contractRate + ", marketType=" + marketType
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}

	
    
    
    
    
    // Getters and setters
}
