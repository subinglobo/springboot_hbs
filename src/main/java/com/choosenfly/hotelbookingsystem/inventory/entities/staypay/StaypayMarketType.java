package com.choosenfly.hotelbookingsystem.inventory.entities.staypay;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="hotel_staypay_market")
public class StaypayMarketType extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "staypay_markettype_id")
	private long staypayMarkettypeId;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staypay_id")
    private StayPay stayPay;

	@ManyToOne
	@JoinColumn(name="markettype_id", nullable = false)
	private MasterMarketType marketType;

	public long getStaypayMarkettypeId() {
		return staypayMarkettypeId;
	}

	public void setStaypayMarkettypeId(long staypayMarkettypeId) {
		this.staypayMarkettypeId = staypayMarkettypeId;
	}

	
	public StayPay getStayPay() {
		return stayPay;
	}

	public void setStayPay(StayPay stayPay) {
		this.stayPay = stayPay;
	}

	
	
	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	
	@Override
	public String toString() {
		return "StaypayMarketType [staypayMarkettypeId=" + staypayMarkettypeId + ", stayPay=" + stayPay
				+ ", marketType=" + marketType + "]";
	}
	
	
}
