package com.choosenfly.hotelbookingsystem.repository.hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.hotelstopsale.HotelStopSale;

@Repository
public interface HotelStopSaleRepositoy extends JpaRepository<HotelStopSale, Long>{


//		Page<HotelStopSale> findByTermsCodeContainingIgnoreCase(String name, Pageable pageable); 
	
}
