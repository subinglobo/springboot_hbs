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

import com.choosenfly.hotelbookingsystem.dto.HotelContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.dto.HotelDTO;
import com.choosenfly.hotelbookingsystem.dto.HotelMailCentreDTO;
import com.choosenfly.hotelbookingsystem.service.hotel.HotelMailtypeServiceInterface;
import com.choosenfly.hotelbookingsystem.service.hotel.HotelRegistrationService;
import com.choosenfly.hotelbookingsystem.service.hotel.HotelRegistrationServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	private final HotelRegistrationServiceInterface hotelService;

	private final HotelMailtypeServiceInterface hotelMailService;

	@Autowired
	public HotelController(HotelRegistrationService hotelService, HotelMailtypeServiceInterface hotelMailService) {
		this.hotelService = hotelService;
		this.hotelMailService = hotelMailService;
	}

	@PostMapping
	public ResponseEntity<HotelDTO> saveHotel(@Valid @RequestBody HotelDTO hotelDTO) {

		HotelDTO savedHotel = hotelService.saveHotel(hotelDTO);
		return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HotelDTO> getHotelById(@PathVariable("id") Long id) {

		HotelDTO hotel = hotelService.getHotelById(id);

		return new ResponseEntity<>(hotel, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<HotelDTO>> getHotels(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// Calls the service method to fetch a page of HotelDTOs based on pagination and
		// search criteria
		Page<HotelDTO> hotelsPage = hotelService.getHotels(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<HotelDTO> listHotelDTO = Optional.ofNullable(hotelsPage)

				// Extracts the List<HotelDTO> from the Page object if hotelsPage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if hotelsPage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listHotelDTO, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<HotelDTO> updateHotel(@PathVariable("id") Long id, @Valid @RequestBody HotelDTO hotelDTO) {
		hotelDTO.setId(id);

		HotelDTO hotel = hotelService.updateHotel(hotelDTO, id);

		return new ResponseEntity<>(hotel, HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id) {

		ResponseEntity<String> deleteHotel = hotelService.deleteHotel(id);

		return deleteHotel;

	}

	@PostMapping("/addMailCentre/{id}")
	public ResponseEntity<String> updateMailCentre(@PathVariable("id") Long id,
			@RequestBody HotelMailCentreDTO mailCentreDTO) {
		
		String addMailCentre = hotelMailService.addMailCentre(id, mailCentreDTO);

		return new ResponseEntity<>(addMailCentre, HttpStatus.OK);
	}

	@GetMapping("/getMailCentre/{id}")
	public List<HotelContactDetailsDTO> getMailCentre(@PathVariable("id") Long id) {
		List<HotelContactDetailsDTO> mailCentre = hotelMailService.getMailCentre(id);

		return mailCentre;

	}

}
