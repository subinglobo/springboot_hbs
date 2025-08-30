package com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents;

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
@Table(name="hotel_compulsorysupplyments_markettype")
public class CompulsorySupplyMarketType extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "supplyment_markettype_id")
	private long supplymentMarkettypeId;

	@ManyToOne
	@JoinColumn(name = "supplyment_id",nullable = false)
	private CompulsorySupplyments compulsorySupplyments;
	
	
	@ManyToOne
	@JoinColumn(name="markettype_id", nullable = false)
	private MasterMarketType marketType;


	public long getSupplymentMarkettypeId() {
		return supplymentMarkettypeId;
	}


	public void setSupplymentMarkettypeId(long supplymentMarkettypeId) {
		this.supplymentMarkettypeId = supplymentMarkettypeId;
	}


	public CompulsorySupplyments getCompulsorySupplyments() {
		return compulsorySupplyments;
	}


	public void setCompulsorySupplyments(CompulsorySupplyments compulsorySupplyments) {
		this.compulsorySupplyments = compulsorySupplyments;
	}


	public MasterMarketType getMarketType() {
		return marketType;
	}


	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}


	@Override
	public String toString() {
		return "CompulsorySupplyMarketType [supplymentMarkettypeId=" + supplymentMarkettypeId
				+ ", compulsorySupplyments=" + compulsorySupplyments + ", marketType=" + marketType + "]";
	}
	
	
}
