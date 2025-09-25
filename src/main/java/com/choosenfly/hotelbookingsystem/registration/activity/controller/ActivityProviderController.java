package com.choosenfly.hotelbookingsystem.registration.activity.controller;

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

import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityProviderDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.service.ActivityProviderService;
import com.choosenfly.hotelbookingsystem.registration.cab.dtos.CabRateDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/activityProvider")
public class ActivityProviderController {
	
		@Autowired
		private  ActivityProviderService activityProviderService; 

	   @PostMapping("/register")
	    public ResponseEntity<Long> registerActivityProvider(@Valid @RequestBody ActivityProviderDTO requestDTO) {
	        
		   ActivityProviderDTO returnDTO = activityProviderService.registerActivityProvider(requestDTO);

		   return null;
//	       return new ResponseEntity<>(cabRate.getCabratesId(), HttpStatus.CREATED);
	    }
	    
	    
	    @GetMapping("/{id}")
		private ActivityProviderDTO getActivityProviderById(@PathVariable("id") Long id) {
			
			return activityProviderService.getActivityProviderById(id);
		}
		
		@PutMapping("/{id}")
		private ActivityProviderDTO editActivityProvider(@PathVariable("id") Long id , @Valid @RequestBody ActivityProviderDTO reqDTO) {
			return activityProviderService.editActivityProvider(id , reqDTO);
		}
		
		
		@DeleteMapping("/{id}")
		private ResponseEntity<String>  deleteActivityProvider(@PathVariable("id") Long id) {
			return activityProviderService.deleteActivityProvider(id);
		}
		
		@GetMapping
		public ResponseEntity<List<ActivityProviderDTO>> getActivityProviders(@RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
				@RequestParam(required = false) String search) {
			
			System.out.println("entering");

			// Creates a Pageable object specifying the page number and size (limit) for
			// pagination
			Pageable pageable = PageRequest.of(page, limit);

			// search criteria
			Page<ActivityProviderDTO> Page = activityProviderService.getActivityProviders(pageable, search);

			// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
			// a repository)
			List<ActivityProviderDTO> CabRatesList = Optional.ofNullable(Page)

					// Extracts the List<HotelDTO> from the Page object if statePage is not null
					// (gets the content of the current page)
					.map(p -> p.getContent())

					// Returns an empty list if statePage is null or getContent() returns null
					// (fallback for edge cases)
					.orElse(List.of());

			// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
			// 200 (OK)
			return new ResponseEntity<>(CabRatesList, HttpStatus.OK);
		}
}
