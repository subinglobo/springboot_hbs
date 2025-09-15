package com.choosenfly.hotelbookingsystem.api.x3.dto.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Room")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomX3Search {

	@XmlElement
	private AdultX3Search[] Adult;
	
	@XmlElement
	private ChildX3Search[] Child;

	/**
	 * @return the adult
	 */
	public AdultX3Search[] getAdult() {
		return Adult;
	}

	/**
	 * @param adult the adult to set
	 */
	public void setAdult(AdultX3Search[] adult) {
		Adult = adult;
	}

	/**
	 * @return the child
	 */
	public ChildX3Search[] getChild() {
		return Child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(ChildX3Search[] child) {
		Child = child;
	}
	
	
	
	
}
