package com.choosenfly.hotelbookingsystem.inventory.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.hotel.entities.HotelBankDetails;

@Repository
public interface HotelBankDetailsRepository extends JpaRepository<HotelBankDetails, Long>{

}
