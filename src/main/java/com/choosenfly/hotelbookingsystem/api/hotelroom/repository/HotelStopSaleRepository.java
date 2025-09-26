package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.hotelstopsale.HotelStopSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelStopSaleRepository extends JpaRepository<HotelStopSale, Long> {

    @Query("""
        SELECT ss.hotel.id, ss.roomCategoryId.id, ss.freeSale, ss.block, ss.roomAllocation
        FROM HotelStopSale ss
        JOIN ss.validityList valid
        WHERE valid.validityFrom <= :checkOut
        AND valid.validityTo >= :checkIn
        AND (ss.marketTypeId.id = :marketId OR ss.marketTypeId.id IS NULL)
        AND ss.hotel.id = :hotelId
        """)
    List<Object[]> findSelectedStopSaleHotels(@Param("checkIn") LocalDate checkIn, 
                                             @Param("checkOut") LocalDate checkOut,
                                             @Param("marketId") Long marketId, 
                                             @Param("hotelId") Long hotelId);
}
