package com.choosenfly.hotelbookingsystem.registration.cab.service;

import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabImageUploadRequest;

public interface CabImageUploadService {

	void saveCabImages(CabImageUploadRequest request);

	void updateCabImages(CabImageUploadRequest request);

	void deleteCabImages(Long id);

}
