package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoom;

@Repository
public interface HotelRoomRepository  extends JpaRepository<HotelRoom, Long>{

}
