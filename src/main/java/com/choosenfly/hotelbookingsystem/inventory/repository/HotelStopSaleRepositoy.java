package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.hotelstopsale.HotelStopSale;





@Repository
public interface HotelStopSaleRepositoy extends JpaRepository<HotelStopSale, Long>{


//		Page<HotelStopSale> findByTermsCodeContainingIgnoreCase(String name, Pageable pageable); 
	
}
