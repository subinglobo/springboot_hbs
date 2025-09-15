package com.choosenfly.hotelbookingsystem.api.x3.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Room")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoomX3Response {
	
	@XmlElement
	private double TotalRate;
	@XmlElement
	private String RoomType;
	@XmlElement
	private String RoomDescription;

	public double getTotalRate() {
		return TotalRate;
	}

	public void setTotalRate(double totalRate) {
		TotalRate = totalRate;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public String getRoomDescription() {
		return RoomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		RoomDescription = roomDescription;
	}

	@Override
	public String toString() {
		return "RoomX3Response [TotalRate=" + TotalRate + ", RoomType=" + RoomType + ", RoomDescription="
				+ RoomDescription + "]";
	}
}
