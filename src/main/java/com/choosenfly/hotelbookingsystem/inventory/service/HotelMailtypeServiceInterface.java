package com.choosenfly.hotelbookingsystem.inventory.service;

import java.util.List;

import com.choosenfly.hotelbookingsystem.inventory.dto.HotelContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelMailCentreDTO;

public interface HotelMailtypeServiceInterface {

	
	public String addMailCentre(Long hotelId, HotelMailCentreDTO mailCentre);
	
	public List<HotelContactDetailsDTO> getMailCentre(Long hotelId);
}
