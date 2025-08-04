package com.choosenfly.hotelbookingsystem.inventory.hotel.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.hotel.dto.HotelContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.inventory.hotel.dto.HotelMailCentreDTO;

public interface HotelMailtypeServiceInterface {

	
	public String addMailCentre(Long hotelId, HotelMailCentreDTO mailCentre);
	
	public List<HotelContactDetailsDTO> getMailCentre(Long hotelId);
}
