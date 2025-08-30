package com.choosenfly.hotelbookingsystem.masters.controller.cityMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.controller.MasterCountryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.service.cityMapping.darina.DarinaCityMappingServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/darinaCityMapping")
public class DarinaCityMappingController {
	
	private final DarinaCityMappingServiceInterface darinaCityMappingServiceInterface;
	
	@Autowired
	public DarinaCityMappingController(DarinaCityMappingServiceInterface darinaCityMappingServiceInterface) {
		this.darinaCityMappingServiceInterface =  darinaCityMappingServiceInterface;
		
	}
	
	@PostMapping("/save")
	private Boolean saveDarinaCityMapping(@Valid @RequestBody ApiCityMappingDTO dto){
		return darinaCityMappingServiceInterface.saveDarinaCityMapping(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterCountryDTO>> getAllDarinaCountry(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "80000") int limit,
			@RequestParam(required = false) String search) {

		Pageable pageable = PageRequest.of(page, limit);

		Page<MasterCountryDTO> countryPage  = darinaCityMappingServiceInterface.getAllDarinaCountry(pageable, search);
		List<MasterCountryDTO> listCountryDTO = Optional.ofNullable(countryPage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(listCountryDTO, HttpStatus.OK);
	}
	
	
}
