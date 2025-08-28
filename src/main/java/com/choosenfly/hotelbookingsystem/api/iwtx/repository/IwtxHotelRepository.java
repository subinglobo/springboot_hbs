package com.choosenfly.hotelbookingsystem.api.iwtx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.api.iwtx.dto.HotelInfoIwtx;
import com.choosenfly.hotelbookingsystem.api.iwtx.entities.IwtxHotel;

@Repository
public interface IwtxHotelRepository extends JpaRepository<IwtxHotel, Integer> {

    /**
     * Find all hotels by cityId and countryId.
     *
     * @param cityId    city ID
     * @param countryId country ID
     * @return List of hotels matching the criteria
     */
    List<IwtxHotel> findByCityIdAndCountryId(Integer cityId, Integer countryId);
    
    @Query("""
    	    SELECT new com.choosenfly.hotelbookingsystem.api.iwtx.dto.HotelInfoIwtx(
    	        h.hotelCode,
    	        h.hotelName,
    	        h.imagesUrl,
    	        CAST(h.starRating AS integer),
    	        h.hotelAddress
    	    )
    	    FROM IwtxHotel h
    	    WHERE h.cityId = :cityId AND h.countryId = :countryId
    	""")
    	List<HotelInfoIwtx> findHotelsByCityAndCountry(
    	        @Param("cityId") Integer cityId,
    	        @Param("countryId") Integer countryId);

    /**
     * If you want case-insensitive search by city name and country ID.
     */
    List<IwtxHotel> findByCityNameIgnoreCaseAndCountryId(String cityName, Integer countryId);

    /**
     * Find hotel by hotel code
     * 
     * @param hotelCode hotel code
     * @return IwtxHotel entity
     */
    IwtxHotel findByHotelCode(String hotelCode);
}
