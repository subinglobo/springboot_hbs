package com.choosenfly.hotelbookingsystem.service.hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.hotel.HotelDTO;

public interface HotelRegistrationServiceInterface {

	
	HotelDTO saveHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long id);

    Page<HotelDTO> getHotels(Pageable pageable, String search);

    HotelDTO updateHotel(HotelDTO hotelDTO, Long id);

    ResponseEntity<String> deleteHotel(Long id);
}
