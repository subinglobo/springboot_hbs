package com.choosenfly.hotelbookingsystem.registration.cab.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabProviderDTO;
import com.choosenfly.hotelbookingsystem.registration.cab.service.CabProviderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cabProvider")
public class CabProviderController {

	@Autowired
	private CabProviderService cabProviderService;
	

    @PostMapping("/register")
    public ResponseEntity<Long> registerCabProvider(@Valid @RequestBody CabProviderDTO request) {
    	
    	System.out.println("JSON request:"+request);
        
    	CabProviderDTO cabProvider = cabProviderService.registerCabProvider(request);
    	
        return new ResponseEntity<>(cabProvider.getCabprovider(), HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
	private CabProviderDTO getCabProviderRegistrationDetailsById(@PathVariable("id") Long id) {
		
		return cabProviderService.getCabProviderRegistrationDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private CabProviderDTO editCabProviderRegistrationDetails(@PathVariable("id") Long id , @Valid @RequestBody CabProviderDTO reqDTO) {
		return cabProviderService.editCabProviderRegistrationDetails(id , reqDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteCabProviderRegistrationDetails(@PathVariable("id") Long id) {
		return cabProviderService.deleteCabProviderRegistrationDetails(id);
	}
	
	@GetMapping
	public ResponseEntity<List<CabProviderDTO>> getAllCabProviders(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<CabProviderDTO> Page = cabProviderService.getAllCabProviders(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<CabProviderDTO> CabProvidersList = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(CabProvidersList, HttpStatus.OK);
	}
    
}
