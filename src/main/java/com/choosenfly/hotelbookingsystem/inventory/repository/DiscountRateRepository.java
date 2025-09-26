package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRateRepository extends JpaRepository<DiscountRate, Long>{

    @Query("SELECT dr FROM DiscountRate dr " +
           "WHERE dr.hotel.hotelId = :hotelId " +
           "AND :checkInDate BETWEEN dr.validityFrom AND dr.validityTo " +
           "AND :checkOutDate BETWEEN dr.validityFrom AND dr.validityTo")
    List<DiscountRate> findActiveDiscountRatesByHotelAndDates(
        @Param("hotelId") Long hotelId,
        @Param("checkInDate") LocalDate checkInDate,
        @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query("SELECT dr FROM DiscountRate dr " +
           "WHERE dr.hotel.hotelId = :hotelId ")
    List<DiscountRate> findActiveDiscountRatesByHotel(@Param("hotelId") Long hotelId);

}
