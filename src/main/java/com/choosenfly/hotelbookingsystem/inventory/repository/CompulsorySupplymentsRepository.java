package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.compulsoryevents.CompulsorySupplyments;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompulsorySupplymentsRepository extends JpaRepository<CompulsorySupplyments, Long>{

    @Query("SELECT cs FROM CompulsorySupplyments cs " +
           "WHERE cs.hotel.hotelId = :hotelId " +
           "AND :checkInDate BETWEEN cs.validityFrom AND cs.validityTo " +
           "AND :checkOutDate BETWEEN cs.validityFrom AND cs.validityTo")
    List<CompulsorySupplyments> findActiveCompulsorySupplymentsByHotelAndDates(
        @Param("hotelId") Long hotelId,
        @Param("checkInDate") LocalDate checkInDate,
        @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query("SELECT cs FROM CompulsorySupplyments cs " +
           "WHERE cs.hotel.hotelId = :hotelId ")
    List<CompulsorySupplyments> findActiveCompulsorySupplymentsByHotel(@Param("hotelId") Long hotelId);

}
