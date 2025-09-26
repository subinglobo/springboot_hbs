package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;

@Repository
public interface HotelDetailsRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h " +
           "LEFT JOIN FETCH h.hotelCurrency " +
           "LEFT JOIN FETCH h.country " +
           "LEFT JOIN FETCH h.state " +
           "LEFT JOIN FETCH h.hotelCategory " +
           "LEFT JOIN FETCH h.hotelType " +
           "LEFT JOIN FETCH h.markupType " +
           "WHERE h.isDeleted = false AND h.hotelId = :hotelId")
    Optional<Hotel> findHotelDetails(@Param("hotelId") Long hotelId);
}
