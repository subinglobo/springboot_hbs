package com.choosenfly.hotelbookingsystem.masters.dto;

import org.springframework.web.multipart.MultipartFile;

public class MasterItenaryDetailsDTO {

    private Long itineraryId;
    
    private String itineraryCode;
    
    private String itineraryDesc;
    
    private String itineraryHeading;
    
    private MultipartFile itineraryImg;
    
    private String imagePath;

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

	public MultipartFile getItineraryImg() {
		return itineraryImg;
	}

	public void setItineraryImg(MultipartFile itineraryImg) {
		this.itineraryImg = itineraryImg;
	}

	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	@Override
	public String toString() {
		return "MasterItenaryDetailsDTO [itineraryId=" + itineraryId + ", itineraryCode=" + itineraryCode
				+ ", itineraryDesc=" + itineraryDesc + ", itineraryHeading=" + itineraryHeading + ", itineraryImg="
				+ itineraryImg + ", imagePath=" + imagePath + "]";
	}  
    
    

}
