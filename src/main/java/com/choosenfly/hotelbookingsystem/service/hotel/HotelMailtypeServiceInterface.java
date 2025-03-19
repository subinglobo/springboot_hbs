package com.choosenfly.hotelbookingsystem.service.hotel;

import java.util.List;

import com.choosenfly.hotelbookingsystem.dto.hotel.HotelContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.dto.hotel.HotelMailCentreDTO;

public interface HotelMailtypeServiceInterface {

	
	public String addMailCentre(Long hotelId, HotelMailCentreDTO mailCentre);
	
	public List<HotelContactDetailsDTO> getMailCentre(Long hotelId);
}
