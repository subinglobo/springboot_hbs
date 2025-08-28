package com.choosenfly.hotelbookingsystem.api.x3.dto.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RoomConfiguration")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomConfigurationX3Search {
	
	@XmlElement
	private RoomX3Search Room;

	public RoomX3Search getRoom() {
		return Room;
	}

	public void setRoom(RoomX3Search room) {
		Room = room;
	}
	
	

}
