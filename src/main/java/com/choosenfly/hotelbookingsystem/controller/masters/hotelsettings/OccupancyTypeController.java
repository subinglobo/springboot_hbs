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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelTypeDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterOccupancyTypeDTO;
import com.choosenfly.hotelbookingsystem.service.masters.occupancyType.OccupancyTypeServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/occupancyType")
public class OccupancyTypeController {
	
	private final OccupancyTypeServiceInterface occupancyTypeServiceInterface;
	
	@Autowired
	public OccupancyTypeController(OccupancyTypeServiceInterface occupancyTypeServiceInterface) {
		this.occupancyTypeServiceInterface = occupancyTypeServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveOccupancyType(@Valid @RequestBody MasterOccupancyTypeDTO dto){
		return occupancyTypeServiceInterface.saveOccupancyType(dto);
	}
	
	@GetMapping("/{id}")
	private MasterOccupancyTypeDTO getOccupancyTypeById(@PathVariable("id") Long id) {
		
		return occupancyTypeServiceInterface.getOccupancyTypeById(id);
	}
	
	@PutMapping("/{id}")
	private MasterOccupancyTypeDTO editOccupancyType(@PathVariable("id") Long id , @Valid @RequestBody MasterOccupancyTypeDTO occupancyDTO) {
		return occupancyTypeServiceInterface.editOccupancyType(id , occupancyDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteOccupancyType(@PathVariable("id") Long id) {
		return occupancyTypeServiceInterface.deleteOccupancyType(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterOccupancyTypeDTO>> getAllOccupanctTypes(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterOccupancyTypeDTO> occupancytypePage = occupancyTypeServiceInterface.getAllOccupanctTypes(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterOccupancyTypeDTO> listOccupanctTypeDTO = Optional.ofNullable(occupancytypePage)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listOccupanctTypeDTO, HttpStatus.OK);
	}

}

