package com.choosenfly.hotelbookingsystem.inventory.entities.contractrate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="hotel_contractrate_exclude_country")
public class ContractRateExcludeCountry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exclude_country_id")
	private Long id;

	@Column(name = "country_id")
	private Long coutryId;

	@ManyToOne
	@JoinColumn(name="contractrate_id",nullable = false)
	private ContractRate contractRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCoutryId() {
		return coutryId;
	}

	public void setCoutryId(Long coutryId) {
		this.coutryId = coutryId;
	}

	public ContractRate getContractRate() {
		return contractRate;
	}

	public void setContractRate(ContractRate contractRate) {
		this.contractRate = contractRate;
	}

	@Override
	public String toString() {
		return "ContractRateExcludeCountry [id=" + id + ", coutryId=" + coutryId + ", contractRate=" + contractRate
				+ "]";
	}
	
	
}
