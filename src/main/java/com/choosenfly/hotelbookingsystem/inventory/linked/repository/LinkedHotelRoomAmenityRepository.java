package com.choosenfly.hotelbookingsystem.inventory.linked.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.hotel.linked.entities.LinkedHotelRoomAmenity;

@Repository
public interface LinkedHotelRoomAmenityRepository extends JpaRepository<LinkedHotelRoomAmenity, Long> {

}
