package com.choosenfly.hotelbookingsystem.registration.activity.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

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
@Table(name = "activityrates_validity")
public class ActivityRateValidity extends BaseEntity{

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "validity_id")
	    private Long validityId;


	    @Column(name = "validity_from")
	    private String validityFrom;

	    @Column(name = "validity_To")
	    private String validityTo;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "activity_rate_id", nullable = false)
	    private ActivityRate activityRate;

		public Long getValidityId() {
			return validityId;
		}

		public void setValidityId(Long validityId) {
			this.validityId = validityId;
		}

		

		public String getValidityFrom() {
			return validityFrom;
		}

		public void setValidityFrom(String validityFrom) {
			this.validityFrom = validityFrom;
		}

		public String getValidityTo() {
			return validityTo;
		}

		public void setValidityTo(String validityTo) {
			this.validityTo = validityTo;
		}

		public ActivityRate getActivityRate() {
			return activityRate;
		}

		public void setActivityRate(ActivityRate activityRate) {
			this.activityRate = activityRate;
		}

		
		@Override
		public String toString() {
			return "ActivityRateValidity [validityId=" + validityId + ", validityFrom=" + validityFrom + ", validityTo="
					+ validityTo + ", activityRate=" + activityRate + "]";
		}
	    
	  
	    
	
}
