package com.choosenfly.hotelbookingsystem.service.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.dto.HotelDTO;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.util.HotelMapper;

@Service
public class HotelService {

	private final HotelRepository hotelRepository;
	
	private final HotelMapper hotelMapper;

	
	@Autowired
	public HotelService(HotelRepository hotelRepository,HotelMapper hotelMapper ) {
		this.hotelRepository = hotelRepository;
		this.hotelMapper = hotelMapper;
	}

	@Transactional
	public HotelDTO saveHotel(HotelDTO hotelDTO) {
		
		Hotel hotel = hotelMapper.mapToEntity(hotelDTO);
		
		Hotel savedHotel = hotelRepository.save(hotel);

		return hotelMapper.mapToDTO(savedHotel);
	}

	@Transactional(readOnly = true)
	public HotelDTO getHotelById(Long id) {
		
		
		Hotel hotel = hotelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
		
		return hotelMapper.mapToDTO(hotel);
	}

	
}