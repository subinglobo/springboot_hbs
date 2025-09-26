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

import com.choosenfly.hotelbookingsystem.inventory.dto.staypay.StayPayPromotionDTO;
import com.choosenfly.hotelbookingsystem.inventory.service.staypay.HotelStaypayService;
import com.choosenfly.hotelbookingsystem.inventory.service.staypay.HotelStaypayServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotelStaypay")
public class HotelStaypayController {

	private final HotelStaypayServiceInterface staypayServiceInterface;
	
	@Autowired
	public HotelStaypayController(HotelStaypayServiceInterface staypayServiceInterface) {
		this.staypayServiceInterface = staypayServiceInterface;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Long> savestayPay(@Valid  @RequestBody StayPayPromotionDTO stayPayDTO){
		
		System.out.println("stayPayDTO::"+stayPayDTO);
		
		Long stayPayId = staypayServiceInterface.saveStayPay(stayPayDTO);
		
		
		return new ResponseEntity<>(stayPayId, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/{id}")
	private StayPayPromotionDTO getstayPay(@PathVariable("id") Long id) {
		
		return staypayServiceInterface.getstayPay(id);
	}
	
	@PutMapping("/{id}")
	private StayPayPromotionDTO editstayPay(@PathVariable("id") Long id , @Valid @RequestBody StayPayPromotionDTO stayPayDTO) {
		return staypayServiceInterface.editstayPay(id , stayPayDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deletestayPay(@PathVariable("id") Long id) {
		return staypayServiceInterface.deletestayPay(id);
	}
	
	@GetMapping
	public ResponseEntity<List<StayPayPromotionDTO>> getAllstayPay(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<StayPayPromotionDTO> Page = staypayServiceInterface.getAllstayPay(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<StayPayPromotionDTO> liststayPayDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(liststayPayDTO, HttpStatus.OK);
	}

}
