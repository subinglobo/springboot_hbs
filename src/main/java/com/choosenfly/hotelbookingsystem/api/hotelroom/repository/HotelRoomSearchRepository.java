package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomSearchRepository extends JpaRepository<HotelRoomCategory, Long> {

    @Query("SELECT hrc FROM HotelRoomCategory hrc " +
           "LEFT JOIN FETCH hrc.roomCategory " +
           "WHERE hrc.hotel.hotelId = :hotelId")
    List<HotelRoomCategory> findHotelRooms(@Param("hotelId") Long hotelId);
}
