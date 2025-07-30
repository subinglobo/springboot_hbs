package com.choosenfly.hotelbookingsystem.service.masters.itenaryDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterItenaryDetailsDTO;

import jakarta.validation.Valid;

public interface ItenaryDetailsServiceInterface {

	Long saveItenaryDetails(@Valid MasterItenaryDetailsDTO itenaryDTO);

	MasterItenaryDetailsDTO getItenaryDetailsById(Long id);

	MasterItenaryDetailsDTO editItenaryDetails(Long id, @Valid MasterItenaryDetailsDTO itenaryDTO);

	ResponseEntity<String> deleteItenaryDetails(Long id);

	Page<MasterItenaryDetailsDTO> getAllItenaryDetails(Pageable pageable, String search);

}
