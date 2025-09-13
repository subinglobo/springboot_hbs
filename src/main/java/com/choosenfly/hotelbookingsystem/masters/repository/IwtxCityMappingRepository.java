package com.choosenfly.hotelbookingsystem.masters.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.choosenfly.hotelbookingsystem.masters.entities.ApiCityMapping;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;

public interface IwtxCityMappingRepository extends JpaRepository<ApiCityMapping, Long> {

	Page<ApiCityMapping> findByApiProviderContainingIgnoreCase(String search, Pageable pageable);

	@Query(value = "SELECT hotel_code FROM iwtx_hotels " +
            "WHERE country_code = :countryCode " +
            "AND city_code = :cityCode " +
            "AND city_name = :cityName", nativeQuery = true)
	List<String> fetchIwtxHotelCodes(String countryCode, String cityCode, String cityName);

	@Query("SELECT a FROM ApiCityMapping a WHERE a.masterCountry.id = :masterCountryId AND a.masterState.id = :masterCityId AND a.apiProvider = :apiProvider")
	ApiCityMapping findByMasterCountryIdAndMasterCityIdAndApiProvider(Long masterCountryId, Long masterCityId, String apiProvider);

	Optional<ApiCityMapping> findByApiProviderAndMasterCountryAndMasterStateAndApiCountryIdAndApiCityId(
			String apiProvider, MasterCountry countryEntity, MasterState stateEntity, String string, String string2);

}
