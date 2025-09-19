package com.choosenfly.hotelbookingsystem.inventory.entities.specialrate;

import java.util.Date;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_specialrate_validity")
public class SpecialRateValidity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="special_validity_id")
	private long specialRateValidityId;

	@ManyToOne
	@JoinColumn(name ="special_rate_id",nullable = false )
	private SpecialRate specialRate;

	@Column(name="validity_from")
	private Date validityFrom;
	
	@Column(name="validity_to")
	private Date validityTo;
	
	@Column(name="blackout_from")
	private Date blackOutFrom;
	
	@Column(name="blackout_to")
	private Date blackOutTo;
	
	@Column(name="is_live")
	private Boolean isLive = false;
	
	@Column(name="priority")
	private Integer priority = 0;
	
	@Column(name="is_type")
	private String isType;

	public long getSpecialRateValidityId() {
		return specialRateValidityId;
	}

	public void setSpecialRateValidityId(long specialRateValidityId) {
		this.specialRateValidityId = specialRateValidityId;
	}

	public SpecialRate getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(SpecialRate specialRate) {
		this.specialRate = specialRate;
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

	public Date getBlackOutFrom() {
		return blackOutFrom;
	}

	public void setBlackOutFrom(Date blackOutFrom) {
		this.blackOutFrom = blackOutFrom;
	}

	public Date getBlackOutTo() {
		return blackOutTo;
	}

	public void setBlackOutTo(Date blackOutTo) {
		this.blackOutTo = blackOutTo;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	@Override
	public String toString() {
		return "SpecialRateValidity [specialRateValidityId=" + specialRateValidityId + ", specialRate=" + specialRate
				+ ", validityFrom=" + validityFrom + ", validityTo=" + validityTo + ", blackOutFrom=" + blackOutFrom
				+ ", blackOutTo=" + blackOutTo + ", isLive=" + isLive + ", priority=" + priority + ", isType=" + isType
				+ "]";
	}
	
	

}
