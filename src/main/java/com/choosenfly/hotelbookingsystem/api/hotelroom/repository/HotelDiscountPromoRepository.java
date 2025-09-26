package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelDiscountBlockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelDiscountPromoRepository extends JpaRepository<DiscountRate, Long> {

    @Query("SELECT dr FROM DiscountRate dr " +
           "JOIN dr.discountValidities v " +
           "JOIN dr.discountMarketTypes mt " +
           "WHERE dr.isLive = true " +
           "AND (:checkIn <= v.validityTo AND :checkOut >= v.validityFrom) " +
           "AND (CASE WHEN mt.marketType.marketTypeId != -1 THEN mt.marketType.marketTypeId = :marketId ELSE mt.marketType.marketTypeId != 0 END) " +
           "AND dr.hotel.hotelId = :hotelId " +
           "ORDER BY dr.discountId DESC")
    List<DiscountRate> findHotelDiscountPromoDetailsSelectedHotel(@Param("checkIn") LocalDate checkIn,
                                                                 @Param("checkOut") LocalDate checkOut,
                                                                 @Param("marketId") Long marketId, 
                                                                 @Param("hotelId") Long hotelId,
                                                                 @Param("nationality") String nationality);

    @Query(value = """
        SELECT dbb.hotel_id, dbb.hotel_room_category_id, dbb.block_date, 
        dbb.validity_from, dbb.validity_to
        FROM con_hoteldiscountblock AS dbb
        INNER JOIN con_hoteldiscountblock_validity AS vald ON dbb.hotel_discountblock_id = vald.hotel_discountblock_id
        WHERE dbb.isDeleted = 0 AND dbb.isLive = 1 
        AND (:checkIn <= DATE(vald.validityTo) AND :checkOut >= DATE(vald.validityFrom))
        AND case when (dbb.market_type_id != -1) then dbb.market_type_id = :marketId
        ELSE dbb.market_type_id != 0 END 
        AND dbb.hotel_id = :hotelId
        """, nativeQuery = true)
    List<HotelDiscountBlockDTO> findDiscountBlockDates(@Param("checkIn") LocalDate checkIn,
                                                       @Param("checkOut") LocalDate checkOut,
                                                       @Param("marketId") Long marketId, 
                                                       @Param("hotelId") Long hotelId);
}
