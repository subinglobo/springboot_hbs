package com.choosenfly.hotelbookingsystem.inventory.entities.discount;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="discount_exclude_country")
public class DiscountExcludedCountry extends BaseEntity{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exclude_country_id")
	private Long id;

	@ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private MasterCountry country;

	@ManyToOne
	@JoinColumn(name="discount_id",nullable = false)
	private DiscountRate discountRate;

	public Long getId() {
		return id;
	}

	public DiscountRate getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(DiscountRate discountRate) {
		this.discountRate = discountRate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	
	
	
}
