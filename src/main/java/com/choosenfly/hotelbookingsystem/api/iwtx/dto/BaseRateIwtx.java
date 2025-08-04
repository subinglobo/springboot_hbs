package com.choosenfly.hotelbookingsystem.api.iwtx.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotelSearchResponse")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BaseRateIwtx {
	
	@XmlElement(name ="Hotels")
	private IwtxBaseRateHotels Hotels;
	
	private String errorMsg;

	
	public IwtxBaseRateHotels getHotels() {
		return Hotels;
	}

	public void setHotels(IwtxBaseRateHotels hotels) {
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
		return "BaseRateIwtx [Hotels=" + Hotels + ", errorMsg=" + errorMsg + "]";
	}
	
	

}
