package com.choosenfly.hotelbookingsystem.api.hotelroom.repository;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelMinimumLengthDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.MinimumLength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelMinimumLengthRepository extends JpaRepository<MinimumLength, Long> {

    @Query(value = """
        SELECT new com.choosenfly.hotelbookingsystem.api.hotelroom.dto.HotelMinimumLengthDTO(
            ml.hotel.hotelId, 
            mls.room.id, 
            mls.minimumDays, 
            CAST(mlv.validityFrom AS string), 
            CAST(mlv.validityTo AS string)
        )
        FROM MinimumLength ml
        JOIN ml.validityPeriods mlv
        JOIN ml.minmumLengthStay mls
        WHERE ml.isDeleted = false AND ml.status = true 
        AND :checkIn BETWEEN mlv.validityFrom AND mlv.validityTo
        AND (ml.marketType.id = :marketId OR ml.marketType.id = -1)
        AND ml.hotel.hotelId = :hotelId
        """)
    List<HotelMinimumLengthDTO> findSelectedMinimumLengthRoomDtls(@Param("checkIn") LocalDate checkIn,
                                                                 @Param("marketId") Long marketId, 
                                                                 @Param("hotelId") Long hotelId);
}
