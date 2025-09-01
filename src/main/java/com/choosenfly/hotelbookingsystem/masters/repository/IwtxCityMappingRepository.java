package com.choosenfly.hotelbookingsystem.masters.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.choosenfly.hotelbookingsystem.masters.entities.ApiCityMapping;

public interface IwtxCityMappingRepository extends JpaRepository<ApiCityMapping, Long> {

	Page<ApiCityMapping> findByApiProviderContainingIgnoreCase(String search, Pageable pageable);

	@Query(value = "SELECT hotel_code FROM iwtx_hotels " +
            "WHERE country_code = :countryCode " +
            "AND city_code = :cityCode " +
            "AND city_name = :cityName", nativeQuery = true)
	List<String> fetchIwtxHotelCodes(String countryCode, String cityCode, String cityName);

	@Query("SELECT a FROM ApiCityMapping a WHERE a.masterCountry.id = :masterCountryId AND a.masterState.id = :masterCityId AND a.apiProvider = :apiProvider")
	ApiCityMapping findByMasterCountryIdAndMasterCityIdAndApiProvider(Long masterCountryId, Long masterCityId, String apiProvider);

}
