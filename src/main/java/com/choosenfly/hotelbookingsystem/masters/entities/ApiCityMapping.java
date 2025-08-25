package com.choosenfly.hotelbookingsystem.masters.entities;

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
@Table(name = "api_city_mapping", schema = "public")
public class ApiCityMapping extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "api_provider", length = 50)
	private String apiProvider ;		//platform
	
	@ManyToOne
	@JoinColumn(name = "master_country_id", nullable = false)
	private MasterCountry masterCountry;

	@ManyToOne
	@JoinColumn(name = "master_city_id", nullable = false)
	private MasterPlace masterCity;
	
	@Column(name = "api_country_id", length = 50)
	private String apiCountryId ;
	
	@Column(name = "api_country_code", length = 10)
	private String apiCountryCode ;
	
	@Column(name = "api_city_id", length = 50)
	private String apiCityId  ;
	
	@Column(name = "api_city_code", length = 50)
	private String apiCityCode ;
	
	@Column(name = "api_hotel_code_list" , columnDefinition = "TEXT")
	private String apiHotelCodeList ;
	
	@Column(name = "is_deleted", length = 5)
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiProvider() {
		return apiProvider;
	}

	public void setApiProvider(String apiProvider) {
		this.apiProvider = apiProvider;
	}

	public MasterCountry getMasterCountry() {
		return masterCountry;
	}

	public void setMasterCountry(MasterCountry masterCountry) {
		this.masterCountry = masterCountry;
	}

	public MasterPlace getMasterCity() {
		return masterCity;
	}

	public void setMasterCity(MasterPlace masterCity) {
		this.masterCity = masterCity;
	}

	public String getApiCountryId() {
		return apiCountryId;
	}

	public void setApiCountryId(String apiCountryId) {
		this.apiCountryId = apiCountryId;
	}

	public String getApiCountryCode() {
		return apiCountryCode;
	}

	public void setApiCountryCode(String apiCountryCode) {
		this.apiCountryCode = apiCountryCode;
	}

	public String getApiCityId() {
		return apiCityId;
	}

	public void setApiCityId(String apiCityId) {
		this.apiCityId = apiCityId;
	}

	public String getApiCityCode() {
		return apiCityCode;
	}

	public void setApiCityCode(String apiCityCode) {
		this.apiCityCode = apiCityCode;
	}

	public String getApiHotelCodeList() {
		return apiHotelCodeList;
	}

	public void setApiHotelCodeList(String apiHotelCodeList) {
		this.apiHotelCodeList = apiHotelCodeList;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "ApiCityMapping [id=" + id + ", apiProvider=" + apiProvider + ", masterCountry=" + masterCountry
				+ ", masterCity=" + masterCity + ", apiCountryId=" + apiCountryId + ", apiCountryCode=" + apiCountryCode
				+ ", apiCityId=" + apiCityId + ", apiCityCode=" + apiCityCode + ", apiHotelCodeList=" + apiHotelCodeList
				+ ", isDeleted=" + isDeleted + "]";
	}

	

	
	
	

}
