package com.choosenfly.hotelbookingsystem.controller.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.dto.HotelDTO;
import com.choosenfly.hotelbookingsystem.service.hotel.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	
	private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelDTO> saveHotel(@Valid @RequestBody HotelDTO hotelDTO) {
      
    	HotelDTO savedHotel = hotelService.saveHotel(hotelDTO);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }
}
