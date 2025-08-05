package com.choosenfly.hotelbookingsystem.masters.controller.hotelsettings;

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

import com.choosenfly.hotelbookingsystem.masters.dto.MasterHotelAmenityDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterRoomCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.service.hotelAmenity.HotelAmenityServiceImpl;
import com.choosenfly.hotelbookingsystem.masters.service.roomCategory.RoomCategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotelAmenity")
public class HotelAmenityController {
	
	private final HotelAmenityServiceImpl  hotelAmenityServiceImpl;
	
	@Autowired
	public HotelAmenityController(HotelAmenityServiceImpl  hotelAmenityServiceImpl) {
		this.hotelAmenityServiceImpl = hotelAmenityServiceImpl;
	}
	
	@PostMapping("/save")
	private Long saveHotelAmenity(@Valid @RequestBody MasterHotelAmenityDTO dto){
		return hotelAmenityServiceImpl.saveHotelAmenity(dto);
	}
	
	@GetMapping("/{id}")
	private MasterHotelAmenityDTO getHotelAmenityById(@PathVariable("id") Long id) {
		
		return hotelAmenityServiceImpl.getHotelAmenityById(id);
	}
	
	@PutMapping("/{id}")
	private MasterHotelAmenityDTO editHotelAmenity(@PathVariable("id") Long id , @Valid @RequestBody MasterHotelAmenityDTO amenityDTO) {
		return hotelAmenityServiceImpl.editHotelAmenity(id , amenityDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteHotelAmenity(@PathVariable("id") Long id) {
		return hotelAmenityServiceImpl.deleteHotelAmenity(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterHotelAmenityDTO>> getAllHotelAmenities(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterHotelAmenityDTO> hotelAmenityPage = hotelAmenityServiceImpl.getAllHotelAmenities(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterHotelAmenityDTO> hotelAmenityList = Optional.ofNullable(hotelAmenityPage).map(p -> p.getContent()).orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(hotelAmenityList, HttpStatus.OK);
	}

}

