package com.choosenfly.hotelbookingsystem.masters.service.country;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.controller.MasterCountryDTO;

import jakarta.validation.Valid;

public interface CountryServiceInterface {

	Long saveCountry(@Valid MasterCountryDTO countryDTO);

	MasterCountryDTO getCountryById(Long id);

	MasterCountryDTO editCountry(Long id, @Valid MasterCountryDTO countryDTO);

	ResponseEntity<String> deleteCountry(Long id);

	Page<MasterCountryDTO> getAllCountries(Pageable pageable, String search);



}
