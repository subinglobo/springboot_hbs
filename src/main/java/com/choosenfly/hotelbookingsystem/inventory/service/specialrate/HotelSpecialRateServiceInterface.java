package com.choosenfly.hotelbookingsystem.inventory.service.specialrate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.inventory.dto.specialrate.SpecialRateDTO;

import jakarta.validation.Valid;

public interface HotelSpecialRateServiceInterface {

	Long saveSpecialRate(@Valid SpecialRateDTO specialRateDTO);

	SpecialRateDTO getSpecialRate(Long id);

	SpecialRateDTO editSpecialRate(Long id, @Valid SpecialRateDTO specialRateDTO);

	ResponseEntity<String> deleteSpecialRate(Long id);

	Page<SpecialRateDTO> getAllSpecialRate(Pageable pageable, String search);

}
