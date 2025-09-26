package com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="hotel_compulsorysupplyments_validity")
public class CompulsorySupplyValidity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplyment_validity_id")
	private Long supplymentValidityId;

	@ManyToOne
	@JoinColumn(name = "supplyment_id",nullable = false)
	private CompulsorySupplyments compulsorySupplyments;

	@Column(name = "validity_from")
	private Date validityFrom;
	
	@Column(name = "validity_to")
	private Date validityTo;
	
	@Column
	private boolean isLive = false;
	
	@Column(length=11)
	private long priority = 0;

	

	public Long getSupplymentValidityId() {
		return supplymentValidityId;
	}

	public void setSupplymentValidityId(Long supplymentValidityId) {
		this.supplymentValidityId = supplymentValidityId;
	}

	public CompulsorySupplyments getCompulsorySupplyments() {
		return compulsorySupplyments;
	}

	public void setCompulsorySupplyments(CompulsorySupplyments compulsorySupplyments) {
		this.compulsorySupplyments = compulsorySupplyments;
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

	@Override
	public String toString() {
		return "CompulsorySupplyValidity [supplymentValidityId=" + supplymentValidityId + ", compulsorySupplyments="
				+ compulsorySupplyments + ", validityFrom=" + validityFrom + ", validityTo=" + validityTo + ", isLive="
				+ isLive + ", priority=" + priority + "]";
	}
	
	
}
