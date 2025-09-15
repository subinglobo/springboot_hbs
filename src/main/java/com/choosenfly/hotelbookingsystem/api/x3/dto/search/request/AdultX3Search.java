package com.choosenfly.hotelbookingsystem.api.x3.dto.search.request;



import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Adult")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdultX3Search {

	@XmlElement
	private int Age;

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}
	
	
}
