package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GeoLocation")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GeoLocationIwtx {

	@XmlElement(name = "Latitude")
	 private double Latitude;
	
	@XmlElement(name = "Longitude")
   private double Longitude;

	/**
	 * @return the latitude
	 */
//   @JsonProperty("Latitude")
//	@XmlAttribute(name = "Latitude")
	public double getLatitude() {
		return Latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
//	@JsonProperty("Longitude")
//	@XmlAttribute(name = "Longitude")
	public double getLongitude() {
		return Longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
    
    
}
