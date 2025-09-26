package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StayPay;

public interface HotelStaypayRepository extends JpaRepository<StayPay, Long>  {
	
	

}
