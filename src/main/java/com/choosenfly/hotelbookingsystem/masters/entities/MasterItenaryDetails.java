package com.choosenfly.hotelbookingsystem.masters.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Master_itenary_etails", schema = "public")
public class MasterItenaryDetails extends BaseEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itineary_id")
    private Long itineraryId;

    @Column(name = "itineary_code")
    private String itineraryCode;

    @Column(name = "itineary_desc")
    private String itineraryDesc;

    @Column(name = "itineary_heading")
    private String itineraryHeading;

    @Column(name = "itineary_img")
    private String itineraryImg;

	public Long getItineraryId() {
		return itineraryId;
	}

	public void setItineraryId(Long itineraryId) {
		this.itineraryId = itineraryId;
	}

	public String getItineraryCode() {
		return itineraryCode;
	}

	public void setItineraryCode(String itineraryCode) {
		this.itineraryCode = itineraryCode;
	}

	public String getItineraryDesc() {
		return itineraryDesc;
	}

	public void setItineraryDesc(String itineraryDesc) {
		this.itineraryDesc = itineraryDesc;
	}

	public String getItineraryHeading() {
		return itineraryHeading;
	}

	public void setItineraryHeading(String itineraryHeading) {
		this.itineraryHeading = itineraryHeading;
	}

	public String getItineraryImg() {
		return itineraryImg;
	}

	public void setItineraryImg(String itineraryImg) {
		this.itineraryImg = itineraryImg;
	}

	@Override
	public String toString() {
		return "MasterItenaryDetails [itineraryId=" + itineraryId + ", itineraryCode=" + itineraryCode
				+ ", itineraryDesc=" + itineraryDesc + ", itineraryHeading=" + itineraryHeading + ", itineraryImg="
				+ itineraryImg + "]";
	}  
	
	

}
