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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.dto.availability.HotelAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.dto.availability.HotelListAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.dto.hotel.HotelContactDetailsDTO;
import com.choosenfly.hotelbookingsystem.dto.hotel.HotelDTO;
import com.choosenfly.hotelbookingsystem.dto.hotel.HotelMailCentreDTO;
import com.choosenfly.hotelbookingsystem.dto.minimumlength.MinimumLengthDTO;
import com.choosenfly.hotelbookingsystem.dto.minimumlength.MinimumLengthResponseDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyPatchDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyResponseDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.ListOccupanyDTO;
import com.choosenfly.hotelbookingsystem.service.availability.AvailabilityServiceInterface;
import com.choosenfly.hotelbookingsystem.service.hotel.HotelMailtypeServiceInterface;
import com.choosenfly.hotelbookingsystem.service.hotel.HotelRegistrationService;
import com.choosenfly.hotelbookingsystem.service.hotel.HotelRegistrationServiceInterface;
import com.choosenfly.hotelbookingsystem.service.occupancy.OccupancyServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	private final HotelRegistrationServiceInterface hotelService;

	private final HotelMailtypeServiceInterface hotelMailService;
	
	private final OccupancyServiceInterface occupancyService;
	
	private final AvailabilityServiceInterface availabilityService;

	@Autowired
	public HotelController(HotelRegistrationService hotelService, HotelMailtypeServiceInterface hotelMailService,OccupancyServiceInterface occupancyService,AvailabilityServiceInterface availabilityService) {
		this.hotelService = hotelService;
		this.hotelMailService = hotelMailService;
		this.occupancyService = occupancyService;
		this.availabilityService = availabilityService;
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

	@PostMapping("/{hotelId}/occupancies")
	public ResponseEntity<String> addOccupancyToHotel(@PathVariable Long hotelId,
			@RequestBody HotelOccupancyDTO request) {

		request.setHotelId(hotelId);
		
		occupancyService.addOccupancy(request);
		
		return new ResponseEntity<>("Occupancy Added for Hotel with id "+hotelId,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/{hotelId}/occupancies")
	public ResponseEntity<List<ListOccupanyDTO>> getOccupancyDetailsOfHotel(@PathVariable Long hotelId) {

		
		List<ListOccupanyDTO> listOccupancies =   occupancyService.getHotelOccupancies(hotelId);
		
		return new ResponseEntity<>(listOccupancies,HttpStatus.OK);
	}

	
	@GetMapping("/{hotelId}/occupancies/{occupanyId}")
	public ResponseEntity<HotelOccupancyResponseDTO> getOccupancyDetailsOfHotel(@PathVariable("hotelId") Long hotelId,@PathVariable("occupanyId") Long occupancyId) {

		
		HotelOccupancyResponseDTO occupancy =   occupancyService.getHotelOccupancy(hotelId,occupancyId);
		
		return new ResponseEntity<>(occupancy,HttpStatus.OK);
		
	}
	
	@PutMapping("/{hotelId}/occupancies/{occupanyId}")
	public ResponseEntity<Void> editOccupancyDetailsOfHotel(@PathVariable("hotelId") Long hotelId,@PathVariable("occupanyId") Long occupancyId,@RequestBody HotelOccupancyDTO request) {

		
		 occupancyService.editHotelOccupancy(hotelId,occupancyId,request);
		
		 return ResponseEntity.noContent().build();
		
	}
	
	@PatchMapping("/{hotelId}/occupancies/{occupancyId}/status")
    public ResponseEntity<ListOccupanyDTO> updateOccupancyStatus(
            @PathVariable("occupancyId") Long occupancyId,
            @RequestBody HotelOccupancyPatchDTO patchDTO) {
        
        // Update the isLive status and get the updated entity as a DTO
		ListOccupanyDTO updatedOccupancy = occupancyService.updateOccupancyStatus(occupancyId, patchDTO);
        
        // Return the updated DTO with a 200 OK response
        return ResponseEntity.ok(updatedOccupancy);
    }

	
	@PostMapping("/{hotelId}/minimumlengths")
	public ResponseEntity<String> addMinimumLengthToHotel(@PathVariable Long hotelId,
			@RequestBody MinimumLengthDTO request) {

		request.setHotelId(hotelId);
		
		occupancyService.addMinimumLength(request);
		
		return new ResponseEntity<>("Minimum Length Added for Hotel with id "+hotelId,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{hotelId}/minimumlengths")
	public ResponseEntity<List<MinimumLengthResponseDTO>> getMinimumLengthsOfHotel(@PathVariable Long hotelId) {

		
		List<MinimumLengthResponseDTO> minimumLengthsOfAHotel = occupancyService.getMinimumLengthOfAHottel(hotelId);
		
		return new ResponseEntity<>(minimumLengthsOfAHotel,HttpStatus.OK);
	}
	
	@GetMapping("/{hotelId}/minimumlengths/{minimumLengthId}")
	public ResponseEntity<MinimumLengthDTO> getAMinimumLengthOfHotel(@PathVariable("hotelId") Long hotelId,@PathVariable("minimumLengthId") Long minimumLengthId) {

		
		MinimumLengthDTO minimumLengthOfAHotel = occupancyService.getAMinimumLengthOfHotel(hotelId,minimumLengthId);
		
		return new ResponseEntity<>(minimumLengthOfAHotel,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/{hotelId}/availabilities")
	public ResponseEntity<HotelAvailabilityDTO> addAvailabilityToHotel(@PathVariable Long hotelId,
			@RequestBody HotelAvailabilityDTO request) {

		request.setHotelId(hotelId);
		
		HotelAvailabilityDTO addAvailability = availabilityService.addAvailability(request);
		
		return new ResponseEntity<>(addAvailability,HttpStatus.CREATED);
	}
	
	@GetMapping("/{hotelId}/availabilities")
	public ResponseEntity<List<HotelListAvailabilityDTO>> getAvailabilitiesOfAHotel(@PathVariable Long hotelId) {

		
		List<HotelListAvailabilityDTO> availabilities = availabilityService.getAvailabilities(hotelId);
		
		return new ResponseEntity<>(availabilities,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{hotelId}/availabilities/{availabilityId}")
	public ResponseEntity<HotelAvailabilityDTO> editAvailabilitiesOfAHotel(@PathVariable("hotelId") Long hotelId,@RequestBody HotelAvailabilityDTO request) {

		
		HotelAvailabilityDTO updatedAvailability = 	availabilityService.editAvailability(hotelId,request);
		
		
		return new ResponseEntity<>(updatedAvailability,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{hotelId}/availabilities/{availabilityId}")
	public ResponseEntity<Void> deleteAvailabilitiesOfAHotel(@PathVariable("hotelId") Long hotelId, @PathVariable("availabilityId") Long availabilityId) {

		
		
		
	   availabilityService.deleteAvailability(hotelId,availabilityId);
		
		
		return ResponseEntity.noContent().build();
		
	}
}
