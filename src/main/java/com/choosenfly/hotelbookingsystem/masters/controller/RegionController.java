package com.choosenfly.hotelbookingsystem.masters.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterCurrencyDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterRegionDTO;
import com.choosenfly.hotelbookingsystem.masters.service.bank.BankServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.region.RegionServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/region")
public class RegionController {
	
	private final RegionServiceInterface regionServiceInterface;
	
	@Autowired
	public RegionController(RegionServiceInterface regionServiceInterface) {
		this.regionServiceInterface = regionServiceInterface;
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping("/saveRegion")
	private Long saveRegion(@Valid @RequestBody MasterRegionDTO regionDTO){
		return regionServiceInterface.saveRegion(regionDTO);
	}
	
	@GetMapping("/{id}")
	private MasterRegionDTO getRegionById(@PathVariable("id") Long id) {
		
		return regionServiceInterface.getRegionById(id);
	}
	
	@PutMapping("/{id}")
	private MasterRegionDTO editRegion(@PathVariable("id") Long id , @Valid @RequestBody MasterRegionDTO regionDTO) {
		return regionServiceInterface.editRegion(id , regionDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteRegion(@PathVariable("id") Long id) {
		return regionServiceInterface.deleteRegion(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterRegionDTO>> getAllRegionsList(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int limit,
			@RequestParam(required = false, name = "search") String searchTerm) {   

		Pageable pageable = PageRequest.of(page, limit);
		Page<MasterRegionDTO> regionPage = regionServiceInterface.getAllRegionsList(pageable, searchTerm);
		List<MasterRegionDTO> regionListDTO = Optional.ofNullable(regionPage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(regionListDTO, HttpStatus.OK);
	}
	
	
	
	
	

}
