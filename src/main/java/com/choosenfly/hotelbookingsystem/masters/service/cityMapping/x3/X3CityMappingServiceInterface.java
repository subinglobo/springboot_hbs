package com.choosenfly.hotelbookingsystem.masters.service.cityMapping.x3;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;

import jakarta.validation.Valid;

public interface X3CityMappingServiceInterface {

	Long saveX3CityMapping(ApiCityMappingDTO dto);

	ApiCityMappingDTO getX3CityMappingById(Long id);

	ApiCityMappingDTO editX3CityMapping(Long id, @Valid ApiCityMappingDTO placeDTO);

	ResponseEntity<String> deleteX3CityMapping(Long id);

	Page<ApiCityMappingDTO> getAllX3CityMappingList(Pageable pageable, String search);
}
