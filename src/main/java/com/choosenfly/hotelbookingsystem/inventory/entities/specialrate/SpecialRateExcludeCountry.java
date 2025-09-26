package com.choosenfly.hotelbookingsystem.inventory.entities.specialrate;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
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
@Table(name ="hotel_specialrate_exclude_country")
public class SpecialRateExcludeCountry extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exclude_country_id")
	private Long id;

	@ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private MasterCountry country;

	@ManyToOne
	@JoinColumn(name="special_rate_id",nullable = false)
	private SpecialRate specialRate;

	public Long getId() {
		return id;
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

	public SpecialRate getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(SpecialRate specialRate) {
		this.specialRate = specialRate;
	}

	@Override
	public String toString() {
		return "SpecialRateExcludeCountry [id=" + id + ", country=" + country + ", specialRate=" + specialRate + "]";
	}
	
	
	
	
}
