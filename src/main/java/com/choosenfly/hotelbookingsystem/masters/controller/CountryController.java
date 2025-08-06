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
import com.choosenfly.hotelbookingsystem.masters.dto.MasterRegionDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.masters.service.bank.BankServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.country.CountryServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.region.RegionServiceInterface;

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
	
	@GetMapping
	public ResponseEntity<List<MasterCountryDTO>> getAllCountry(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "80000") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterCountryDTO> countryPage  = countryServiceInterface.getAllCountries(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterCountryDTO> listCountryDTO = Optional.ofNullable(countryPage)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listCountryDTO, HttpStatus.OK);
	}
	
	
	
	
	

}
