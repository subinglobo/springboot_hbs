package com.choosenfly.hotelbookingsystem.masters.service.destination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterPlaceDTO;

public interface DestinationServiceInterface {

	Long saveDestination(MasterPlaceDTO placeDTO);

	MasterPlaceDTO getDestinationById(Long id);

	MasterPlaceDTO editDestination(Long id, MasterPlaceDTO placeDTO);

	ResponseEntity<String> deleteDestination(Long id);

	Page<MasterPlaceDTO> getAllDestination(Pageable pageable, String search);

}
