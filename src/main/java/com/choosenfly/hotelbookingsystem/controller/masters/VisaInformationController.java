package com.choosenfly.hotelbookingsystem.controller.masters;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterDayActivitiesDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterVisaInformationDTO;
import com.choosenfly.hotelbookingsystem.service.masters.dayActivities.DayActivitiesServiceInterface;
import com.choosenfly.hotelbookingsystem.service.masters.visaInformation.VisaInformationServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/master/visaInfo")
public class VisaInformationController {

	private final VisaInformationServiceInterface informationServiceInterface;
	
	@Autowired
	public VisaInformationController(VisaInformationServiceInterface informationServiceInterface) {
		this.informationServiceInterface = informationServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveVisaInformation(@Valid @RequestBody MasterVisaInformationDTO visaDTO){
		return informationServiceInterface.saveVisaInformation(visaDTO);
	}
	
	@GetMapping("/{id}")
	private MasterVisaInformationDTO getVisaInformationById(@PathVariable("id") Long id) {
		
		return informationServiceInterface.getVisaInformationById(id);
	}
	
	@PutMapping("/{id}")
	private MasterVisaInformationDTO editVisaInfo(@PathVariable("id") Long id , @Valid @RequestBody MasterVisaInformationDTO visaDTO) {
		return informationServiceInterface.editVisaInfo(id , visaDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteVisaInfo(@PathVariable("id") Long id) {
		return informationServiceInterface.deleteVisaInfo(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterVisaInformationDTO>> getAllVisaInfo(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterVisaInformationDTO> Page = informationServiceInterface.getAllVisaInfo(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterVisaInformationDTO> listVisaInfoDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listVisaInfoDTO, HttpStatus.OK);
	}

}
