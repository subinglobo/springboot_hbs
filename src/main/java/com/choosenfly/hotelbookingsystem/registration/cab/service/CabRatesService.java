package com.choosenfly.hotelbookingsystem.registration.cab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabRateDTO;

import jakarta.validation.Valid;

public interface CabRatesService {

	CabRateDTO registerCabRate(@Valid CabRateDTO request);

	CabRateDTO getCabRateDetailsById(Long id);

	CabRateDTO editCabRateDetails(Long id, @Valid CabRateDTO reqDTO);

	ResponseEntity<String> deleteCabRateDetails(Long id);

	Page<CabRateDTO> getAllCabRates(Pageable pageable, String search);

}
