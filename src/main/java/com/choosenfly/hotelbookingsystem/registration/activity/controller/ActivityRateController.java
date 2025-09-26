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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityInclusionAndTermsDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.dtos.ActivityRateDTO;
import com.choosenfly.hotelbookingsystem.registration.activity.service.ActivityRateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/activityRate")
public class ActivityRateController {

	@Autowired
	private  ActivityRateService activityRateService;

   @PostMapping("/save")
    public ResponseEntity<Long> saveActivityRate(@ModelAttribute @Valid ActivityRateDTO requestDTO) {
	   
	   System.out.println("requestDTO:::"+requestDTO);
        
	   ActivityRateDTO returnDTO = activityRateService.saveActivityRate(requestDTO);

       return new ResponseEntity<>(returnDTO.getActivityRateId(), HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
	private ActivityRateDTO getActivityRateById(@PathVariable("id") Long id) {
		
		return activityRateService.getActivityRateById(id);
	}
	
	@PutMapping("/{id}")
	private ActivityRateDTO editActivityRate(@PathVariable("id") Long id ,@ModelAttribute @Valid ActivityRateDTO reqDTO) {
		return activityRateService.editActivityRate(id , reqDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteActivityRate(@PathVariable("id") Long id) {
		return activityRateService.deleteActivityRate(id);
	}
	
	@GetMapping
	public ResponseEntity<List<ActivityRateDTO>> getActivityRates(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<ActivityRateDTO> Page = activityRateService.getActivityRates(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<ActivityRateDTO> CabRatesList = Optional.ofNullable(Page)

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
	
	
	   @PostMapping("/inclutionAndTerms/save")
	    public ResponseEntity<String> saveInclutionAndTerms(@RequestBody @Valid List<ActivityInclusionAndTermsDTO> requestDTO) {
	        
		   String response = activityRateService.saveInclutionAndTerms(requestDTO);

	       return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	
}
