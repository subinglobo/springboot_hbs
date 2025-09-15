package com.choosenfly.hotelbookingsystem.masters.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "darina_country_list", schema = "public")
public class DarinaCountryList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "darina_country_id", nullable = false)
	private Long id;
	
	@Column(name = "country_code", nullable = false)
	private String countryCode;
	
	@Column(name = "country_name", nullable = false)
	private String countryName;
	
	@Column(name = "country_id", nullable = false)
	private Long countryId;
	
	@Column(name = "inhouse_country_id", nullable = false)
	private Long inhouseCountryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getInhouseCountryId() {
		return inhouseCountryId;
	}

	public void setInhouseCountryId(Long inhouseCountryId) {
		this.inhouseCountryId = inhouseCountryId;
	}

	@Override
	public String toString() {
		return "DarinaCountryList [id=" + id + ", countryCode=" + countryCode + ", countryName=" + countryName
				+ ", countryId=" + countryId + ", inhouseCountryId=" + inhouseCountryId + "]";
	}
	
	
}
