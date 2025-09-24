package com.choosenfly.hotelbookingsystem.registration.cab.entities;

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
@Table(name = "Cab_ratemarket_type")
public class CabRateMarketType extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cab_markettype_id")
    private Long CabRateMarketTypeId;

    // Many market types can belong to one cab rate
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabrate_id", nullable = false)
    private CabRates cabRate;

    // Many cab rate mappings can point to one market type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketType_id", nullable = false)
    private MasterMarketType marketType;



	public CabRates getCabRate() {
		return cabRate;
	}

	public void setCabRate(CabRates cabRate) {
		this.cabRate = cabRate;
	}

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	public Long getCabRateMarketTypeId() {
		return CabRateMarketTypeId;
	}

	public void setCabRateMarketTypeId(Long cabRateMarketTypeId) {
		CabRateMarketTypeId = cabRateMarketTypeId;
	}

	
	@Override
	public String toString() {
		return "CabRateMarketType [CabRateMarketTypeId=" + CabRateMarketTypeId + ", cabRate=" + cabRate
				+ ", marketType=" + marketType + "]";
	}
    
    
}
