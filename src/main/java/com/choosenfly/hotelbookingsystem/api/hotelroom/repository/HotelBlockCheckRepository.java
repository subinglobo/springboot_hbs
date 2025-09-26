package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.BlockCheckInAndCheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelBlockCheckRepository extends JpaRepository<BlockCheckInAndCheckOut, Long> {

    @Query("SELECT DISTINCT b.hotel.hotelId FROM BlockCheckInAndCheckOut b " +
           "JOIN b.validityList v " +
           "WHERE ((b.isCheckin = true AND :checkIn BETWEEN v.validityFrom AND v.validityTo) " +
           "OR (b.isCheckOut = true AND :checkOut BETWEEN v.validityFrom AND v.validityTo)) " +
           "AND b.marketType.marketTypeId = :marketId " +
           "AND b.hotel.hotelId = :hotelId")
    List<Long> findBlockedHotels(@Param("checkIn") LocalDate checkIn, @Param("marketId") Long marketId, 
                                @Param("hotelId") Long hotelId, @Param("checkOut") LocalDate checkOut);
}
