package com.choosenfly.hotelbookingsystem.inventory.entities.specialrate;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_specialrate_markettype")
public class SpecialRateMarketType extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="specialrate_markeytype_id")
	private Long specialRateMarketTypeId;;

	@ManyToOne
	@JoinColumn(name ="special_rate_id",nullable = false )
	private SpecialRate specialRate;

	@ManyToOne
	@JoinColumn(name="markettype_id", nullable = false)
	private MasterMarketType marketType;

	public Long getSpecialRateMarketTypeId() {
		return specialRateMarketTypeId;
	}

	public void setSpecialRateMarketTypeId(Long specialRateMarketTypeId) {
		this.specialRateMarketTypeId = specialRateMarketTypeId;
	}

	public SpecialRate getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(SpecialRate specialRate) {
		this.specialRate = specialRate;
	}

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	@Override
	public String toString() {
		return "SpecialRateMarkupType [specialRateMarketTypeId=" + specialRateMarketTypeId + ", specialRate="
				+ specialRate + ", marketType=" + marketType + "]";
	}
	
	
	
	
}
