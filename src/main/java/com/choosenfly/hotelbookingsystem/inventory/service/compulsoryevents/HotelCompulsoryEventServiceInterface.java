package com.choosenfly.hotelbookingsystem.inventory.service.compulsoryevents;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents.CompulsorySupplymentsDTO;

import jakarta.validation.Valid;

public interface HotelCompulsoryEventServiceInterface {

	Long saveCompulsorySupplyment(@Valid CompulsorySupplymentsDTO compulsorySupplymentsDTO);

	CompulsorySupplymentsDTO getCompulsorySupplyment(Long id);

	CompulsorySupplymentsDTO editcompulsorySupplyment(Long id,
			@Valid CompulsorySupplymentsDTO compulsorySupplymentsDTO);

	ResponseEntity<String> deleteCompulsorySupplyment(Long id);

	Page<CompulsorySupplymentsDTO> getAllCompulsorySupplyment(Pageable pageable, String search);

}
