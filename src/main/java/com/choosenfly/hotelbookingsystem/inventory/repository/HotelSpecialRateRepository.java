package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelSpecialRateBlockDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelSpecialRatePromoDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRate;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelSpecialRateRepository extends JpaRepository<SpecialRate, Long>{

    @Query("SELECT sr FROM SpecialRate sr " +
           "JOIN sr.specialRateValidities v " +
           "WHERE sr.hotel.hotelId = :hotelId " +
           "AND sr.isLive = true " +
           "AND :checkInDate BETWEEN v.validityFrom AND v.validityTo " +
           "AND :checkOutDate BETWEEN v.validityFrom AND v.validityTo")
    List<SpecialRate> findActiveSpecialRatesByHotelAndDates(
        @Param("hotelId") Long hotelId,
        @Param("checkInDate") LocalDate checkInDate,
        @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query("SELECT sr FROM SpecialRate sr " +
           "WHERE sr.hotel.hotelId = :hotelId " +
           "AND sr.isLive = true")
    List<SpecialRate> findActiveSpecialRatesByHotel(@Param("hotelId") Long hotelId);
    
    @Query("SELECT sr FROM SpecialRate sr " +
            "JOIN sr.specialRateValidities v " +
            "JOIN sr.specialRateMarketTypes mt " +
            "WHERE sr.isLive = true " +
            "AND (:checkIn <= v.validityTo AND :checkOut >= v.validityFrom) " +
            "AND (CASE WHEN mt.marketType.marketTypeId != -1 THEN mt.marketType.marketTypeId = :marketId ELSE mt.marketType.marketTypeId != 0 END) " +
            "AND sr.hotel.hotelId = :hotelId " +
            "AND (sr.excludeCountry IS NULL OR sr.excludeCountry != :nationality)")
     List<SpecialRate> findSpecialRateSelectedHotel(@Param("checkIn") LocalDate checkIn,
                                                    @Param("checkOut") LocalDate checkOut,
                                                    @Param("marketId") Long marketId, 
                                                    @Param("hotelId") Long hotelId,
                                                    @Param("nationality") String nationality);

     @Query(value = """
         SELECT srb.hotel_id, srb.hotel_room_category_id, srb.block_date, 
         srb.validity_from, srb.validity_to
         FROM con_hotelspecialrateblock AS srb
         INNER JOIN con_hotelspecialrateblock_validity AS vald ON srb.hotel_specialrateblock_id = vald.hotel_specialrateblock_id
         WHERE srb.isDeleted = 0 AND srb.isLive = 1 
         AND (:checkIn <= DATE(vald.validityTo) AND :checkOut >= DATE(vald.validityFrom))
         AND case when (srb.market_type_id != -1) then srb.market_type_id = :marketId
         ELSE srb.market_type_id != 0 END 
         AND srb.hotel_id = :hotelId
         """, nativeQuery = true)
     List<HotelSpecialRateBlockDTO> findBlockDatesSelectedHotel(@Param("checkIn") LocalDate checkIn,
                                                               @Param("checkOut") LocalDate checkOut,
                                                               @Param("marketId") Long marketId, 
                                                               @Param("hotelId") Long hotelId);

     @Query(value = """
         SELECT srp.hotel_id, srp.hotel_room_category_id, srp.promo_name, 
         srp.promo_type, srp.promo_value, srp.validity_from, srp.validity_to
         FROM con_hotelspecialratepromo AS srp
         INNER JOIN con_hotelspecialratepromo_validity AS vald ON srp.hotel_specialratepromo_id = vald.hotel_specialratepromo_id
         WHERE srp.isDeleted = 0 AND srp.isLive = 1 
         AND (:checkIn <= DATE(vald.validityTo) AND :checkOut >= DATE(vald.validityFrom))
         AND case when (srp.market_type_id != -1) then srp.market_type_id = :marketId
         ELSE srp.market_type_id != 0 END 
         AND srp.hotel_id = :hotelId
         """, nativeQuery = true)
     List<HotelSpecialRatePromoDTO> findSpecialRatePromoSelectedHotel(@Param("checkIn") LocalDate checkIn,
                                                                     @Param("checkOut") LocalDate checkOut,
                                                                     @Param("marketId") Long marketId, 
                                                                     @Param("hotelId") Long hotelId);

}
