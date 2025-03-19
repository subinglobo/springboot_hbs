package com.choosenfly.hotelbookingsystem.repository.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.HotelBankDetails;

@Repository
public interface HotelBankDetailsRepository extends JpaRepository<HotelBankDetails, Long>{

}
