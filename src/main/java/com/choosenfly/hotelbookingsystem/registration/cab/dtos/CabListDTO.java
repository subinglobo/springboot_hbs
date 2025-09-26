package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

public class CabListDTO {

	private Long cabId;
	
	private String cabName;

	public Long getCabId() {
		return cabId;
	}

	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}

	public String getCabName() {
		return cabName;
	}

	public void setCabName(String cabName) {
		this.cabName = cabName;
	}

	@Override
	public String toString() {
		return "CabListDTO [cabId=" + cabId + ", cabName=" + cabName + "]";
	}
	
	
}
