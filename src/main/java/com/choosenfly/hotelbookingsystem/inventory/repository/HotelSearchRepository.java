package com.choosenfly.hotelbookingsystem.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;

@Repository
public interface HotelSearchRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h " +
           "WHERE h.country.id = :countryId " +
           "AND h.place.id = :cityId " +
           "AND h.isDeleted = false " +
           "ORDER BY h.hotelName ASC")
    List<Hotel> findHotelsByCountryAndCity(@Param("countryId") Long countryId, 
                                          @Param("cityId") Long cityId);

    @Query("SELECT h FROM Hotel h " +
           "WHERE h.country.id = :countryId " +
           "AND h.place.id = :cityId " +
           "AND h.hotelCategory.hotelCategoryId = :categoryId " +
           "AND h.isDeleted = false " +
           "ORDER BY h.hotelName ASC")
    List<Hotel> findHotelsByCountryAndCityAndCategory(@Param("countryId") Long countryId, 
                                                     @Param("cityId") Long cityId,
                                                     @Param("categoryId") Long categoryId);

    // Simple test query to debug
    @Query("SELECT h FROM Hotel h WHERE h.isDeleted = false")
    List<Hotel> findAllActiveHotels();
}
