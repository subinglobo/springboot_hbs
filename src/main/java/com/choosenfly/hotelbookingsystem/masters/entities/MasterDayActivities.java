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
@Table(name = "master_day_activities", schema = "public")
public class MasterDayActivities extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_activit_id")
    private Long dayActivityId;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private MasterCountry country;
    
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private MasterState state;

    @Column(name = "activity_code")
    private String activityCode;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "description")
    private String description;

	public Long getDayActivityId() {
		return dayActivityId;
	}

	public void setDayActivityId(Long dayActivityId) {
		this.dayActivityId = dayActivityId;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	public MasterState getState() {
		return state;
	}

	public void setState(MasterState state) {
		this.state = state;
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
		return "MasterDayActivities [dayActivityId=" + dayActivityId + ", country=" + country + ", state=" + state
				+ ", activityCode=" + activityCode + ", activityName=" + activityName + ", description=" + description
				+ "]";
	}
    
    

    

}
