package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request;



import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Adult")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdultiwtxSearch {

	@XmlElement
	private int Age;

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}
	
	
}
