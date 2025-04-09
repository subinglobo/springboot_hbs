package com.choosenfly.hotelbookingsystem.service.availability;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.availability.AvailabilityValidityDTO;
import com.choosenfly.hotelbookingsystem.dto.availability.HotelAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.dto.availability.HotelListAvailabilityDTO;
import com.choosenfly.hotelbookingsystem.entities.availability.AvailabilityValidity;
import com.choosenfly.hotelbookingsystem.entities.availability.HotelAvailability;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelRoom;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarketType;
import com.choosenfly.hotelbookingsystem.entities.master.MasterRoomCategory;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.repository.availability.AvailabilityRepository;
import com.choosenfly.hotelbookingsystem.repository.availability.AvailabilityValidityRepository;
import com.choosenfly.hotelbookingsystem.repository.hotel.HotelRepository;
import com.choosenfly.hotelbookingsystem.repository.hotel.HotelRoomRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMarketTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class AvailabilityService implements AvailabilityServiceInterface {

	private final AvailabilityRepository availabilityRepository;

	private final HotelRoomRepository hotelRoomRepository;

	private final MasterMarketTypeRepository marketTypeRepository;
	
	private final HotelRepository hotelRepository;

	private final AvailabilityValidityRepository availabilityValidityRepository;
	
	@Autowired
	public AvailabilityService(AvailabilityRepository availabilityRepository, HotelRoomRepository hotelRoomRepository,
			MasterMarketTypeRepository marketTypeRepository,HotelRepository hotelRepository,AvailabilityValidityRepository availabilityValidityRepository) {
		this.availabilityRepository = availabilityRepository;
		this.hotelRoomRepository = hotelRoomRepository;
		this.marketTypeRepository = marketTypeRepository;
		this.hotelRepository = hotelRepository;
		this.availabilityValidityRepository = availabilityValidityRepository;
	}

	@Override
	@Transactional
	public HotelAvailabilityDTO addAvailability(HotelAvailabilityDTO availability) {
		// TODO Auto-generated method stub

		HotelAvailability hotelAvailability = new HotelAvailability();
		
		Hotel hotel = hotelRepository.findById(availability.getHotelId())
		.orElseThrow(() -> new HotelNotFoundException("Hotel not found with id "+availability.getHotelId()));
	
		hotelAvailability.setHotel(hotel);
		hotelAvailability.setStatus(false);
		
		hotelAvailability.setAvailabilityType(availability.getAvailabilityType());

		HotelRoom room = hotelRoomRepository.findById(availability.getHotelRoomId()).orElseThrow(
				() -> new EntityNotFoundException("Room not found with id : " + availability.getHotelRoomId()));

		hotelAvailability.setHotelRoom(room);
		hotelAvailability.setId(availability.getId());

		MasterMarketType marketType = marketTypeRepository.findById(availability.getMarketTypeId()).orElseThrow(
				() -> new EntityNotFoundException("Market Type not found with id : " + availability.getMarketTypeId()));
		hotelAvailability.setMarketType(marketType);
		hotelAvailability.setNoOfRooms(availability.getNoOfRooms());
		hotelAvailability.setReleaseDay(availability.getReleaseDay());

		List<AvailabilityValidityDTO> availabilityValidities = availability.getAvailabilityValidities();

		List<AvailabilityValidity> availabilityValidity = availabilityValidities.stream().map(validityDTO -> {

			AvailabilityValidity validity = new AvailabilityValidity();
			validity.setHotelAvailability(hotelAvailability);
			validity.setValidityFrom(validityDTO.getValidityFrom());
			validity.setValidityTo(validityDTO.getValidityTo());

			return validity;
		}).collect(Collectors.toList());

		hotelAvailability.setValidityPeriods(availabilityValidity);
		hotelAvailability.setCheckinAllowedDays(availability.getCheckinAllowedDays()); 
		

		//saving hotel availability 
		HotelAvailability hotelAvailabilityResponse = availabilityRepository.save(hotelAvailability);

		HotelAvailabilityDTO hotelAvailabilityResponseDTO = new HotelAvailabilityDTO();
		
		hotelAvailabilityResponseDTO.setAvailabilityType(hotelAvailabilityResponse.getAvailabilityType());

		
		List<AvailabilityValidity> validityPeriods = hotelAvailabilityResponse.getValidityPeriods();
		
		List<AvailabilityValidityDTO> validityResponseList = validityPeriods.stream()
		.map( validity -> {
			 
			AvailabilityValidityDTO validityDTO = new AvailabilityValidityDTO();
			validityDTO.setHotelAvailabilityId(validity.getHotelAvailability().getId());
			validityDTO.setId(validity.getId());
			validityDTO.setValidityFrom(validity.getValidityFrom());
			validityDTO.setValidityTo(validity.getValidityTo());
			
			return validityDTO;
			
		}).collect(Collectors.toList());
		
		
		
		hotelAvailabilityResponseDTO.setAvailabilityValidities(validityResponseList);
		hotelAvailabilityResponseDTO.setHotelId(availability.getHotelId());
		hotelAvailabilityResponseDTO.setHotelRoomId(hotelAvailabilityResponse.getHotelRoom().getId());
		hotelAvailabilityResponseDTO.setId(hotelAvailabilityResponse.getId());
		hotelAvailabilityResponseDTO.setMarketTypeId(hotelAvailabilityResponse.getMarketType().getMarketTypeId());
		hotelAvailabilityResponseDTO.setNoOfRooms(hotelAvailabilityResponse.getNoOfRooms());
		hotelAvailabilityResponseDTO.setReleaseDay(hotelAvailabilityResponse.getReleaseDay());
		hotelAvailabilityResponseDTO.setCheckinAllowedDays(hotelAvailabilityResponse.getCheckinAllowedDays());
		return hotelAvailabilityResponseDTO;
	}

	@Override
	@Transactional
	public List<HotelListAvailabilityDTO> getAvailabilities(Long hotelId) {
		// TODO Auto-generated method stub
		System.err.println("First Query :: ");
		Hotel hotel = hotelRepository.findHotelWithAvailabilitiesByHotelId(hotelId);
		
		System.err.println("After fetch, before getHotelAvailabilities: " + hotel.getHotelId());
		
		List<HotelAvailability> hotelAvailabilities = hotel.getHotelAvailabilities();
		
		System.err.println("After getHotelAvailabilities, before stream");
		
		List<HotelListAvailabilityDTO> collect = hotelAvailabilities.stream()
		.map(validity -> {
			
			HotelListAvailabilityDTO dto = new HotelListAvailabilityDTO();
			dto.setAvailabilityId(validity.getId());
			dto.setAvailabilityType(validity.getAvailabilityType());
			dto.setMarketName(Optional.ofNullable(validity.getMarketType())
					.map(MasterMarketType::getName)
					.orElse(null));
			
			dto.setNoOfRooms(validity.getNoOfRooms());
			dto.setRoomCategory(
					Optional.ofNullable(validity.getHotelRoom())
					.map(HotelRoom::getRoomCategory)
					.map(MasterRoomCategory::getName)
					.orElse(null));
			dto.setStatus(validity.getStatus());
			
			return dto;
		})
		.collect(Collectors.toList());
		System.err.println("After stream, returning collect");
		return collect;
	}

	@Override
	@Transactional
	public HotelAvailabilityDTO editAvailability(Long hotelId, HotelAvailabilityDTO availability) {
		
		HotelAvailability hotelAvailability = availabilityRepository.findById(availability.getId())
		.orElseThrow(() -> new EntityNotFoundException("Availability Not Found with id "+availability.getId()));
		
		HotelRoom room = hotelRoomRepository.findById(availability.getHotelRoomId())
		.orElseThrow(() -> new EntityNotFoundException("Room Not Found with id "+availability.getHotelRoomId()));
		
		hotelAvailability.setAvailabilityType(availability.getAvailabilityType());
		hotelAvailability.setCheckinAllowedDays(availability.getCheckinAllowedDays());
		hotelAvailability.setHotel(hotelAvailability.getHotel());
		hotelAvailability.setHotelRoom(room);
		hotelAvailability.setId(hotelAvailability.getId());
		
		MasterMarketType marketType = marketTypeRepository.findById(availability.getMarketTypeId())
		.orElseThrow(() -> new EntityNotFoundException("Market Type Not Found with id "+availability.getMarketTypeId()));
		hotelAvailability.setMarketType(marketType);
		hotelAvailability.setNoOfRooms(availability.getNoOfRooms());
		hotelAvailability.setReleaseDay(availability.getReleaseDay());
		hotelAvailability.setStatus(false);
		
		List<AvailabilityValidityDTO> availabilityValidities = availability.getAvailabilityValidities();

		
		List<AvailabilityValidity> validityPeriods = hotelAvailability.getValidityPeriods();
		
		validityPeriods.clear();
		
		List<AvailabilityValidity> availabilityValidity = availabilityValidities.stream().map(validityDTO -> {

			 AvailabilityValidity validity = new AvailabilityValidity();
			
			validity.setHotelAvailability(hotelAvailability);
			validity.setValidityFrom(validityDTO.getValidityFrom());
			validity.setValidityTo(validityDTO.getValidityTo());

			return validity;
		}).collect(Collectors.toList());
		
		validityPeriods.addAll(availabilityValidity);
		
		hotelAvailability.setValidityPeriods(validityPeriods);
		
		availabilityRepository.save(hotelAvailability);
		
		return availability;
	}

	@Override
	@Transactional
	public void deleteAvailability(Long hotelId, Long availabilityId) {
		// TODO Auto-generated method stub
		
		HotelAvailability availability = availabilityRepository.findById(availabilityId)
		.orElseThrow(() -> new EntityNotFoundException("Availability Not Found with id "+availabilityId));
		
       Hotel hotel = Optional.ofNullable(availability.getHotel())
       .orElseThrow(() -> new HotelNotFoundException("Hotel Not Found For Provided Availability"));
		
       if(!hotelId.equals(hotel.getHotelId()))
       {
    	   throw new HotelNotFoundException("Availability does not belong to the specified hotel.");
       }
       
		
		availabilityRepository.deleteById(availabilityId);
		
		
	}

}
