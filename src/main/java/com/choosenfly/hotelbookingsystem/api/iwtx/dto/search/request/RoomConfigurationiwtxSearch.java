package com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RoomConfiguration")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomConfigurationiwtxSearch {
	
	@XmlElement
	private RoomiwxtSearch Room;

	public RoomiwxtSearch getRoom() {
		return Room;
	}

	public void setRoom(RoomiwxtSearch room) {
		Room = room;
	}
	
	

}
