package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelStayPayPromoDTO;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelStayPayBlockDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.staypay.StayPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelStayPayPromoRepository extends JpaRepository<StayPay, Long> {

    @Query(value = """
        SELECT new com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelStayPayPromoDTO(
            sp.hotel.hotelId, 
            sr.roomCategory.id, 
            sp.rateCode, 
            3, 
            2, 
            CAST(sv.validityFrom AS string), 
            CAST(sv.validityTo AS string), 
            '', 
            1
        )
        FROM StayPay sp
        JOIN sp.validities sv
        JOIN sp.staypayRooms sr
        JOIN sp.marketTypes smt
        WHERE sp.isLive = true 
        AND :checkIn <= sv.validityTo AND :checkOut >= sv.validityFrom
        AND (smt.marketType.marketTypeId = :marketId OR smt.marketType.marketTypeId = -1)
        AND sp.hotel.hotelId = :hotelId
        ORDER BY sp.staypayId DESC
        """)
    List<HotelStayPayPromoDTO> findHotelStayPayPromoDetailsSelectedHotel(@Param("checkIn") LocalDate checkIn,
                                                                         @Param("checkOut") LocalDate checkOut,
                                                                         @Param("marketId") Long marketId, 
                                                                         @Param("hotelId") Long hotelId,
                                                                         @Param("nationality") String nationality);

    @Query(value = """
        SELECT spb.hotel_id, spb.hotel_room_category_id, spb.block_date, 
        spb.validity_from, spb.validity_to
        FROM con_hotelstaypayblock AS spb
        INNER JOIN con_hotelstaypayblock_validity AS vald ON spb.hotel_staypayblock_id = vald.hotel_staypayblock_id
        WHERE spb.isDeleted = 0 AND spb.isLive = 1 
        AND (:checkIn <= DATE(vald.validityTo) AND :checkOut >= DATE(vald.validityFrom))
        AND case when (spb.market_type_id != -1) then spb.market_type_id = :marketId
        ELSE spb.market_type_id != 0 END 
        AND spb.hotel_id = :hotelId
        """, nativeQuery = true)
    List<HotelStayPayBlockDTO> findStayPayBlockDates(@Param("checkIn") LocalDate checkIn,
                                                     @Param("checkOut") LocalDate checkOut,
                                                     @Param("marketId") Long marketId, 
                                                     @Param("hotelId") Long hotelId);
}
