package com.choosenfly.hotelbookingsystem.controller.masters;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterPlaceDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.service.masters.destination.DestinationServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {
	
	private final DestinationServiceInterface destinationServiceInterface;
	
	@Autowired
	public DestinationController(DestinationServiceInterface destinationServiceInterface) {
		this.destinationServiceInterface = destinationServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveDestination(@Valid @RequestBody MasterPlaceDTO placeDTO){
		return destinationServiceInterface.saveDestination(placeDTO);
	}
	
	@GetMapping("/{id}")
	private MasterPlaceDTO getDestinationById(@PathVariable("id") Long id) {
		
		return destinationServiceInterface.getDestinationById(id);
	}
	
	@PutMapping("/{id}")
	private MasterPlaceDTO editDestination(@PathVariable("id") Long id , @Valid @RequestBody MasterPlaceDTO placeDTO) {
		return destinationServiceInterface.editDestination(id , placeDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteDestination(@PathVariable("id") Long id) {
		return destinationServiceInterface.deleteDestination(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterPlaceDTO>> getAllDestination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterPlaceDTO> placePage = destinationServiceInterface.getAllDestination(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterPlaceDTO> placeListDTO = Optional.ofNullable(placePage)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(placeListDTO, HttpStatus.OK);
	}

}
