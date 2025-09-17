package com.choosenfly.hotelbookingsystem.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents.CompulsorySupplymentsDTO;
import com.choosenfly.hotelbookingsystem.inventory.service.compulsoryevents.HotelCompulsoryEventServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/compulsoryEvent")
public class HotelCompulsoryEventController {

	
private final HotelCompulsoryEventServiceInterface eventServiceInterface;
	
	@Autowired
	public HotelCompulsoryEventController(HotelCompulsoryEventServiceInterface eventServiceInterface) {
		this.eventServiceInterface = eventServiceInterface;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Long> saveCompulsorySupplyment(@Valid  @RequestBody CompulsorySupplymentsDTO compulsorySupplymentsDTO){
		
		System.out.println("CompulsorySupplymentsDTO::"+compulsorySupplymentsDTO);
		
		Long compulsorySupplymentId = eventServiceInterface.saveCompulsorySupplyment(compulsorySupplymentsDTO);
		
		
		return new ResponseEntity<>(compulsorySupplymentId, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/{id}")
	private CompulsorySupplymentsDTO getCompulsorySupplyment(@PathVariable("id") Long id) {
		
		return eventServiceInterface.getCompulsorySupplyment(id);
	}
	
	@PutMapping("/{id}")
	private CompulsorySupplymentsDTO editCompulsorySupplyment(@PathVariable("id") Long id , @Valid @RequestBody CompulsorySupplymentsDTO compulsorySupplymentsDTO) {
		
		System.out.println("entered:::");
		return eventServiceInterface.editcompulsorySupplyment(id , compulsorySupplymentsDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteCompulsorySupplyment(@PathVariable("id") Long id) {
		return eventServiceInterface.deleteCompulsorySupplyment(id);
	}
	
	@GetMapping
	public ResponseEntity<List<CompulsorySupplymentsDTO>> getAllcompulsorySupplyment(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<CompulsorySupplymentsDTO> Page = eventServiceInterface.getAllCompulsorySupplyment(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<CompulsorySupplymentsDTO> listCompulsorySupplymentsDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listCompulsorySupplymentsDTO, HttpStatus.OK);
	}
}
