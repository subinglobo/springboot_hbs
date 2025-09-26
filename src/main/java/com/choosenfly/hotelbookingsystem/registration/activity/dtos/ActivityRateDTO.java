package com.choosenfly.hotelbookingsystem.registration.activity.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public class ActivityRateDTO {


    private Long activityRateId;
    private Long providerId;

    @NotBlank(message = "Activity name is required")
    private String activityName;
    
    @NotBlank(message = "Activity code is required")
    private String activityCode;
    
    @NotBlank(message = "Activity details is required")
    private String activityDetails;
    private MultipartFile activityImage;
    
    private String imagePath;

    private Integer childAgeMin;
    private Integer childAgeMax;

    private Integer totalUsersAllowed;
    private Double activityRate;
    private Integer maxPax;
    private String activityType;

    private List<Long> marketType; // list of IDs

    private Long countryId;
    private Long placeId;

    private List<ActivityValidityDTO> validity;

    private Integer durationHr;
    private Integer durationMin;

    private String reportingPoint;
    private Integer rating;
    

	public Long getActivityRateId() {
		return activityRateId;
	}
	public void setActivityRateId(Long activityRateId) {
		this.activityRateId = activityRateId;
	}
	public Long getProviderId() {
		return providerId;
	}
	public void setProviderId(Long providerId) {
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
	public Integer getTotalUsersAllowed() {
		return totalUsersAllowed;
	}
	public void setTotalUsersAllowed(Integer totalUsersAllowed) {
		this.totalUsersAllowed = totalUsersAllowed;
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
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	public List<Long> getMarketType() {
		return marketType;
	}
	public void setMarketType(List<Long> marketType) {
		this.marketType = marketType;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public Long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
	public List<ActivityValidityDTO> getValidity() {
		return validity;
	}
	public void setValidity(List<ActivityValidityDTO> validity) {
		this.validity = validity;
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
	public String getReportingPoint() {
		return reportingPoint;
	}
	public void setReportingPoint(String reportingPoint) {
		this.reportingPoint = reportingPoint;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	public MultipartFile getActivityImage() {
		return activityImage;
	}
	public void setActivityImage(MultipartFile activityImage) {
		this.activityImage = activityImage;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	
	@Override
	public String toString() {
		return "ActivityRateDTO [activityRateId=" + activityRateId + ", providerId=" + providerId + ", activityName="
				+ activityName + ", activityCode=" + activityCode + ", activityDetails=" + activityDetails
				+ ", activityImage=" + activityImage + ", imagePath=" + imagePath + ", childAgeMin=" + childAgeMin
				+ ", childAgeMax=" + childAgeMax + ", totalUsersAllowed=" + totalUsersAllowed + ", activityRate="
				+ activityRate + ", maxPax=" + maxPax + ", activityType=" + activityType + ", marketType=" + marketType
				+ ", countryId=" + countryId + ", placeId=" + placeId + ", validity=" + validity + ", durationHr="
				+ durationHr + ", durationMin=" + durationMin + ", reportingPoint=" + reportingPoint + ", rating="
				+ rating + "]";
	}
    
    
}
