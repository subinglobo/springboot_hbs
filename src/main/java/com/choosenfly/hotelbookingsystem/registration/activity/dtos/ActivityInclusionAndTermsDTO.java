package com.choosenfly.hotelbookingsystem.registration.activity.dtos;

public class ActivityInclusionAndTermsDTO {

	private Long id;
	
	private String data;
	
	private Integer type;
	
	private Long activityRateId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getActivityRateId() {
		return activityRateId;
	}

	public void setActivityRateId(Long activityRateId) {
		this.activityRateId = activityRateId;
	}

	@Override
	public String toString() {
		return "ActivityInclusionAndTermsDTO [id=" + id + ", data=" + data + ", type=" + type + ", activityRateId="
				+ activityRateId + "]";
	}
	
	
}
