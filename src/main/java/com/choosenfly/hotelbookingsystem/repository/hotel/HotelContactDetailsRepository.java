package com.choosenfly.hotelbookingsystem.repository.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.HotelContactDetails;

@Repository
public interface HotelContactDetailsRepository extends JpaRepository<HotelContactDetails, Long> {

}
