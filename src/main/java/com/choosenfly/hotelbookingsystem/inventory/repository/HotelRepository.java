package com.choosenfly.hotelbookingsystem.inventory.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	Page<Hotel> findByHotelNameContainingIgnoreCase(String hotelName, Pageable pageable);
	
	@EntityGraph(attributePaths = {
	        "hotelAvailabilities",
	        "hotelAvailabilities.hotelRoom",
	        "hotelAvailabilities.marketType",
	        "hotelAvailabilities.hotelRoom.roomCategory",
	    })
	 @Query("SELECT h FROM Hotel h WHERE h.hotelId = :hotelId")
    Hotel findHotelWithAvailabilitiesByHotelId(@Param("hotelId") Long hotelId);
	
	
	@EntityGraph(attributePaths = {
	        "hotelBlockedDates",
	        "hotelBlockedDates.marketType"
	    })
	 @Query("SELECT h FROM Hotel h WHERE h.hotelId = :hotelId")
    Optional<Hotel> findHotelWithOccupanciesByHotelId(@Param("hotelId") Long hotelId);
	
	
	@EntityGraph(attributePaths = {
	        "hotelOccupancies",
	        "hotelOccupancies.marketType"
	    })
	 @Query("SELECT h FROM Hotel h WHERE h.hotelId = :hotelId")
    Optional<Hotel> findHotelWithBlockedDatesByHotelId(@Param("hotelId") Long hotelId);
	
}