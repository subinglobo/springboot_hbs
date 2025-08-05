package com.choosenfly.hotelbookingsystem.masters.service.itenaryDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterItenaryDetailsDTO;

import jakarta.validation.Valid;

public interface ItenaryDetailsServiceInterface {

	Long saveItenaryDetails(@Valid MasterItenaryDetailsDTO itenaryDTO);

	MasterItenaryDetailsDTO getItenaryDetailsById(Long id);

	MasterItenaryDetailsDTO editItenaryDetails(Long id, @Valid MasterItenaryDetailsDTO itenaryDTO);

	ResponseEntity<String> deleteItenaryDetails(Long id);

	Page<MasterItenaryDetailsDTO> getAllItenaryDetails(Pageable pageable, String search);

}
