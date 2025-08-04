package com.choosenfly.hotelbookingsystem.inventory.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.hotel.entities.HotelRoom;

@Repository
public interface HotelRoomRepository  extends JpaRepository<HotelRoom, Long>{

}
