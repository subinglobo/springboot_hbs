package com.choosenfly.hotelbookingsystem.controller.hotel;

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

import com.choosenfly.hotelbookingsystem.dto.hotel.stopsale.StopSaleDTO;
import com.choosenfly.hotelbookingsystem.service.hotel.hotelstopsale.HotelStopSaleServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotelStopSale")
public class HotelStopSaleController {

	private final HotelStopSaleServiceInterface saleServiceInterface;
	
	@Autowired
	public HotelStopSaleController(HotelStopSaleServiceInterface saleServiceInterface) {
		this.saleServiceInterface = saleServiceInterface;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Long> saveStopSale(@Valid  @RequestBody StopSaleDTO stopSaleDTO){
		
		System.out.println("stopSaleDTO::"+stopSaleDTO);
		
		Long stopSaleId = saleServiceInterface.saveStopSale(stopSaleDTO);
		
		
		return new ResponseEntity<>(stopSaleId, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/{id}")
	private StopSaleDTO getStopSale(@PathVariable("id") Long id) {
		
		return saleServiceInterface.getStopSale(id);
	}
	
	@PutMapping("/{id}")
	private StopSaleDTO editStopSale(@PathVariable("id") Long id , @Valid @RequestBody StopSaleDTO stopSaleDTO) {
		return saleServiceInterface.editStopSale(id , stopSaleDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteStopSale(@PathVariable("id") Long id) {
		return saleServiceInterface.deleteStopSale(id);
	}
	
	@GetMapping
	public ResponseEntity<List<StopSaleDTO>> getAllStopSale(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<StopSaleDTO> Page = saleServiceInterface.getAllStopSale(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<StopSaleDTO> listStopSaleDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listStopSaleDTO, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/live-status")
	public ResponseEntity<String> updateIsLiveStatus(
	        @PathVariable("id") Long id,
	        @RequestParam("isLive") Boolean isLive) {

	    saleServiceInterface.updateIsLiveStatus(id, isLive);
	    return ResponseEntity.ok("StopSale 'isLive' status updated successfully.");
	}
}
