package com.choosenfly.hotelbookingsystem.api.x3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.api.x3.entities.X3Hotels;

@Repository
public interface X3HotelsRepository extends JpaRepository<X3Hotels, Long> {

    @Query(value = "SELECT iwtx_code FROM iwtx_new_hotelslist_feed " +
            "WHERE country_code = :countryCode " +
            "AND city_code = :cityName", nativeQuery = true)
    List<String> fetchX3HotelCodes(String countryCode, String cityCode, String cityName);

    List<X3Hotels> findByCityCodeAndCountryCode(String cityCode, String countryCode);

    List<X3Hotels> findByCountryCodeAndStateNameContainingIgnoreCase(String countryCode, String cityName);
}
