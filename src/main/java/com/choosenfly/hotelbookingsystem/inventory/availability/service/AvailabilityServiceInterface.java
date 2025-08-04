package com.choosenfly.hotelbookingsystem.inventory.availability.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.availability.dto.HotelAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.inventory.availability.dto.HotelListAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.dto.BlockCheckInAndCheckOutDTO;

public interface AvailabilityServiceInterface {

	
	HotelAvailabilityDTO addAvailability(HotelAvailabilityDTO availability);

	List<HotelListAvailabilityDTO> getAvailabilities(Long hotelId);

	HotelAvailabilityDTO editAvailability(Long hotelId, HotelAvailabilityDTO availability);

	void deleteAvailability(Long hotelId, Long availabilityId);

	HotelAvailabilityDTO getAvailabilityOfAHotel(Long hotelId, Long availabilityId);

	BlockCheckInAndCheckOutDTO addBlockDatestToHotel(BlockCheckInAndCheckOutDTO request);
	
	
}
