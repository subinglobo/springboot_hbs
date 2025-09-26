package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplyments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRoomCompulsoryEventRepository extends JpaRepository<CompulsorySupplyments, Long> {

    @Query("SELECT cs FROM CompulsorySupplyments cs " +
           "JOIN cs.compulsorySupplyValidities v " +
           "JOIN cs.compulsorySupplyMarketTypes mt " +
           "WHERE cs.isLive = true " +
           "AND (:checkIn <= v.validityTo AND :checkOut >= v.validityFrom) " +
           "AND (CASE WHEN mt.marketType.marketTypeId != -1 THEN mt.marketType.marketTypeId = :marketId ELSE mt.marketType.marketTypeId != 0 END) " +
           "AND cs.hotel.hotelId = :hotelId")
    List<CompulsorySupplyments> findEventSelectedHotel(@Param("checkIn") LocalDate checkIn,
                                                      @Param("checkOut") LocalDate checkOut,
                                                      @Param("marketId") Long marketId, 
                                                      @Param("hotelId") Long hotelId);
}
