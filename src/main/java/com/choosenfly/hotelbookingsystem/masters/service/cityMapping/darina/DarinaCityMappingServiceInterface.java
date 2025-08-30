package com.choosenfly.hotelbookingsystem.masters.service.cityMapping.darina;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.choosenfly.hotelbookingsystem.masters.controller.MasterCountryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;

public interface DarinaCityMappingServiceInterface {

	Boolean saveDarinaCityMapping(ApiCityMappingDTO dto);

	Page<MasterCountryDTO> getAllDarinaCountry(Pageable pageable, String search);

}
