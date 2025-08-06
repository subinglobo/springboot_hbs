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
import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.masters.service.province.ProvinceServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {
	
	private final ProvinceServiceInterface provinceServiceInterface;
	
	@Autowired
	public ProvinceController(ProvinceServiceInterface provinceServiceInterface) {
		this.provinceServiceInterface = provinceServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveProvince(@Valid @RequestBody MasterStateDTO stateDTO){
		return provinceServiceInterface.saveProvince(stateDTO);
	}
	
	@GetMapping("/{id}")
	private MasterStateDTO getProvinveById(@PathVariable("id") Long id) {
		
		return provinceServiceInterface.getProvinveById(id);
	}
	
	@PutMapping("/{id}")
	private MasterStateDTO editProvince(@PathVariable("id") Long id , @Valid @RequestBody MasterStateDTO stateDTO) {
		return provinceServiceInterface.editProvince(id , stateDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteProvince(@PathVariable("id") Long id) {
		return provinceServiceInterface.deleteProvince(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterStateDTO>> getAllProvince(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "80000") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterStateDTO> statePage = provinceServiceInterface.getAllProvince(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterStateDTO> listStateDTO = Optional.ofNullable(statePage)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of()); 

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listStateDTO, HttpStatus.OK);
	}
	
	//pass country to fetch corresponsing state/province list
	@GetMapping("/getByCountryId/{countryId}")
	private List<MasterStateDTO> getProvinveByCountryId(@PathVariable("countryId") Long countryId) {
		
		return provinceServiceInterface.getProvinveByCountryId(countryId);
	}

}
