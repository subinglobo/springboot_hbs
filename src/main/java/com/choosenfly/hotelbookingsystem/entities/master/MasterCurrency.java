package com.choosenfly.hotelbookingsystem.entities.master;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_currency")
public class MasterCurrency extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;


    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "value", length = 100)
    private String value;

    @Column(name = "currency_code", length = 100)
    private String currencyCode;

    // Getters and Setters
    public Long getCurrencyId() {
        return currencyId;
    }

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	@Override
	public String toString() {
		return "Currency [currencyId=" + currencyId + ", isDeleted=" + isDeleted + ", name=" + name + ", value=" + value
				+ ", currencyCode=" + currencyCode + "]";
	}

    
}