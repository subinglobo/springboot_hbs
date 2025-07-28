package com.choosenfly.hotelbookingsystem.controller.masters.hotelsettings;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelTypeDTO;
import com.choosenfly.hotelbookingsystem.service.masters.hoteltype.HotelTypeServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotelType")
public class HotelTypeController {
	
	private final HotelTypeServiceInterface hotelTypeServiceInterface;
	
	@Autowired
	public HotelTypeController(HotelTypeServiceInterface hotelTypeServiceInterface) {
		this.hotelTypeServiceInterface = hotelTypeServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveHotelType(@Valid @RequestBody MasterHotelTypeDTO dto){
		return hotelTypeServiceInterface.saveHotelType(dto);
	}
	
	@GetMapping("/{id}")
	private MasterHotelTypeDTO getHotelTypeById(@PathVariable("id") Long id) {
		
		return hotelTypeServiceInterface.getHotelTypeById(id);
	}
	
	@PutMapping("/{id}")
	private MasterHotelTypeDTO editHotelType(@PathVariable("id") Long id , @Valid @RequestBody MasterHotelTypeDTO hotelTypeDTO) {
		return hotelTypeServiceInterface.editHotelType(id , hotelTypeDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteHotelType(@PathVariable("id") Long id) {
		return hotelTypeServiceInterface.deleteHotelType(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterHotelTypeDTO>> getAllHotelType(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterHotelTypeDTO> hoteltypePage = hotelTypeServiceInterface.getAllHotelType(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterHotelTypeDTO> listHotelTypeDTO = Optional.ofNullable(hoteltypePage)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listHotelTypeDTO, HttpStatus.OK);
	}

}

