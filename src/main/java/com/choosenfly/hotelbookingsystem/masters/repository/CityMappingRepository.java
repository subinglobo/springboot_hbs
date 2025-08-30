package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.ApiCityMapping;

@Repository
public interface CityMappingRepository  extends JpaRepository<ApiCityMapping, Long> {

	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END " +
            "FROM public.api_city_mapping " +
            "WHERE api_provider = :apiProvider " +
            "AND api_city_id = :apiCityId " +
            "AND api_country_id = :apiCountryId",
    nativeQuery = true)
boolean checkDataExistinDb(String apiProvider, String apiCountryId, String apiCityId);


	

}
