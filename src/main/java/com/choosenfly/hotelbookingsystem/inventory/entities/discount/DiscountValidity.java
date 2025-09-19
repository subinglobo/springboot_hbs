package com.choosenfly.hotelbookingsystem.inventory.entities.discount;

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
@Table(name="discount_validity")
public class DiscountValidity extends BaseEntity { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_validity_id")
    private Long discountValidityId;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountRate discountRate;

    @Column(name = "validity_from", nullable = false)
    private Date validityFrom;

    @Column(name = "validity_to", nullable = false)
    private Date validityTo;

    @Column(name = "is_live", nullable = false)
    private boolean isLive = false;

    @Column(name = "priority", nullable = false)
    private Long priority = 0L;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

	public Long getDiscountValidityId() {
		return discountValidityId;
	}

	public void setDiscountValidityId(Long discountValidityId) {
		this.discountValidityId = discountValidityId;
	}

	public DiscountRate getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(DiscountRate discountRate) {
		this.discountRate = discountRate;
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

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    
	
}
