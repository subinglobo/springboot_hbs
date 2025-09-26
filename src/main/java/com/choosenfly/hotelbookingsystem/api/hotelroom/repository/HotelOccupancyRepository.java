package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelOccupancyRepository extends JpaRepository<HotelOccupancy, Long> {

    @Query("SELECT ho FROM HotelOccupancy ho " +
           "JOIN ho.validityPeriods vp " +
           "WHERE ho.deleted = false AND ho.live = true " +
           "AND (CASE WHEN ho.marketType.marketTypeId != -1 THEN ho.marketType.marketTypeId = :marketId ELSE ho.marketType.marketTypeId != 0 END) " +
           "AND :checkIn BETWEEN vp.validityFrom AND vp.validityTo " +
           "AND ho.hotel.hotelId = :hotelId")
    List<HotelOccupancy> findOccupancyRoomSelectedHotels(@Param("marketId") Long marketId,
                                                        @Param("checkIn") LocalDate checkIn, 
                                                        @Param("hotelId") Long hotelId);
}
