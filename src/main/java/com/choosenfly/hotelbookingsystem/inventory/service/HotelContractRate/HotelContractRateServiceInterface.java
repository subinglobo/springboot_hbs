package com.choosenfly.hotelbookingsystem.inventory.service.HotelContractRate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.inventory.dto.contractrate.ContractRateDTO;

import jakarta.validation.Valid;

public interface HotelContractRateServiceInterface {

	Long saveContractRate(@Valid ContractRateDTO contractRateDTO);

	ContractRateDTO getContractRate(Long id);

	ContractRateDTO editContractRate(Long id, @Valid ContractRateDTO contractRateDTO);

	ResponseEntity<String> deleteContractRate(Long id);

	Page<ContractRateDTO> getAllContractRate(Pageable pageable, String search);

}
