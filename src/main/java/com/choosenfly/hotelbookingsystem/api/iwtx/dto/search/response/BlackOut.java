package com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BlackOut")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BlackOut {
	@XmlElement
	private String  Status;
	@XmlElement
	private String Msg;
	/**
	 * @return the status
	 */
//	@JsonProperty("Status")
//	@XmlAttribute(name = "Status")
	public String getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}
	/**
	 * @return the msg
	 */
//	@JsonProperty("Msg")
//	@XmlAttribute(name = "Msg")
	public String getMsg() {
		return Msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		Msg = msg;
	}
	@Override
	public String toString() {
		return "BlackOut [Status=" + Status + ", Msg=" + Msg + "]";
	}
	
	
	

}
