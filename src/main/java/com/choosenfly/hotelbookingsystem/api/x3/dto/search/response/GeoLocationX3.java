package com.choosenfly.hotelbookingsystem.api.x3.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GeoLocation")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GeoLocationX3 {
	
	@XmlElement
	private String Latitude;
	@XmlElement
	private String Longitude;
	
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "GeoLocationX3 [Latitude=" + Latitude + ", Longitude=" + Longitude + "]";
	}
}
