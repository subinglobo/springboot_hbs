package com.choosenfly.hotelbookingsystem.service.masters.itenaryDetails;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterItenaryDetailsDTO;

import jakarta.validation.Valid;

public interface ItenaryDetailsServiceInterface {

	Long saveItenaryDetails(@Valid MasterItenaryDetailsDTO itenaryDTO);

	MasterAgentCategoryDTO getItenaryDetailsById(Long id);

	MasterAgentCategoryDTO editItenaryDetails(Long id, @Valid MasterItenaryDetailsDTO itenaryDTO);

	ResponseEntity<String> deleteItenaryDetails(Long id);

}
