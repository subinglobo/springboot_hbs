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

import com.choosenfly.hotelbookingsystem.masters.dto.MasterOccupancyTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterSeasonTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.service.seasonType.SeasonTypeServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/seasonType")
public class SeasonTypeController {
	
	private final SeasonTypeServiceInterface seasonTypeServiceInterface;
	
	@Autowired
	public SeasonTypeController(SeasonTypeServiceInterface seasonTypeServiceInterface) {
		this.seasonTypeServiceInterface = seasonTypeServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveSeasonType(@Valid @RequestBody MasterSeasonTypeDTO dto){
		return seasonTypeServiceInterface.saveSeasonType(dto);
	}
	
	@GetMapping("/{id}")
	private MasterSeasonTypeDTO getSeasonTypeById(@PathVariable("id") Long id) {
		
		return seasonTypeServiceInterface.getSeasonTypeById(id);
	}
	
	@PutMapping("/{id}")
	private MasterSeasonTypeDTO editSeasonType(@PathVariable("id") Long id , @Valid @RequestBody MasterSeasonTypeDTO seasonDTO) {
		return seasonTypeServiceInterface.editSeasonType(id , seasonDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteSeasonType(@PathVariable("id") Long id) {
		return seasonTypeServiceInterface.deleteSeasonType(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterSeasonTypeDTO>> getAllSeasonTypes(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterSeasonTypeDTO> seasonTypePage = seasonTypeServiceInterface.getAllSeasonTypes(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
    	List<MasterSeasonTypeDTO> seasonTypeList = Optional.ofNullable(seasonTypePage).map(p -> p.getContent()).orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(seasonTypeList, HttpStatus.OK);
	}

}

