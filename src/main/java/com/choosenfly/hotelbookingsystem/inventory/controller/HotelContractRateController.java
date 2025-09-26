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

import com.choosenfly.hotelbookingsystem.inventory.dto.contractrate.ContractRateDTO;
import com.choosenfly.hotelbookingsystem.inventory.service.HotelContractRate.HotelContractRateServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotelContractRate")
public class HotelContractRateController {

	private final HotelContractRateServiceInterface contractRateServiceInterface;
	
	@Autowired
	public HotelContractRateController(HotelContractRateServiceInterface contractRateServiceInterface) {
		this.contractRateServiceInterface = contractRateServiceInterface;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Long> saveContractRate(@Valid  @RequestBody ContractRateDTO contractRateDTO){
		
		System.out.println("ContractRateDTO::"+contractRateDTO);
		
		Long contractRateId = contractRateServiceInterface.saveContractRate(contractRateDTO);
		
		
		return new ResponseEntity<>(contractRateId, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/{id}")
	private ContractRateDTO getContractRate(@PathVariable("id") Long id) {
		
		return contractRateServiceInterface.getContractRate(id);
	}
	
	@PutMapping("/{id}")
	private ContractRateDTO editContractRate(@PathVariable("id") Long id , @Valid @RequestBody ContractRateDTO contractRateDTO) {
		return contractRateServiceInterface.editContractRate(id , contractRateDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteContractRate(@PathVariable("id") Long id) {
		return contractRateServiceInterface.deleteContractRate(id);
	}
	
	@GetMapping
	public ResponseEntity<List<ContractRateDTO>> getAllContractRate(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<ContractRateDTO> Page = contractRateServiceInterface.getAllContractRate(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<ContractRateDTO> listContractRateDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listContractRateDTO, HttpStatus.OK);
	}
	
	
}
