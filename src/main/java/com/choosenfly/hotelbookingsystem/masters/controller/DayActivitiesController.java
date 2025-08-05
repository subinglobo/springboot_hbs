package com.choosenfly.hotelbookingsystem.masters.controller;

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

import com.choosenfly.hotelbookingsystem.masters.dto.MasterDayActivitiesDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.masters.service.agentCategory.AgentCategoryServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.dayActivities.DayActivitiesService;
import com.choosenfly.hotelbookingsystem.masters.service.dayActivities.DayActivitiesServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.province.ProvinceServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dayActivities")
public class DayActivitiesController {
	
	
	private final DayActivitiesServiceInterface activitiesServiceInterface;
	
	@Autowired
	public DayActivitiesController(DayActivitiesServiceInterface activitiesServiceInterface) {
		this.activitiesServiceInterface = activitiesServiceInterface;
	}
	
	@PostMapping("/save")
	private Long saveDayActivities(@Valid @RequestBody MasterDayActivitiesDTO activitiesDTO){
		return activitiesServiceInterface.saveDayActivities(activitiesDTO);
	}
	
	@GetMapping("/{id}")
	private MasterDayActivitiesDTO getDayActivitiesById(@PathVariable("id") Long id) {
		
		return activitiesServiceInterface.getDayActivitiesById(id);
	}
	
	@PutMapping("/{id}")
	private MasterDayActivitiesDTO editDayActivities(@PathVariable("id") Long id , @Valid @RequestBody MasterDayActivitiesDTO activitiesDTO) {
		return activitiesServiceInterface.editDayActivities(id , activitiesDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteDayActivities(@PathVariable("id") Long id) {
		return activitiesServiceInterface.deleteDayActivities(id);
	}
	
	@GetMapping
	public ResponseEntity<List<MasterDayActivitiesDTO>> getAllDayActivities(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int limit, // Default to 20 hotels per page
			@RequestParam(required = false) String search) {
		
		System.out.println("entering");

		// Creates a Pageable object specifying the page number and size (limit) for
		// pagination
		Pageable pageable = PageRequest.of(page, limit);

		// search criteria
		Page<MasterDayActivitiesDTO> Page = activitiesServiceInterface.getAllDayActivities(pageable, search);

		// Wraps hotelsPage in an Optional to safely handle null cases (though rare from
		// a repository)
		List<MasterDayActivitiesDTO> listDayActivitiesDTO = Optional.ofNullable(Page)

				// Extracts the List<HotelDTO> from the Page object if statePage is not null
				// (gets the content of the current page)
				.map(p -> p.getContent())

				// Returns an empty list if statePage is null or getContent() returns null
				// (fallback for edge cases)
				.orElse(List.of());

		// Returns the list of HotelDTOs wrapped in a ResponseEntity with HTTP status
		// 200 (OK)
		return new ResponseEntity<>(listDayActivitiesDTO, HttpStatus.OK);
	}


}
