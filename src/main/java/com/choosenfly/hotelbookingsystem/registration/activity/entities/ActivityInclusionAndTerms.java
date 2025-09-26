package com.choosenfly.hotelbookingsystem.registration.activity.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "activity_inclusions_terms")
public class ActivityInclusionAndTerms extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	private String datas;
	
	private Integer type;	
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_rate_id") // foreign key
    private ActivityRate activityRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ActivityRate getActivityRate() {
		return activityRate;
	}

	public void setActivityRate(ActivityRate activityRate) {
		this.activityRate = activityRate;
	}

	@Override
	public String toString() {
		return "ActivityInclusionAndTerms [id=" + id + ", datas=" + datas + ", type=" + type + ", activityRate="
				+ activityRate + "]";
	}
    
    
	
}
