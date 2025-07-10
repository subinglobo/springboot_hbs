package com.choosenfly.hotelbookingsystem.service.masters.region;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterRegionDTO;

import jakarta.validation.Valid;

public interface RegionServiceInterface {

	Long saveRegion(@Valid MasterRegionDTO regionDTO);

	MasterRegionDTO getRegionById(Long id);

	MasterRegionDTO editRegion(Long id, @Valid MasterRegionDTO regionDTO);

	ResponseEntity<String> deleteRegion(Long id);

}
