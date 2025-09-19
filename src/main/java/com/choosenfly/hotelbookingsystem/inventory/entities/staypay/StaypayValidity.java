package com.choosenfly.hotelbookingsystem.inventory.entities.staypay;

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

@Entity
@Table(name="hotel_staypay_validity")
public class StaypayValidity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staypay_validity_id")
	private Long staypayValidityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="staypay_id")
	private StayPay stayPay;

	@Column(name = "validity_from")
	private Date validityFrom;
	
	@Column(name = "validity_to")
	private Date validityTo;
	
	@Column(name = "is_live")
	private boolean isLive = false;
	
	@Column(length=11)
	private long priority = 0;
	
	@Column(name="is_type")
	private String isType;

	public Long getStaypayValidityId() {
		return staypayValidityId;
	}

	public void setStaypayValidityId(Long staypayValidityId) {
		this.staypayValidityId = staypayValidityId;
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

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public long getPriority() {
		return priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	
	public StayPay getStayPay() {
		return stayPay;
	}

	public void setStayPay(StayPay stayPay) {
		this.stayPay = stayPay;
	}

	
	@Override
	public String toString() {
		return "StaypayValidity [staypayValidityId=" + staypayValidityId + ", stayPay=" + stayPay + ", validityFrom="
				+ validityFrom + ", validityTo=" + validityTo + ", isLive=" + isLive + ", priority=" + priority
				+ ", isType=" + isType + "]";
	}
	
	
}
