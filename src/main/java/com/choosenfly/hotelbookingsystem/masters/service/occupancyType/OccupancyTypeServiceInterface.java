package com.choosenfly.hotelbookingsystem.masters.service.occupancyType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterOccupancyTypeDTO;

import jakarta.validation.Valid;

public interface OccupancyTypeServiceInterface {

	Long saveOccupancyType(@Valid MasterOccupancyTypeDTO dto);

	MasterOccupancyTypeDTO getOccupancyTypeById(Long id);

	MasterOccupancyTypeDTO editOccupancyType(Long id, @Valid MasterOccupancyTypeDTO occupancyDTO);

	ResponseEntity<String> deleteOccupancyType(Long id);

	Page<MasterOccupancyTypeDTO> getAllOccupanctTypes(Pageable pageable, String search);

}
