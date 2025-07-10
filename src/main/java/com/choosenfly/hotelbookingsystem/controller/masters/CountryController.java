package com.choosenfly.hotelbookingsystem.controller.masters;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterRegionDTO;
import com.choosenfly.hotelbookingsystem.service.masters.bank.BankServiceInterface;
import com.choosenfly.hotelbookingsystem.service.masters.country.CountryServiceInterface;
import com.choosenfly.hotelbookingsystem.service.masters.region.RegionServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/country")
public class CountryController {
	
	private final CountryServiceInterface countryServiceInterface;
	
	@Autowired
	public CountryController(CountryServiceInterface countryServiceInterface) {
		this.countryServiceInterface = countryServiceInterface;
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping("/saveCountry")
	private Long saveCountry(@Valid @RequestBody MasterCountryDTO countryDTO){
		return countryServiceInterface.saveCountry(countryDTO);
	}
	
	@GetMapping("/{id}")
	private MasterCountryDTO getCountryById(@PathVariable("id") Long id) {
		
		return countryServiceInterface.getCountryById(id);
	}
	
	@PutMapping("/{id}")
	private MasterCountryDTO editCountry(@PathVariable("id") Long id , @Valid @RequestBody MasterCountryDTO countryDTO) {
		return countryServiceInterface.editCountry(id , countryDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteCountry(@PathVariable("id") Long id) {
		return countryServiceInterface.deleteCountry(id);
	}
	
	
	
	
	
	
	

}
