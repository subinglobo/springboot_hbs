package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Room")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomiwxtSearch {

	@XmlElement
	private AdultiwtxSearch[] Adult;
	
	@XmlElement
	private ChildiwtxSearch[] Child;

	/**
	 * @return the adult
	 */
	public AdultiwtxSearch[] getAdult() {
		return Adult;
	}

	/**
	 * @param adult the adult to set
	 */
	public void setAdult(AdultiwtxSearch[] adult) {
		Adult = adult;
	}

	/**
	 * @return the child
	 */
	public ChildiwtxSearch[] getChild() {
		return Child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(ChildiwtxSearch[] child) {
		Child = child;
	}
	
	
	
	
}
