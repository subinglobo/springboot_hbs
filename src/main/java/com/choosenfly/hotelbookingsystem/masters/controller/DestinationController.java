package com.choosenfly.hotelbookingsystem.masters.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterPlaceDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.masters.service.destination.DestinationServiceInterface;

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
	public ResponseEntity<List<MasterPlaceDTO>> getAllDestination(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "80000") int limit, // Default to 20 hotels per page
			@RequestParam(required = false, name = "search") String search) {  //search -> searchTerm 

		Pageable pageable = PageRequest.of(page, limit);
		Page<MasterPlaceDTO> placePage = destinationServiceInterface.getAllDestination(pageable, search);
		List<MasterPlaceDTO> placeListDTO = Optional.ofNullable(placePage)
				.map(p -> p.getContent())
				.orElse(List.of());
		return new ResponseEntity<>(placeListDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getplaces/{stateId}")
	private List<MasterPlaceDTO> getplacesByPassingStateId(@PathVariable("stateId") Long stateId) {
		
		return destinationServiceInterface.getplacesByPassingStateId(stateId);
	}
	
	@GetMapping("/getCitiesByCountryId/{countryId}")
	private List<MasterPlaceDTO> getCitiesByPassingCountryId(@PathVariable("countryId") Long countryId) {
		
		return destinationServiceInterface.getCitiesByPassingCountryId(countryId);
	}


}
