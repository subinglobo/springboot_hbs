package com.choosenfly.hotelbookingsystem.inventory.entities.specialrate;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StayPay;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name ="hotel_special_rate_combined")
public class SpecialRateCombined extends BaseEntity{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "special_combined_id")
	private Long specialCombinedId;

	@OneToOne
	@JoinColumn(name= "special_rate_id")
	private SpecialRate specialRate;
	
	@Column(length=100)
	private String promotype;
	
	@Any
	@AnyDiscriminator(DiscriminatorType.STRING)  // old metaType
	@AnyKeyJavaClass(Long.class)                 // old idType
	@Column(name = "promotion_type")             // discriminator column
	@JoinColumn(name = "promotion_id")           // foreign key column
	@AnyDiscriminatorValue(discriminator = "DISCOUNT", entity = DiscountRate.class)
	@AnyDiscriminatorValue(discriminator = "STAYPAY", entity = StayPay.class)
	private Object promotion;

	public Long getSpecialCombinedId() {
		return specialCombinedId;
	}

	public void setSpecialCombinedId(Long specialCombinedId) {
		this.specialCombinedId = specialCombinedId;
	}

	public SpecialRate getSpecialRate() {
		return specialRate;
	}

	public void setSpecialRate(SpecialRate specialRate) {
		this.specialRate = specialRate;
	}

	public String getPromotype() {
		return promotype;
	}

	public void setPromotype(String promotype) {
		this.promotype = promotype;
	}

	

	
	public Object getPromotion() {
		return promotion;
	}

	public void setPromotion(Object promotion) {
		this.promotion = promotion;
	}

	@Override
	public String toString() {
		return "SpecialRateCombined [specialCombinedId=" + specialCombinedId + ", specialRate=" + specialRate
				+ ", promotype=" + promotype + ", promotion=" + promotion + "]";
	}


	
	
	
	
	
}
