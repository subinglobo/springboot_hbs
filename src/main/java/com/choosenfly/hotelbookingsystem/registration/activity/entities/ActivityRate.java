package com.choosenfly.hotelbookingsystem.registration.activity.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "activity_rates")
public class ActivityRate extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_rate_id")
    private Long activityRateId;
    
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private ActivityProvider providerId;

    @Column(name = "activity_name")
    private String activityName;
    
    @Column(name = "activity_code")
    private String activityCode;

    @Column(name = "activity_details")
    private String activityDetails;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryid", nullable = false)
    private MasterCountry country;
    
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
    private MasterState placeId;	

    @Column(name = "activity_image")
    private String activityImage;
    
    private Integer rating;
    
    private String reportingpoint;
    
    @Column(name = "durationhr")
    private Integer durationHr;

    @Column(name = "durationmin")
    private Integer durationMin;
    
    @Column(name = "total_users_allowed")
    private Integer totalUsersAllowed;
    
    @Column(name = "child_age_min")
    private Integer childAgeMin;
    
    @Column(name = "child_age_max")
    private Integer childAgeMax;
    
    @Column(name = "activitytype")
    private String activityType;
    
    @Column(name = "activity_rate")
    private Double activityRate;
    
    @Column(name = "max_pax")
    private Integer maxPax;

    // Relationships
    @OneToMany(mappedBy = "activityRate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityRateMarketType> marketTypes = new ArrayList<>();

    @OneToMany(mappedBy = "activityRate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityRateValidity> validities = new ArrayList<>();
    
    @OneToMany(mappedBy = "activityRate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityInclusionAndTerms> inclusionsAndTerms = new ArrayList<>();

	public Long getActivityRateId() {
		return activityRateId;
	}

	public void setActivityRateId(Long activityRateId) {
		this.activityRateId = activityRateId;
	}

	

	public ActivityProvider getProviderId() {
		return providerId;
	}

	public void setProviderId(ActivityProvider providerId) {
		this.providerId = providerId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(String activityDetails) {
		this.activityDetails = activityDetails;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	public MasterState getPlaceId() {
		return placeId;
	}

	public void setPlaceId(MasterState placeId) {
		this.placeId = placeId;
	}

	public String getActivityImage() {
		return activityImage;
	}

	public void setActivityImage(String activityImage) {
		this.activityImage = activityImage;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReportingpoint() {
		return reportingpoint;
	}

	public void setReportingpoint(String reportingpoint) {
		this.reportingpoint = reportingpoint;
	}

	public Integer getDurationHr() {
		return durationHr;
	}

	public void setDurationHr(Integer durationHr) {
		this.durationHr = durationHr;
	}

	public Integer getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(Integer durationMin) {
		this.durationMin = durationMin;
	}

	public Integer getTotalUsersAllowed() {
		return totalUsersAllowed;
	}

	public void setTotalUsersAllowed(Integer totalUsersAllowed) {
		this.totalUsersAllowed = totalUsersAllowed;
	}

	public Integer getChildAgeMin() {
		return childAgeMin;
	}

	public void setChildAgeMin(Integer childAgeMin) {
		this.childAgeMin = childAgeMin;
	}

	public Integer getChildAgeMax() {
		return childAgeMax;
	}

	public void setChildAgeMax(Integer childAgeMax) {
		this.childAgeMax = childAgeMax;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Double getActivityRate() {
		return activityRate;
	}

	public void setActivityRate(Double activityRate) {
		this.activityRate = activityRate;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public List<ActivityRateMarketType> getMarketTypes() {
		return marketTypes;
	}

	public void setMarketTypes(List<ActivityRateMarketType> marketTypes) {
		this.marketTypes = marketTypes;
	}

	public List<ActivityRateValidity> getValidities() {
		return validities;
	}

	public void setValidities(List<ActivityRateValidity> validities) {
		this.validities = validities;
	}

	
	public List<ActivityInclusionAndTerms> getInclusionsAndTerms() {
		return inclusionsAndTerms;
	}

	public void setInclusionsAndTerms(List<ActivityInclusionAndTerms> inclusionsAndTerms) {
		this.inclusionsAndTerms = inclusionsAndTerms;
	}

	
	@Override
	public String toString() {
		return "ActivityRate [activityRateId=" + activityRateId + ", providerId=" + providerId + ", activityName="
				+ activityName + ", activityCode=" + activityCode + ", activityDetails=" + activityDetails
				+ ", country=" + country + ", placeId=" + placeId + ", activityImage=" + activityImage + ", rating="
				+ rating + ", reportingpoint=" + reportingpoint + ", durationHr=" + durationHr + ", durationMin="
				+ durationMin + ", totalUsersAllowed=" + totalUsersAllowed + ", childAgeMin=" + childAgeMin
				+ ", childAgeMax=" + childAgeMax + ", activityType=" + activityType + ", activityRate=" + activityRate
				+ ", maxPax=" + maxPax + ", marketTypes=" + marketTypes + ", validities=" + validities
				+ ", inclusionsAndTerms=" + inclusionsAndTerms + "]";
	}
    
    
}
