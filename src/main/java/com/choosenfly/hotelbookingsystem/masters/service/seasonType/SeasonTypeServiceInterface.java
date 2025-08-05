package com.choosenfly.hotelbookingsystem.masters.service.seasonType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterSeasonTypeDTO;

public interface SeasonTypeServiceInterface {

	Long saveSeasonType(MasterSeasonTypeDTO dto);

	MasterSeasonTypeDTO getSeasonTypeById(Long id);

	MasterSeasonTypeDTO editSeasonType(Long id, MasterSeasonTypeDTO seasonDTO);

	ResponseEntity<String> deleteSeasonType(Long id);

	Page<MasterSeasonTypeDTO> getAllSeasonTypes(Pageable pageable, String search);

}
