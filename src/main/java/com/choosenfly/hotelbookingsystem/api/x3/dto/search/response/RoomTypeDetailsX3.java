package com.choosenfly.hotelbookingsystem.api.x3.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RoomTypeDetails")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoomTypeDetailsX3 {
	
	@XmlElement
	private RoomsX3Response Rooms;

	public RoomsX3Response getRooms() {
		return Rooms;
	}

	public void setRooms(RoomsX3Response rooms) {
		Rooms = rooms;
	}

	@Override
	public String toString() {
		return "RoomTypeDetailsX3 [Rooms=" + Rooms + "]";
	}
}
