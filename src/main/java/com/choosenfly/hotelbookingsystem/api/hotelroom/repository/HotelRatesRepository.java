package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelRatesRepository extends JpaRepository<ContractRate, Long> {

    // Step 16: Fetch compulsory events
    @Query(value = """
        SELECT event.hotel_id, event.hotel_room_category_id, event.hotel_roomtype_id, 
        event.event_name, event.event_rate, event.event_date, event.is_compulsory
        FROM con_hotelevents AS event
        INNER JOIN con_hotelevents_validity AS valid ON event.hotel_events_id = valid.hotel_events_id
        WHERE event.event_date BETWEEN :checkIn AND :checkOut
        AND event.market_type_id = :marketId
        AND event.hotel_id = :hotelId
        AND event.isDeleted = 0 AND event.isLive = 1
        ORDER BY event.event_date
        """, nativeQuery = true)
    List<Object[]> getEventSelectedHotel(@Param("checkIn") Date checkIn,
                                        @Param("checkOut") Date checkOut,
                                        @Param("marketId") Long marketId,
                                        @Param("hotelId") Long hotelId);

    // Step 17: Fetch contract rates
    @Query(value = """
        SELECT contract.hotel_id, contract.hotel_room_category_id, contract.hotel_roomtype_id, 
        contract.rate_date, contract.single_rate, contract.double_rate, contract.triple_rate, 
        contract.quad_rate, contract.extra_adult_rate, contract.extra_child_rate
        FROM con_hotelcontractrate AS contract
        INNER JOIN con_hotelcontractrate_validity AS valid ON contract.hotel_contractrate_id = valid.hotel_contractrate_id
        WHERE contract.rate_date BETWEEN :checkIn AND :checkOut
        AND contract.market_type_id = :marketId
        AND contract.hotel_id = :hotelId
        AND contract.isDeleted = 0 AND contract.isLive = 1
        ORDER BY contract.rate_date
        """, nativeQuery = true)
    List<Object[]> getContractRateSelectedHotel(@Param("checkIn") Date checkIn,
                                               @Param("checkOut") Date checkOut,
                                               @Param("marketId") Long marketId,
                                               @Param("hotelId") Long hotelId);

    // Step 18: Fetch special rates
    @Query(value = """
        SELECT special.hotel_id, special.hotel_room_category_id, special.hotel_roomtype_id, 
        special.rate_date, special.single_rate, special.double_rate, special.triple_rate, 
        special.quad_rate, special.extra_adult_rate, special.extra_child_rate, 
        special.exclude_country, special.priority
        FROM con_hotelspecialrate AS special
        INNER JOIN con_hotelspecialrate_validity AS valid ON special.hotel_specialrate_id = valid.hotel_specialrate_id
        WHERE special.rate_date BETWEEN :checkIn AND :checkOut
        AND special.market_type_id = :marketId
        AND special.hotel_id = :hotelId
        AND (special.exclude_country IS NULL OR special.exclude_country != :nationality)
        AND special.isDeleted = 0 AND special.isLive = 1
        ORDER BY special.rate_date, special.priority
        """, nativeQuery = true)
    List<Object[]> getSpecialRateSelectedHotel(@Param("checkIn") Date checkIn,
                                              @Param("checkOut") Date checkOut,
                                              @Param("marketId") Long marketId,
                                              @Param("hotelId") Long hotelId,
                                              @Param("nationality") String nationality);

    // Step 19: Fetch special rate blocked dates
    @Query(value = """
        SELECT block.hotel_id, block.hotel_room_category_id, block.hotel_roomtype_id, 
        block.blocked_date, block.is_blocked
        FROM con_hotelspecialrate_blockdates AS block
        WHERE block.blocked_date BETWEEN :checkIn AND :checkOut
        AND block.hotel_id = :hotelId
        AND block.isDeleted = 0
        """, nativeQuery = true)
    List<Object[]> checkBlockDatesSelectedHotel(@Param("checkIn") Date checkIn,
                                               @Param("checkOut") Date checkOut,
                                               @Param("hotelId") Long hotelId);

    // Step 20: Fetch special rate combined promotions
    @Query(value = """
        SELECT promo.hotel_id, promo.hotel_room_category_id, promo.hotel_roomtype_id, 
        promo.promotion_name, promo.discount_percentage, promo.discount_amount, 
        promo.minimum_nights, promo.free_nights
        FROM con_hotelspecialrate_promotions AS promo
        INNER JOIN con_hotelspecialrate_promotions_validity AS valid ON promo.hotel_specialrate_promotion_id = valid.hotel_specialrate_promotion_id
        WHERE :checkIn BETWEEN DATE(valid.validityFrom) AND DATE(valid.validityTo)
        AND promo.market_type_id = :marketId
        AND promo.hotel_id = :hotelId
        AND promo.isDeleted = 0 AND promo.isLive = 1
        """, nativeQuery = true)
    List<Object[]> getSpecialRatePromoSelectedHotel(@Param("checkIn") Date checkIn,
                                                   @Param("marketId") Long marketId,
                                                   @Param("hotelId") Long hotelId);
}
