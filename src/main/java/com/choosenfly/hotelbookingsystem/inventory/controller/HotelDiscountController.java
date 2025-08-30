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

import com.choosenfly.hotelbookingsystem.inventory.service.discount.HotelDiscountServiceInterface;

import com.choosenfly.hotelbookingsystem.inventory.dto.discount.DiscountDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/discount")
public class HotelDiscountController {
	
private final HotelDiscountServiceInterface hotelDiscountServiceInterface;
	
	@Autowired
	public HotelDiscountController(HotelDiscountServiceInterface hotelDiscountServiceInterface) {
		this.hotelDiscountServiceInterface = hotelDiscountServiceInterface;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Long> saveDiscount(@Valid @RequestBody DiscountDTO discountDTO){
		
		System.out.println("DiscountDTO::"+discountDTO);
		
		Long DiscountId = hotelDiscountServiceInterface.saveDiscount(discountDTO);
		
		
		return new ResponseEntity<>(DiscountId, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/{id}")
	private DiscountDTO getDiscount(@PathVariable("id") Long id) {
		
		return hotelDiscountServiceInterface.getDiscount(id);
	}
	
	@PutMapping("/{id}")
	private DiscountDTO editDiscount(@PathVariable("id") Long id , @Valid @RequestBody DiscountDTO DiscountDTO) {
		return hotelDiscountServiceInterface.editDiscount(id , DiscountDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteDiscount(@PathVariable("id") Long id) {
		return hotelDiscountServiceInterface.deleteDiscount(id);
	}
	
	@GetMapping
	public ResponseEntity<List<DiscountDTO>> getAllDiscount(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<DiscountDTO> Page = hotelDiscountServiceInterface.getAllDiscount(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<DiscountDTO> listDiscountDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listDiscountDTO, HttpStatus.OK);
	}


}
