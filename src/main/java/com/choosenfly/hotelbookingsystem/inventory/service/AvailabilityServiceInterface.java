package com.choosenfly.hotelbookingsystem.inventory.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.dto.BlockCheckInAndCheckOutDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelListAvailabilityDTO;

public interface AvailabilityServiceInterface {

	
	HotelAvailabilityDTO addAvailability(HotelAvailabilityDTO availability);

	List<HotelListAvailabilityDTO> getAvailabilities(Long hotelId);

	HotelAvailabilityDTO editAvailability(Long hotelId, HotelAvailabilityDTO availability);

	void deleteAvailability(Long hotelId, Long availabilityId);

	HotelAvailabilityDTO getAvailabilityOfAHotel(Long hotelId, Long availabilityId);

	BlockCheckInAndCheckOutDTO addBlockDatestToHotel(BlockCheckInAndCheckOutDTO request);
	
	
}
