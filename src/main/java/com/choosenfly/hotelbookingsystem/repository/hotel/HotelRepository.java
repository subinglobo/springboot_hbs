package com.choosenfly.hotelbookingsystem.repository.hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	Page<Hotel> findByHotelNameContainingIgnoreCase(String hotelName, Pageable pageable);
}