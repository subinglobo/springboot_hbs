package com.choosenfly.hotelbookingsystem.api.x3.dto.search.response;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rooms")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoomsX3Response {
	
	@XmlElement
	private List<RoomX3Response> Room;

	public List<RoomX3Response> getRoom() {
		return Room;
	}

	public void setRoom(List<RoomX3Response> room) {
		Room = room;
	}

	@Override
	public String toString() {
		return "RoomsX3Response [Room=" + Room + "]";
	}
}
