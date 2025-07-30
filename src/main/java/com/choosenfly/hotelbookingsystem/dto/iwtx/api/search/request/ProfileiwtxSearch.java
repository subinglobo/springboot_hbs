package com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfileiwtxSearch {

	@XmlElement
	private String Password;
	@XmlElement
	private String Code;
	@XmlElement
	private String TokenNumber;
	
	public void setPassword(String password) {
		Password = password;
	}
	public void setCode(String code) {
		Code = code;
	}
	public void setTokenNumber(String tokenNumber) {
		TokenNumber = tokenNumber;
	}
	
	
	
}
