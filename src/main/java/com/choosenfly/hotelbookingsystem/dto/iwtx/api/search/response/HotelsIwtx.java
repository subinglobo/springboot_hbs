package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hotels")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class HotelsIwtx  {

	@XmlElement
	private HotelIwtx Hotel;

	
	public HotelIwtx getHotel() {
		return Hotel;
	}

	
	public void setHotel(HotelIwtx hotel) {
		Hotel = hotel;
	}


	@Override
	public String toString() {
		return "Hotels [Hotel=" + Hotel + "]";
	}
	
	
}
