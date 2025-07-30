package com.choosenfly.hotelbookingsystem.controller.masters;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterItenaryDetailsDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterVisaInformationDTO;
import com.choosenfly.hotelbookingsystem.service.masters.agentCategory.AgentCategoryServiceInterface;
import com.choosenfly.hotelbookingsystem.service.masters.itenaryDetails.ItenaryDetailsServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/master/itenaryDetails")
public class ItenaryDetailsController {
	
	private final ItenaryDetailsServiceInterface detailsServiceInterface;
	
	@Autowired
	public ItenaryDetailsController(ItenaryDetailsServiceInterface detailsServiceInterface) {
		this.detailsServiceInterface = detailsServiceInterface;
		
	}
	
	@PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Long saveItenaryDetails(@ModelAttribute @Valid MasterItenaryDetailsDTO itenaryDTO) {
		System.out.println("itenaryDTO:::"+itenaryDTO);
	    return detailsServiceInterface.saveItenaryDetails(itenaryDTO);
	}
	
	@GetMapping("/{id}")
	private MasterItenaryDetailsDTO getItenaryDetailsById(@PathVariable("id") Long id) {
		
		return detailsServiceInterface.getItenaryDetailsById(id);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MasterItenaryDetailsDTO> updateItinerary(
	        @PathVariable Long id,
	        @ModelAttribute MasterItenaryDetailsDTO dto) {
	    MasterItenaryDetailsDTO updated = detailsServiceInterface.editItenaryDetails(id, dto);
	    return ResponseEntity.ok(updated);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteItenaryDetails(@PathVariable("id") Long id) {
		return detailsServiceInterface.deleteItenaryDetails(id);
	}

	@GetMapping
	public ResponseEntity<List<MasterItenaryDetailsDTO>> getAllItenaryDetails(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterItenaryDetailsDTO> Page = detailsServiceInterface.getAllItenaryDetails(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterItenaryDetailsDTO> itenaryDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(itenaryDTO, HttpStatus.OK);
	}

}
