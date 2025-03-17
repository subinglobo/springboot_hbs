package com.choosenfly.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.linked.LinkedHotelRoomAmenity;

@Repository
public interface LinkedHotelRoomAmenityRepository extends JpaRepository<LinkedHotelRoomAmenity, Long> {

}
