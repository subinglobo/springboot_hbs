package com.choosenfly.hotelbookingsystem.inventory.entities.discount;

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
@Table(name="discount_markettype")
public class DiscountMarketType extends BaseEntity  { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="discount_markettype_id")
	private Long discountMarkettypeId;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountRate discountRate;
    
	@ManyToOne
	@JoinColumn(name="markettype_id", nullable = false)
	private MasterMarketType marketType;

	public Long getDiscountMarkettypeId() {
		return discountMarkettypeId;
	}

	public void setDiscountMarkettypeId(Long discountMarkettypeId) {
		this.discountMarkettypeId = discountMarkettypeId;
	}

	public DiscountRate getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(DiscountRate discountRate) {
		this.discountRate = discountRate;
	}

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}
	
	
	
}


