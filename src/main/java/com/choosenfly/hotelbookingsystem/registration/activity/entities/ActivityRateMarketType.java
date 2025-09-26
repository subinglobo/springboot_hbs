package com.choosenfly.hotelbookingsystem.registration.activity.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "activityrates_markettypes")
public class ActivityRateMarketType extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_market_id")
    private Long activityMarketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketType_id", nullable = false)
    private MasterMarketType marketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_rate_id", nullable = false)
    private ActivityRate activityRate;

    

	public Long getActivityMarketId() {
		return activityMarketId;
	}

	public void setActivityMarketId(Long activityMarketId) {
		this.activityMarketId = activityMarketId;
	}

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	public ActivityRate getActivityRate() {
		return activityRate;
	}

	public void setActivityRate(ActivityRate activityRate) {
		this.activityRate = activityRate;
	}

	@Override
	public String toString() {
		return "ActivityRateMarketType [activityMarketId=" + activityMarketId + ", marketType=" + marketType
				+ ", activityRate=" + activityRate + "]";
	}
    
    
	
}
