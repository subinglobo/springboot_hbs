package com.choosenfly.hotelbookingsystem.masters.service.designation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterDesignationDTO;

import jakarta.validation.Valid;

public interface DesignationServiceInterface {

	Long save(@Valid MasterDesignationDTO desigDTO);

	MasterDesignationDTO getDesigDetailsById(Long id);

	MasterDesignationDTO editDesignation(Long id, @Valid MasterDesignationDTO desigDTO);

	ResponseEntity<String> deleteDesignation(Long id);

	Page<MasterDesignationDTO> getAllDesignation(Pageable pageable, String search);

	

}
