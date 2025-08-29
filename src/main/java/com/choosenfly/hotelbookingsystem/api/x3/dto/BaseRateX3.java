package com.choosenfly.hotelbookingsystem.api.x3.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotelSearchResponse")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BaseRateX3 {
	
	@XmlElement(name ="Hotels")
	private X3BaseRateHotels Hotels;
	
	private String errorMsg;

	
	public X3BaseRateHotels getHotels() {
		return Hotels;
	}

	public void setHotels(X3BaseRateHotels hotels) {
		Hotels = hotels;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "BaseRateX3 [Hotels=" + Hotels + ", errorMsg=" + errorMsg + "]";
	}
	
	

}
