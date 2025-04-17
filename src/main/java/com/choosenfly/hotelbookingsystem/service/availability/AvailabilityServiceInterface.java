package com.choosenfly.hotelbookingsystem.service.availability;

import java.util.List;

import com.choosenfly.hotelbookingsystem.dto.availability.HotelAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.dto.availability.HotelListAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.dto.blockCheckinCheckout.BlockCheckInAndCheckOutDTO;

public interface AvailabilityServiceInterface {

	
	HotelAvailabilityDTO addAvailability(HotelAvailabilityDTO availability);

	List<HotelListAvailabilityDTO> getAvailabilities(Long hotelId);

	HotelAvailabilityDTO editAvailability(Long hotelId, HotelAvailabilityDTO availability);

	void deleteAvailability(Long hotelId, Long availabilityId);

	HotelAvailabilityDTO getAvailabilityOfAHotel(Long hotelId, Long availabilityId);

	BlockCheckInAndCheckOutDTO addBlockDatestToHotel(BlockCheckInAndCheckOutDTO request);
	
	
}
