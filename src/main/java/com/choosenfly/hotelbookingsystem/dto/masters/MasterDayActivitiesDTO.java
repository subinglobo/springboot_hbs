package com.choosenfly.hotelbookingsystem.dto.masters;

public class MasterDayActivitiesDTO {

    private Long dayActivityId;
    
    private Long countryId;
    
    private Long stateId;
    
    private String activityCode;
    
    private String activityName;

    private String description;

	public Long getDayActivityId() {
		return dayActivityId;
	}

	public void setDayActivityId(Long dayActivityId) {
		this.dayActivityId = dayActivityId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MasterDayActivitiesDTO [dayActivityId=" + dayActivityId + ", countryId=" + countryId + ", stateId="
				+ stateId + ", activityCode=" + activityCode + ", activityName=" + activityName + ", description="
				+ description + "]";
	}

    
    
}
