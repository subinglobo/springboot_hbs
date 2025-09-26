package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StayPay;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StayPayRepository extends JpaRepository<StayPay, Long>{

    @Query("SELECT sp FROM StayPay sp " +
           "WHERE sp.hotel.hotelId = :hotelId " +
           "AND :checkInDate BETWEEN sp.validityFrom AND sp.validityTo " +
           "AND :checkOutDate BETWEEN sp.validityFrom AND sp.validityTo " +
           "AND :nights >= sp.minimumNights")
    List<StayPay> findActiveStayPayPromotionsByHotelAndDates(
        @Param("hotelId") Long hotelId,
        @Param("checkInDate") LocalDate checkInDate,
        @Param("checkOutDate") LocalDate checkOutDate,
        @Param("nights") Long nights
    );

    @Query("SELECT sp FROM StayPay sp " +
           "WHERE sp.hotel.hotelId = :hotelId ")
    List<StayPay> findActiveStayPayPromotionsByHotel(@Param("hotelId") Long hotelId);

}
