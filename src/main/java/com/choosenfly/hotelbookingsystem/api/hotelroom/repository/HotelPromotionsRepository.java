package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.discount.DiscountRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HotelPromotionsRepository extends JpaRepository<DiscountRate, Long> {

    // Step 21: Fetch discount promotions
    @Query(value = """
        SELECT discount.hotel_id, discount.hotel_room_category_id, discount.hotel_roomtype_id, 
        discount.promotion_name, discount.discount_percentage, discount.discount_amount, 
        discount.minimum_nights, discount.exclude_country, discount.priority, discount.days_advance
        FROM con_hoteldiscountpromo AS discount
        INNER JOIN con_hoteldiscountpromo_validity AS valid ON discount.hotel_discountpromo_id = valid.hotel_discountpromo_id
        WHERE :checkIn BETWEEN DATE(valid.validityFrom) AND DATE(valid.validityTo)
        AND discount.market_type_id = :marketId
        AND discount.hotel_id = :hotelId
        AND (discount.exclude_country IS NULL OR discount.exclude_country != :nationality)
        AND discount.isDeleted = 0 AND discount.isLive = 1
        ORDER BY discount.days_advance, discount.priority
        """, nativeQuery = true)
    List<Object[]> getHotelDiscountPromoDetailsSelectedHotel(@Param("checkIn") Date checkIn,
                                                            @Param("marketId") Long marketId,
                                                            @Param("hotelId") Long hotelId,
                                                            @Param("nationality") String nationality);

    // Step 22: Fetch stay-pay promotions
    @Query(value = """
        SELECT staypay.hotel_id, staypay.hotel_room_category_id, staypay.hotel_roomtype_id, 
        staypay.promotion_name, staypay.stay_nights, staypay.pay_nights, staypay.free_nights,
        staypay.exclude_country, staypay.priority, staypay.days_advance
        FROM con_hotelstaypaypromo AS staypay
        INNER JOIN con_hotelstaypaypromo_validity AS valid ON staypay.hotel_staypaypromo_id = valid.hotel_staypaypromo_id
        WHERE :checkIn BETWEEN DATE(valid.validityFrom) AND DATE(valid.validityTo)
        AND staypay.market_type_id = :marketId
        AND staypay.hotel_id = :hotelId
        AND (staypay.exclude_country IS NULL OR staypay.exclude_country != :nationality)
        AND staypay.isDeleted = 0 AND staypay.isLive = 1
        ORDER BY staypay.days_advance, staypay.priority
        """, nativeQuery = true)
    List<Object[]> getHotelStayPayPromoDetailsSelectedHotel(@Param("checkIn") Date checkIn,
                                                           @Param("marketId") Long marketId,
                                                           @Param("hotelId") Long hotelId,
                                                           @Param("nationality") String nationality);

    // Step 29: Fetch blocked discount dates
    @Query(value = """
        SELECT block.hotel_id, block.hotel_room_category_id, block.hotel_roomtype_id, 
        block.blocked_date, block.promotion_id
        FROM con_hoteldiscountpromo_blockdates AS block
        WHERE block.blocked_date BETWEEN :checkIn AND :checkOut
        AND block.hotel_id = :hotelId
        AND block.isDeleted = 0
        """, nativeQuery = true)
    List<Object[]> checkDiscountBlockDates(@Param("checkIn") Date checkIn,
                                          @Param("checkOut") Date checkOut,
                                          @Param("hotelId") Long hotelId);

    // Step 30: Fetch blocked stay-pay dates
    @Query(value = """
        SELECT block.hotel_id, block.hotel_room_category_id, block.hotel_roomtype_id, 
        block.blocked_date, block.promotion_id
        FROM con_hotelstaypaypromo_blockdates AS block
        WHERE block.blocked_date BETWEEN :checkIn AND :checkOut
        AND block.hotel_id = :hotelId
        AND block.isDeleted = 0
        """, nativeQuery = true)
    List<Object[]> checkStayPayBlockDates(@Param("checkIn") Date checkIn,
                                         @Param("checkOut") Date checkOut,
                                         @Param("hotelId") Long hotelId);
}
