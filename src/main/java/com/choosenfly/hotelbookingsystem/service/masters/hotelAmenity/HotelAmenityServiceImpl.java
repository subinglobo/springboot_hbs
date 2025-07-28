package com.choosenfly.hotelbookingsystem.service.masters.hotelAmenity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelAmenityDTO;

public interface HotelAmenityServiceImpl {

	Long saveHotelAmenity(MasterHotelAmenityDTO dto);

	MasterHotelAmenityDTO getHotelAmenityById(Long id);

	MasterHotelAmenityDTO editHotelAmenity(Long id,MasterHotelAmenityDTO amenityDTO);

	ResponseEntity<String> deleteHotelAmenity(Long id);

	Page<MasterHotelAmenityDTO> getAllHotelAmenities(Pageable pageable, String search);

}
