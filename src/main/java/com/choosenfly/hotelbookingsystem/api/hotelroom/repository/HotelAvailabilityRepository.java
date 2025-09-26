package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.HotelAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelAvailabilityRepository extends JpaRepository<HotelAvailability, Long> {

    // Step 8: Fetch availability with dynamic day condition
    @Query("""
        SELECT avail.hotel.id, avail.hotelRoom.roomCategory.id, avail.hotelRoom.id, 
        avail.noOfRooms, avail.status
        FROM HotelAvailability avail
        JOIN avail.validityPeriods valid
        WHERE (:checkIn BETWEEN valid.validityFrom AND valid.validityTo)
        AND (:checkOut BETWEEN valid.validityFrom AND valid.validityTo)
        AND avail.marketType.id = :marketId
        AND avail.hotel.id = :hotelId
        ORDER BY avail.id
        """)
    List<Object[]> getSelectedAvailabilityHotels(@Param("checkIn") Date checkIn,
                                                @Param("checkOut") Date checkOut,
                                                @Param("marketId") Long marketId,
                                                @Param("hotelId") Long hotelId);

    // Step 10: Fetch stop sales
    @Query("""
        SELECT stop.hotel.id, stop.roomCategoryId.id, stop.freeSale, 
        stop.block, stop.roomAllocation
        FROM HotelStopSale stop
        JOIN stop.validityList valid
        WHERE valid.validityFrom <= :checkOut
        AND valid.validityTo >= :checkIn
        AND stop.marketTypeId.id = :marketId
        AND stop.hotel.id = :hotelId
        ORDER BY stop.hotelStopSaleId
        """)
    List<Object[]> getSelectedStopSaleHotels(@Param("checkIn") Date checkIn,
                                           @Param("checkOut") Date checkOut,
                                           @Param("marketId") Long marketId,
                                           @Param("hotelId") Long hotelId);

    // Step 12: Fetch minimum length of stay
    @Query("""
        SELECT minlos.minimumLength.hotel.id, minlos.room.roomCategory.id, minlos.room.id, 
        minlos.minimumDays
        FROM MinimumLengthStay minlos
        JOIN minlos.minimumLength.validityPeriods valid
        WHERE :checkIn BETWEEN valid.validityFrom AND valid.validityTo
        AND minlos.minimumLength.marketType.id = :marketId
        AND minlos.minimumLength.hotel.id = :hotelId
        ORDER BY minlos.id
        """)
    List<Object[]> getSelectedMinimumLengthRoomDtls(@Param("checkIn") Date checkIn,
                                                   @Param("marketId") Long marketId,
                                                   @Param("hotelId") Long hotelId);

    // Step 15: Fetch occupancy
    @Query("""
        SELECT occ.hotel.id, room.hotelRoom.roomCategory.id, room.hotelRoom.id, 
        room.totalAdult, room.totalChild, room.extraAdult, room.extraChild
        FROM HotelOccupancy occ
        JOIN occ.validityPeriods valid
        JOIN occ.roomOccupancy room
        WHERE occ.marketType.id = :marketId
        AND :checkIn BETWEEN valid.validityFrom AND valid.validityTo
        AND occ.hotel.id = :hotelId
        """)
    List<Object[]> occupancyRoomSelectedHotels(@Param("marketId") Long marketId,
                                             @Param("checkIn") Date checkIn,
                                             @Param("hotelId") Long hotelId);
}
