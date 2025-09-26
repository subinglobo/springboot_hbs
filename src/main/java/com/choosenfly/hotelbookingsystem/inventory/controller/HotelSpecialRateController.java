package com.choosenfly.hotelbookingsystem.inventory.controller;

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

import com.choosenfly.hotelbookingsystem.inventory.dto.specialrate.SpecialRateDTO;
import com.choosenfly.hotelbookingsystem.inventory.service.specialrate.HotelSpecialRateServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotelSpecialRate")
public class HotelSpecialRateController {
	
	
	private final HotelSpecialRateServiceInterface hotelSpecialRateServiceInterface;
	
	@Autowired
	public HotelSpecialRateController(HotelSpecialRateServiceInterface hotelSpecialRateServiceInterface) {
		this.hotelSpecialRateServiceInterface = hotelSpecialRateServiceInterface;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Long> saveSpecialRate(@Valid  @RequestBody SpecialRateDTO specialRateDTO){
		
		System.out.println("specialRateDTO::"+specialRateDTO);
		
		Long specialRateId = hotelSpecialRateServiceInterface.saveSpecialRate(specialRateDTO);
		
		
		return new ResponseEntity<>(specialRateId, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/{id}")
	private SpecialRateDTO getspecialRate(@PathVariable("id") Long id) {
		
		return hotelSpecialRateServiceInterface.getSpecialRate(id);
	}
	
	@PutMapping("/{id}")
	private SpecialRateDTO editspecialRate(@PathVariable("id") Long id , @Valid @RequestBody SpecialRateDTO SpecialRateDTO) {
		return hotelSpecialRateServiceInterface.editSpecialRate(id , SpecialRateDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteSpecialRate(@PathVariable("id") Long id) {
		return hotelSpecialRateServiceInterface.deleteSpecialRate(id);
	}
	
	@GetMapping
	public ResponseEntity<List<SpecialRateDTO>> getAllSpecialRate(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<SpecialRateDTO> Page = hotelSpecialRateServiceInterface.getAllSpecialRate(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<SpecialRateDTO> listSpecialRateDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listSpecialRateDTO, HttpStatus.OK);
	}

}
