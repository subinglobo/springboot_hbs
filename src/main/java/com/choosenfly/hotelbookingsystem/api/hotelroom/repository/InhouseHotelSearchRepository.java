package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelWeekDays;

@Repository
public interface InhouseHotelSearchRepository extends JpaRepository<Hotel, Long> {

    // Step 3: Fetch hotel details using entity relationships
    @Query("SELECT h FROM Hotel h " +
           "LEFT JOIN FETCH h.hotelCurrency " +
           "LEFT JOIN FETCH h.country " +
           "LEFT JOIN FETCH h.state " +
           "LEFT JOIN FETCH h.hotelCategory " +
           "LEFT JOIN FETCH h.hotelType " +
           "LEFT JOIN FETCH h.markupType " +
           "WHERE h.isDeleted = false AND h.hotelId = :hotelId")
    Optional<Hotel> fetchHotelDetails(@Param("hotelId") Long hotelId);

    // Step 5: Fetch rooms for the hotel using entity relationships
    @Query("SELECT hrc FROM HotelRoomCategory hrc " +
           "LEFT JOIN FETCH hrc.roomCategory " +
           "LEFT JOIN FETCH hrc.hotelRoomTypes hrt " +
           "WHERE hrc.hotel.hotelId = :hotelId")
    List<HotelRoomCategory> fetchHotelRooms(@Param("hotelId") Long hotelId);

    // Step 6: Fetch weekdays for the hotel using entity relationships
    @Query("SELECT hwd FROM HotelWeekDays hwd " +
           "WHERE hwd.id IN " +
           "(SELECT h.weekDays.id FROM Hotel h WHERE h.hotelId = :hotelId)")
    Optional<HotelWeekDays> fetchHotelWeekdays(@Param("hotelId") Long hotelId);

    // Step 7: Fetch blocked check-in/check-out dates using entity relationships
    @Query("SELECT DISTINCT b.hotel.hotelId "+ 
    	       "FROM BlockCheckInAndCheckOut b "+
    	      " JOIN b.validityList v "+
    	         "AND b.hotel.hotelId = :hotelId "+
    	       "  AND ((:checkIn BETWEEN v.validityFrom AND v.validityTo AND b.isCheckin = true)"+ 
    	          " OR (:checkOut BETWEEN v.validityFrom AND v.validityTo AND b.isCheckOut = true))")
    	List<Long> fetchBlockedCheckInCheckOutHotels(@Param("checkIn") LocalDate checkIn,
    	                                             @Param("hotelId") Long hotelId,
    	                                             @Param("checkOut") LocalDate checkOut);
}
