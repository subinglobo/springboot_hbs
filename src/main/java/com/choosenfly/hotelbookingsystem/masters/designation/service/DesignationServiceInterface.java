package com.choosenfly.hotelbookingsystem.masters.designation.service;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterDesignationDTO;

import jakarta.validation.Valid;

public interface DesignationServiceInterface {

	Long save(@Valid MasterDesignationDTO desigDTO);

	MasterDesignationDTO getDesigDetailsById(Long id);

	MasterDesignationDTO editDesignation(Long id, @Valid MasterDesignationDTO desigDTO);

	ResponseEntity<String> deleteDesignation(Long id);

	

}
