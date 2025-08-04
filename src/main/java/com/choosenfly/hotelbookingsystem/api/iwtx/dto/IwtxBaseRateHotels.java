package com.choosenfly.hotelbookingsystem.api.iwtx.dto;

import java.util.List;

import com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.response.HotelIwtx;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hotels")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class IwtxBaseRateHotels {

	@XmlElement
	private List<HotelIwtx> Hotel;

	public List<HotelIwtx> getHotel() {
		return Hotel;
	}

	public void setHotel(List<HotelIwtx> hotel) {
		Hotel = hotel;
	}

	@Override
	public String toString() {
		return "IwtxBaseRateHotels [Hotel=" + Hotel + "]";
	}

	
	
}
