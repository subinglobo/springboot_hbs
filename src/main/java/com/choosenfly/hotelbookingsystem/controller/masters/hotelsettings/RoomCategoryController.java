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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterRoomCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterSeasonTypeDTO;
import com.choosenfly.hotelbookingsystem.service.masters.roomCategory.RoomCategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roomCategory")
public class RoomCategoryController {
	
	private final RoomCategoryServiceImpl  roomCategoryServiceImpl;
	
	@Autowired
	public RoomCategoryController(RoomCategoryServiceImpl  roomCategoryServiceImpl) {
		this.roomCategoryServiceImpl = roomCategoryServiceImpl;
	}
	
	@PostMapping("/save")
	private Long saveRoomCategory(@Valid @RequestBody MasterRoomCategoryDTO dto){
		return roomCategoryServiceImpl.saveRoomCategory(dto);
	}
	
	@GetMapping("/{id}")
	private MasterRoomCategoryDTO getRoomcategoryById(@PathVariable("id") Long id) {
		
		return roomCategoryServiceImpl.getRoomCategoryById(id);
	}
	
	@PutMapping("/{id}")
	private MasterRoomCategoryDTO editRoomcategory(@PathVariable("id") Long id , @Valid @RequestBody MasterRoomCategoryDTO roomDTO) {
		return roomCategoryServiceImpl.editRoomCategory(id , roomDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteRoomcategory(@PathVariable("id") Long id) {
		return roomCategoryServiceImpl.deleteRoomCategory(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterRoomCategoryDTO>> getAllRoomCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterRoomCategoryDTO> roomcategoryPage = roomCategoryServiceImpl.getAllRoomCategories(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
    	 List<MasterRoomCategoryDTO> roomCategoryList = Optional.ofNullable(roomcategoryPage).map(p -> p.getContent()).orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(roomCategoryList, HttpStatus.OK);
	}

}

