package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.response;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rooms")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoomsIwtx {
	
	@XmlElement
	private RoomIwtxResponse[] Room;

	/**
	 * @return the room
	 */
//	@JsonProperty("Room")
//	@XmlAttribute(name = "Room")
	public RoomIwtxResponse[] getRoom() {
		return Room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(RoomIwtxResponse[] room) {
		Room = room;
	}

	@Override
	public String toString() {
		return "Rooms [Room=" + Arrays.toString(Room) + "]";
	}
	
	
}
