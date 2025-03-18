package com.choosenfly.hotelbookingsystem.service.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.HotelDTO;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.util.HotelMapper;

@Service
public class HotelRegistrationService implements HotelRegistrationServiceInterface{

	private final HotelRepository hotelRepository;

	private final HotelMapper hotelMapper;

	@Autowired
	public HotelRegistrationService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
		this.hotelRepository = hotelRepository;
		this.hotelMapper = hotelMapper;
	}

	@Transactional
	public HotelDTO saveHotel(HotelDTO hotelDTO) {

		Hotel hotel = hotelMapper.mapToEntity(hotelDTO);

		Hotel savedHotel = hotelRepository.save(hotel);

		return hotelMapper.mapToDTO(savedHotel);
	}

	@Transactional(readOnly = true)
	public HotelDTO getHotelById(Long id) {

		Hotel hotel = hotelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

		return hotelMapper.mapToDTO(hotel);
	}

	// Marks this method as read-only, optimizing the transaction for queries (no
	// writes)
	@Transactional(readOnly = true)
	public Page<HotelDTO> getHotels(Pageable pageable, String search) {

		// Declares a Page variable to hold the paginated Hotel entities from the
		// repository
		Page<Hotel> hotelsPage;

		// Checks if the search parameter is non-null and non-empty (has meaningful
		// text)
		if (StringUtils.hasText(search)) {

			// Queries the repository for hotels where the name contains the search term
			// (case-insensitive), with pagination
			hotelsPage = hotelRepository.findByHotelNameContainingIgnoreCase(search, pageable);

		} else {

			// Queries the repository for all hotels, applying pagination
			hotelsPage = hotelRepository.findAll(pageable);
		}

		// Converts the Page of Hotel entities to a Page of HotelDTOs using the mapper
		// and returns it
		return hotelsPage.map(hotelMapper::mapToDTO);
	}

	@Transactional
	public HotelDTO updateHotel(HotelDTO hotelDTO, Long id) {

		// Fetch existing hotel entity
		Hotel existingHotel = hotelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

		hotelMapper.mapDTOToExisitingEntity(existingHotel, hotelDTO);

		// Save updated hotel entity
		Hotel updatedHotel = hotelRepository.save(existingHotel);

		// Convert entity to DTO and return
		return hotelMapper.mapToDTO(updatedHotel);
	}

	@Transactional
	public ResponseEntity<String> deleteHotel(Long id) {
		try {
			// Fetch existing hotel entity
			Hotel existingHotel = hotelRepository.findById(id)
					.orElseThrow(() -> new HotelNotFoundException("Hotel not found with id: " + id));

			// Delete the hotel
			hotelRepository.delete(existingHotel);

			// Return success response
			return ResponseEntity.ok("Hotel with id " + id + " deleted successfully");

		} catch (HotelNotFoundException e) {
			// Log the not found error

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (DataIntegrityViolationException e) {
			// Handle case where deletion violates database constraints (e.g., foreign key)

			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Cannot delete hotel with id " + id + " due to existing dependencies");

		} catch (Exception e) {
			// Catch any other unexpected errors

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while deleting hotel with id " + id + ": " + e.getMessage());
		}
	}
}