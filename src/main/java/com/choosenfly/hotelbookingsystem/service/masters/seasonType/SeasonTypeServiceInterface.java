package com.choosenfly.hotelbookingsystem.service.masters.seasonType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterSeasonTypeDTO;

public interface SeasonTypeServiceInterface {

	Long saveSeasonType(MasterSeasonTypeDTO dto);

	MasterSeasonTypeDTO getSeasonTypeById(Long id);

	MasterSeasonTypeDTO editSeasonType(Long id, MasterSeasonTypeDTO seasonDTO);

	ResponseEntity<String> deleteSeasonType(Long id);

	Page<MasterSeasonTypeDTO> getAllSeasonTypes(Pageable pageable, String search);

}
