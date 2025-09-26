package com.choosenfly.hotelbookingsystem.registration.cab.entities;

import java.util.Date;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cabrate_validity")
public class CabRateValidity extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabrate_validity_id")
    private Long cabValidityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabrate_id", nullable = false)
    private CabRates cabRate;

    @Column(name = "validity_from")
    @Temporal(TemporalType.DATE)
    private Date validityFrom;

    @Column(name = "validity_to")
    @Temporal(TemporalType.DATE)
    private Date validityTo;

	public Long getCabValidityId() {
		return cabValidityId;
	}

	public void setCabValidityId(Long cabValidityId) {
		this.cabValidityId = cabValidityId;
	}

	

	public CabRates getCabRate() {
		return cabRate;
	}

	public void setCabRate(CabRates cabRate) {
		this.cabRate = cabRate;
	}

	public Date getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(Date validityFrom) {
		this.validityFrom = validityFrom;
	}

	public Date getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(Date validityTo) {
		this.validityTo = validityTo;
	}

	
	@Override
	public String toString() {
		return "CabRateValidity [cabValidityId=" + cabValidityId + ", cabRate=" + cabRate + ", validityFrom="
				+ validityFrom + ", validityTo=" + validityTo + "]";
	}
    
    
	
}
