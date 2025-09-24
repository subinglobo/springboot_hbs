package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

import org.springframework.web.multipart.MultipartFile;

public class CabImageUploadDTO {

    private Long cabId;
    
    private MultipartFile file;

	public Long getCabId() {
		return cabId;
	}

	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "CabImageUploadDTO [cabId=" + cabId + ", file=" + file + "]";
	}
    
    
}
