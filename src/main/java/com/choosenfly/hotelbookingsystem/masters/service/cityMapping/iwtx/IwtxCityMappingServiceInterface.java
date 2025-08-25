package com.choosenfly.hotelbookingsystem.masters.service.cityMapping.iwtx;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;

import jakarta.validation.Valid;

public interface IwtxCityMappingServiceInterface {

	Long saveIwtxCityMapping(ApiCityMappingDTO dto);

	ApiCityMappingDTO getIwtxCityMappingById(Long id);

	ApiCityMappingDTO editIwtxCityMapping(Long id, @Valid ApiCityMappingDTO placeDTO);

	ResponseEntity<String> deleteIwtxCityMapping(Long id);

	Page<ApiCityMappingDTO> getAllIwtxCityMappingList(Pageable pageable, String search);
	
	

}
