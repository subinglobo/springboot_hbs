package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelWeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRoomWeekDaysRepository extends JpaRepository<HotelWeekDays, Long> {

    @Query("SELECT hwd FROM HotelWeekDays hwd " +
           "JOIN Hotel h ON h.weekDays.id = hwd.id " +
           "WHERE h.hotelId = :hotelId")
    Optional<HotelWeekDays> findHotelWeekDays(@Param("hotelId") Long hotelId);
}
