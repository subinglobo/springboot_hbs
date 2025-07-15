package com.choosenfly.hotelbookingsystem.service.masters.province;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterStateDTO;

import jakarta.validation.Valid;

public interface ProvinceServiceInterface {

	Long saveProvince(@Valid MasterStateDTO stateDTO);

	MasterStateDTO getProvinveById(Long id);

	MasterStateDTO editProvince(Long id, @Valid MasterStateDTO stateDTO);

	ResponseEntity<String> deleteProvince(Long id);

}
