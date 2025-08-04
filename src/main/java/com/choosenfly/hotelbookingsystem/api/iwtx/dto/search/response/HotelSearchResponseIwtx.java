package com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotelSearchResponse")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class HotelSearchResponseIwtx  {
	
	@XmlElement(name ="Hotels")
	private HotelsIwtx Hotels;
	
	private String errorMsg;
	
	

	
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}


	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public HotelsIwtx getHotels() {
		return Hotels;
	}

	
	public void setHotels(HotelsIwtx hotels) {
		Hotels = hotels;
	}


	@Override
	public String toString() {
		return "HotelSearchResponse [Hotels=" + Hotels + "]";
	}
	
	

}
