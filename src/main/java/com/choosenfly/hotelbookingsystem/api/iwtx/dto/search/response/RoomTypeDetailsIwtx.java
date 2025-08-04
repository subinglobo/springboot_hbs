package com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RoomTypeDetails")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoomTypeDetailsIwtx {

	@XmlElement
	private RoomsIwtx Rooms;

	
	private double baseRate;
	
	/**
	 * @return the rooms
	 */
//	@JsonProperty("Rooms")
//	@XmlAttribute(name = "Rooms")
	public RoomsIwtx getRooms() {
		return Rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(RoomsIwtx rooms) {
		Rooms = rooms;
	}

	public double getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(double baseRate) {
		this.baseRate = baseRate;
	}

	@Override
	public String toString() {
		return "RoomTypeDetails [Rooms=" + Rooms + ", baseRate=" + baseRate + "]";
	}

	
	
	
}
