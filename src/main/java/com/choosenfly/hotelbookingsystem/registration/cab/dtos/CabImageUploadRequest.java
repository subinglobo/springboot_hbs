package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

import java.util.List;

public class CabImageUploadRequest {

	 private List<CabImageUploadDTO> cabImages;

	public List<CabImageUploadDTO> getCabImages() {
		return cabImages;
	}

	public void setCabImages(List<CabImageUploadDTO> cabImages) {
		this.cabImages = cabImages;
	}

	@Override
	public String toString() {
		return "CabImageUploadRequest [cabImages=" + cabImages + "]";
	}
	 
	 
}
