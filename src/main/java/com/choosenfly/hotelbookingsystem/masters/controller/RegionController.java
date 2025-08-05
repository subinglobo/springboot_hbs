package com.choosenfly.hotelbookingsystem.masters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
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
	
	
	
	
	
	
	

}
