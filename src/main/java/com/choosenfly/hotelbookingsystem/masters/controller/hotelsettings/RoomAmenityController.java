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
import com.choosenfly.hotelbookingsystem.masters.dto.MasterRoomAmenityDTO;
import com.choosenfly.hotelbookingsystem.masters.service.roomAmenity.RoomAmenityServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roomAmenity")
public class RoomAmenityController {
	
	private final RoomAmenityServiceImpl  roomAmenityServiceImpl;
	
	@Autowired
	public RoomAmenityController(RoomAmenityServiceImpl  roomAmenityServiceImpl) {
		this.roomAmenityServiceImpl = roomAmenityServiceImpl;
	}
	
	@PostMapping("/save")
	private Long saveRoomAmenity(@Valid @RequestBody MasterRoomAmenityDTO dto){
		return roomAmenityServiceImpl.saveRoomAmenity(dto);
	}
	
	@GetMapping("/{id}")
	private MasterRoomAmenityDTO getRoomAmenityById(@PathVariable("id") Long id) {
		
		return roomAmenityServiceImpl.getRoomAmenityById(id);
	}
	
	@PutMapping("/{id}")
	private MasterRoomAmenityDTO editRoomAmenity(@PathVariable("id") Long id , @Valid @RequestBody MasterRoomAmenityDTO roomAmenityDTO) {
		return roomAmenityServiceImpl.editRoomAmenity(id , roomAmenityDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteRoomAmenity(@PathVariable("id") Long id) {
		return roomAmenityServiceImpl.deleteRoomAmenity(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterRoomAmenityDTO>> getAllRoomAmenities(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterRoomAmenityDTO> roomAmenityPage = roomAmenityServiceImpl.getAllRoomAmenities(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterRoomAmenityDTO> roomAmenityList = Optional.ofNullable(roomAmenityPage).map(p -> p.getContent()).orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(roomAmenityList, HttpStatus.OK);
	}

}

