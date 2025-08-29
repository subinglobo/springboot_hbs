package com.choosenfly.hotelbookingsystem.api.x3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.api.x3.dto.HotelInfoX3;
import com.choosenfly.hotelbookingsystem.api.x3.entities.X3Hotel;

@Repository
public interface X3HotelRepository extends JpaRepository<X3Hotel, Long> {

    /**
     * Find all hotels by cityCode and countryCode.
     *
     * @param cityCode    city code
     * @param countryCode country code
     * @return List of hotels matching the criteria
     */
    List<X3Hotel> findByCityCodeAndCountryCode(String cityCode, String countryCode);
    
    /**
     * Find hotels by city code and country code, excluding deleted records.
     *
     * @param cityCode    city code
     * @param countryCode country code
     * @return List of active hotels matching the criteria
     */
    List<X3Hotel> findByCityCodeAndCountryCodeAndIsDeletedFalse(String cityCode, String countryCode);
    
    @Query("""
    	    SELECT new com.choosenfly.hotelbookingsystem.api.x3.dto.HotelInfoX3(
    	        h.id,
    	        h.hotelName,
    	        h.hotelImage,
    	        CAST(h.starCategory AS integer),
    	        h.address
    	    )
    	    FROM X3Hotel h
    	    WHERE h.cityCode = :cityCode AND h.countryCode = :countryCode
    	    AND (h.isDeleted IS NULL OR h.isDeleted = false)
    	""")
    	List<HotelInfoX3> findHotelsByCityAndCountry(
    	        @Param("cityCode") String cityCode,
    	        @Param("countryCode") String countryCode);

    /**
     * Find hotels by supplier code.
     *
     * @param supplierCode supplier code
     * @return List of hotels from the specified supplier
     */
    List<X3Hotel> findBySupplierCode(String supplierCode);

    /**
     * Find hotels by zone.
     *
     * @param zone zone
     * @return List of hotels in the specified zone
     */
    List<X3Hotel> findByZone(String zone);

    /**
     * Find hotels by property type.
     *
     * @param propertyType property type
     * @return List of hotels of the specified property type
     */
    List<X3Hotel> findByPropertyType(String propertyType);

    /**
     * Find hotels by state code and country code.
     *
     * @param stateCode   state code
     * @param countryCode country code
     * @return List of hotels matching the criteria
     */
    List<X3Hotel> findByStateCodeAndCountryCode(String stateCode, String countryCode);
}
