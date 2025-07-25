package com.choosenfly.hotelbookingsystem.service.masters.visaInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterVisaInformationDTO;

import jakarta.validation.Valid;

public interface VisaInformationServiceInterface {

	Long saveVisaInformation(@Valid MasterVisaInformationDTO visaDTO);

	MasterVisaInformationDTO getVisaInformationById(Long id);

	MasterVisaInformationDTO editVisaInfo(Long id, @Valid MasterVisaInformationDTO visaDTO);

	ResponseEntity<String> deleteVisaInfo(Long id);

	Page<MasterVisaInformationDTO> getAllVisaInfo(Pageable pageable, String search);

}
