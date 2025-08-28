package com.choosenfly.hotelbookingsystem.api.x3.dto;

import java.util.List;

import com.choosenfly.hotelbookingsystem.api.x3.dto.search.response.HotelX3;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hotels")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class X3BaseRateHotels {

	@XmlElement
	private List<HotelX3> Hotel;

	public List<HotelX3> getHotel() {
		return Hotel;
	}

	public void setHotel(List<HotelX3> hotel) {
		Hotel = hotel;
	}

	@Override
	public String toString() {
		return "X3BaseRateHotels [Hotel=" + Hotel + "]";
	}

	
	
}
