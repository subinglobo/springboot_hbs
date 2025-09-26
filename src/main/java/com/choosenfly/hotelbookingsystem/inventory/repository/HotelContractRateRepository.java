package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRate;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelContractRateRepository extends JpaRepository<ContractRate, Long>{

    @Query("SELECT cr FROM ContractRate cr " +
           "JOIN cr.validities v " +
           "WHERE cr.hotel.hotelId = :hotelId " +
           "AND cr.isLive = true " +
           "AND :checkInDate BETWEEN v.validityFrom AND v.validityTo " +
           "AND :checkOutDate BETWEEN v.validityFrom AND v.validityTo")
    List<ContractRate> findActiveContractRatesByHotelAndDates(
        @Param("hotelId") Long hotelId,
        @Param("checkInDate") LocalDate checkInDate,
        @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query("SELECT cr FROM ContractRate cr " +
           "WHERE cr.hotel.hotelId = :hotelId " +
           "AND cr.isLive = true")
    List<ContractRate> findActiveContractRatesByHotel(@Param("hotelId") Long hotelId);

}
