package com.choosenfly.hotelbookingsystem.api.x3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.api.x3.dto.HotelInfoX3;
import com.choosenfly.hotelbookingsystem.api.x3.entities.X3Hotel;

@Repository
public interface X3HotelRepository extends JpaRepository<X3Hotel, Integer> {

    /**
     * Find all hotels by cityId and countryId.
     *
     * @param cityId    city ID
     * @param countryId country ID
     * @return List of hotels matching the criteria
     */
    List<X3Hotel> findByCityIdAndCountryId(Integer cityId, Integer countryId);
    
    @Query("""
    	    SELECT new com.choosenfly.hotelbookingsystem.api.x3.dto.HotelInfoX3(
    	        h.hotelCode,
    	        h.hotelName,
    	        h.imagesUrl,
    	        CAST(h.starRating AS integer),
    	        h.hotelAddress
    	    )
    	    FROM X3Hotel h
    	    WHERE h.cityId = :cityId AND h.countryId = :countryId
    	""")
    	List<HotelInfoX3> findHotelsByCityAndCountry(
    	        @Param("cityId") Integer cityId,
    	        @Param("countryId") Integer countryId);

    /**
     * If you want case-insensitive search by city name and country ID.
     */
    List<X3Hotel> findByCityNameIgnoreCaseAndCountryId(String cityName, Integer countryId);
}
