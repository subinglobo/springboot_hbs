package com.choosenfly.hotelbookingsystem.registration.cab.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabListDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabProviderDTO;

import jakarta.validation.Valid;

public interface CabProviderService {

	CabProviderDTO registerCabProvider(@Valid CabProviderDTO request);

	CabProviderDTO getCabProviderRegistrationDetailsById(Long id);

	CabProviderDTO editCabProviderRegistrationDetails(Long id, @Valid CabProviderDTO reqDTO);

	ResponseEntity<String> deleteCabProviderRegistrationDetails(Long id);

	Page<CabProviderDTO> getAllCabProviders(Pageable pageable, String search);

	List<CabListDTO> getCabList(Long id);

}
