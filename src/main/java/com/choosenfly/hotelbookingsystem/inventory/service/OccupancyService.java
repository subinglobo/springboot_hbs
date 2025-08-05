package com.choosenfly.hotelbookingsystem.inventory.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelOccupancyDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelOccupancyPatchDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelOccupancyResponseDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.HotelRoomDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.ListOccupanyDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.MinimumLengthDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.MinimumLengthResponseDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.MinimumLengthStayDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.MinimumLengthValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.OccupancyValidityDTO;
import com.choosenfly.hotelbookingsystem.inventory.dto.RoomOccupancyDTO;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelOccupancy;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoom;
import com.choosenfly.hotelbookingsystem.inventory.entities.MinimumLength;
import com.choosenfly.hotelbookingsystem.inventory.entities.MinimumLengthStay;
import com.choosenfly.hotelbookingsystem.inventory.entities.MinimumLengthValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.OccupancyValidity;
import com.choosenfly.hotelbookingsystem.inventory.entities.RoomOccupancy;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelRoomRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.MinimumLengthRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.OccupancyRepository;
import com.choosenfly.hotelbookingsystem.inventory.repository.OccupancyTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterOccupancyType;

import jakarta.transaction.Transactional;

@Service
public class OccupancyService implements OccupancyServiceInterface {

	private final HotelRepository hotelRepository;

	private final OccupancyRepository occupancyRepository;

	private final HotelRoomRepository hotelRoomRepository;

	private final OccupancyTypeRepository occupancyTypeRepository;

	private final MasterMarketTypeRepository masterMarketTypeRepository;

	private final MinimumLengthRepository minimumLengthRepository;

	@Autowired
	public OccupancyService(OccupancyRepository occupancyRepository, HotelRepository hotelRepository,
			HotelRoomRepository hotelRoomRepository, OccupancyTypeRepository occupancyTypeRepository,
			MasterMarketTypeRepository masterMarketTypeRepository, MinimumLengthRepository minimumLengthRepository) {
		this.occupancyRepository = occupancyRepository;
		this.hotelRepository = hotelRepository;
		this.hotelRoomRepository = hotelRoomRepository;
		this.occupancyTypeRepository = occupancyTypeRepository;
		this.masterMarketTypeRepository = masterMarketTypeRepository;
		this.minimumLengthRepository = minimumLengthRepository;
	}

	@Override
	@Transactional
	public HotelOccupancy addOccupancy(HotelOccupancyDTO request) {
		// Create a new HotelOccupancy instance and set initial values
		HotelOccupancy hotelOccupancy = new HotelOccupancy();
		hotelOccupancy.setLive(false);
		hotelOccupancy.setDeleted(false);

		// Fetch the hotel entity by ID and handle if not found
		Hotel hotel = hotelRepository.findById(request.getHotelId())
				.orElseThrow(() -> new HotelNotFoundException("Hotel not found with id " + request.getHotelId()));
		hotelOccupancy.setHotel(hotel);

		// Fetch the market type entity by ID and handle if not found
		MasterMarketType marketType = masterMarketTypeRepository.findById(request.getMarketTypeId()).orElseThrow(
				() -> new EntityNotFoundException("Market Type not found for id " + request.getMarketTypeId()));
		hotelOccupancy.setMarketType(marketType);

		// Save HotelOccupancy first to make it persistent

		HotelOccupancy persistedOccupancyEntity = occupancyRepository.save(hotelOccupancy);

		// Fetch all required hotel rooms in a batch to optimize performance
		List<Long> roomIds = request.getHotelRooms().stream().map(HotelRoomDTO::getId).toList();
		List<HotelRoom> rooms = hotelRoomRepository.findAllById(roomIds);

		// Create a map of room IDs to their respective HotelRoom objects for quick
		// lookup
		Map<Long, HotelRoom> roomMap = rooms.stream().collect(Collectors.toMap(HotelRoom::getId, r -> r));

		// Process each hotel room received in the request
		request.getHotelRooms().stream().map(hotelRoomDTO -> {
			// Fetch the hotel room from the map
			HotelRoom room = roomMap.get(hotelRoomDTO.getId());
			if (room == null) {
				throw new EntityNotFoundException("Room not found with id " + hotelRoomDTO.getId());
			}

			// Fetch all required occupancy types in a batch to optimize performance
			List<Long> occupancyTypeIds = hotelRoomDTO.getRoomOccupancies().stream()
					.map(RoomOccupancyDTO::getOccupancyTypeId).toList();
			List<MasterOccupancyType> occupancyTypes = occupancyTypeRepository.findAllById(occupancyTypeIds);

			// Create a map of occupancy type IDs to their respective MasterOccupancyType
			// objects for quick lookup
			Map<Long, MasterOccupancyType> occupancyTypeMap = occupancyTypes.stream()
					.collect(Collectors.toMap(MasterOccupancyType::getOccupancyTypeId, o -> o));

			// Get existing room occupancies and clear them to prevent stale data
			List<RoomOccupancy> existingRoomOccupancies = room.getRoomOccupancies();
			existingRoomOccupancies.clear();

			// Process each room occupancy in the request
			List<RoomOccupancy> newRoomOccupancies = Optional.ofNullable(hotelRoomDTO.getRoomOccupancies())
					.orElse(Collections.emptyList()).stream().map(occupancyDTO -> {
						RoomOccupancy roomOccupancy = new RoomOccupancy();
						roomOccupancy.setExtraAdult(occupancyDTO.getExtraAdult());
						roomOccupancy.setExtraChild(occupancyDTO.getExtraChild());
						roomOccupancy.setHotelRoom(room);
						roomOccupancy.setHotelOccupancy(persistedOccupancyEntity);
						// Fetch the occupancy type from the map
						MasterOccupancyType occupancyType = occupancyTypeMap.get(occupancyDTO.getOccupancyTypeId());
						if (occupancyType == null) {
							throw new EntityNotFoundException(
									"Occupancy Type not found with id " + occupancyDTO.getOccupancyTypeId());
						}

						roomOccupancy.setOccupancyType(occupancyType);
						roomOccupancy.setTotalAdult(occupancyDTO.getTotalAdult());
						roomOccupancy.setTotalChild(occupancyDTO.getTotalChild());
						return roomOccupancy;
					}).collect(Collectors.toList());

			// Add the new occupancies to the room and update its occupancies list
			existingRoomOccupancies.addAll(newRoomOccupancies);
			room.setRoomOccupancies(existingRoomOccupancies);
			return room;
		}).collect(Collectors.toList());

		// Set the hotel rooms list in the hotel occupancy entity

		// Process the validity periods from the request
		List<OccupancyValidity> occupancyValidityList = Optional.ofNullable(request.getValidityPeriods())
				.orElse(Collections.emptyList()).stream().map(validity -> {
					OccupancyValidity occupancyValidity = new OccupancyValidity();
					occupancyValidity.setHotelOccupancy(hotelOccupancy); // Now safe because hotelOccupancy is
																			// persisted
					occupancyValidity.setValidityFrom(validity.getValidityFrom());
					occupancyValidity.setValidityTo(validity.getValidityTo());
					return occupancyValidity;
				}).collect(Collectors.toList());

		// Set the validity periods in the hotel occupancy entity
		hotelOccupancy.setValidityPeriods(occupancyValidityList);

		// Save the updated hotel occupancy entity with all associations
		return occupancyRepository.save(persistedOccupancyEntity);
	}

	@Override
	@Transactional
	public List<ListOccupanyDTO> getHotelOccupancies(Long hotelId) {

		Hotel hotel = hotelRepository.findHotelWithOccupanciesByHotelId(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel Not Found with Id " + hotelId));

		List<ListOccupanyDTO> listOccupancies = hotel.getHotelOccupancies().stream().map(occupancy -> {

			ListOccupanyDTO occup = new ListOccupanyDTO();
			occup.setHotelName(hotel.getHotelName());
			occup.setIsLive(occupancy.isLive());
			occup.setMarketTypeName(
					Optional.ofNullable(occupancy.getMarketType()).map(MasterMarketType::getName).orElse("-"));
			occup.setOccupancyId(occupancy.getId());

			return occup;
		}).collect(Collectors.toList());

		return listOccupancies;

	}

	@Override
	@Transactional
	public HotelOccupancyResponseDTO getHotelOccupancy(Long hotelId, Long occupancyId) {
		// TODO Auto-generated method stub

		HotelOccupancy hotelOccupancy = occupancyRepository.findById(occupancyId)
				.orElseThrow(() -> new EntityNotFoundException(
						"Occupancy Not Found with id " + occupancyId + " for hotel with id " + hotelId));

		HotelOccupancyResponseDTO hotelOccupancyResponseDTO = mapToOccupancyResponseDTO(hotelOccupancy);

		return hotelOccupancyResponseDTO;

	}

	private HotelOccupancyResponseDTO mapToOccupancyResponseDTO(HotelOccupancy hotelOccupancy) {
		// Return an empty DTO if hotelOccupancy is null
		if (hotelOccupancy == null) {
			return new HotelOccupancyResponseDTO();
		}

		HotelOccupancyResponseDTO hotelOccupancyResponseDTO = new HotelOccupancyResponseDTO();

		// Safely set marketName and marketTypeId
		hotelOccupancyResponseDTO.setMarketName(
				Optional.ofNullable(hotelOccupancy.getMarketType()).map(MasterMarketType::getName).orElse("-"));
		hotelOccupancyResponseDTO.setMarketTypeId(Optional.ofNullable(hotelOccupancy.getMarketType())
				.map(MasterMarketType::getMarketTypeId).orElse(null));

		// Safely handle hotelRoom list
		List<RoomOccupancy> roomOccupancyList = Optional.ofNullable(hotelOccupancy.getRoomOccupancy())
				.orElse(Collections.emptyList());

		List<RoomOccupancyDTO> roomOccupancyDTOList = roomOccupancyList.stream().map(roomOccupancy -> {

			RoomOccupancyDTO roomOccupancyDTO = new RoomOccupancyDTO();
			roomOccupancyDTO.setExtraAdult(roomOccupancy.getExtraAdult());
			roomOccupancyDTO.setExtraChild(roomOccupancy.getExtraChild());
			roomOccupancyDTO.setId(roomOccupancy.getId());
			roomOccupancyDTO.setOccupancyTypeId(roomOccupancy.getOccupancyType().getOccupancyTypeId());
			roomOccupancyDTO.setOccupancyTypeName(roomOccupancy.getOccupancyType().getName());
			roomOccupancyDTO.setRoomId(roomOccupancy.getHotelRoom().getId());
			roomOccupancyDTO.setRoomName(roomOccupancy.getHotelRoom().getRoomName());
			roomOccupancyDTO.setTotalAdult(roomOccupancy.getTotalAdult());
			roomOccupancyDTO.setTotalChild(roomOccupancy.getTotalChild());

			return roomOccupancyDTO;

		}).collect(Collectors.toList());

		hotelOccupancyResponseDTO.setRooms(roomOccupancyDTOList);

		// Safely handle validityPeriods list
		List<OccupancyValidity> validityPeriods = Optional.ofNullable(hotelOccupancy.getValidityPeriods())
				.orElse(Collections.emptyList());

		List<OccupancyValidityDTO> occupancyValidityDTOList = validityPeriods.stream().map(validity -> {
			OccupancyValidityDTO occupancyValidityDTO = new OccupancyValidityDTO();
			occupancyValidityDTO.setId(Optional.ofNullable(validity.getId()).orElse(null));
			occupancyValidityDTO.setValidityFrom(Optional.ofNullable(validity.getValidityFrom()).orElse(null));
			occupancyValidityDTO.setValidityTo(Optional.ofNullable(validity.getValidityTo()).orElse(null));
			return occupancyValidityDTO;
		}).collect(Collectors.toList());

		hotelOccupancyResponseDTO.setValidityList(occupancyValidityDTOList);

		return hotelOccupancyResponseDTO;
	}

	@Override
	@Transactional
	public ListOccupanyDTO updateOccupancyStatus(Long occupancyId, HotelOccupancyPatchDTO patchDTO) {
		// TODO Auto-generated method stub

		HotelOccupancy hotelOccupancy = occupancyRepository.findById(occupancyId)
				.orElseThrow(() -> new EntityNotFoundException("HotelOccupancy not found with id " + occupancyId));

		if (patchDTO.getIsLive() != null) { // Only update if provided
			hotelOccupancy.setLive(patchDTO.getIsLive());
		}

		HotelOccupancy updatedOccupancy = occupancyRepository.save(hotelOccupancy);

		ListOccupanyDTO occup = new ListOccupanyDTO();
		occup.setHotelName(
				Optional.ofNullable(updatedOccupancy.getHotel()).map(Hotel::getHotelName).orElse("Unknown Hotel"));
		occup.setIsLive(updatedOccupancy.isLive());
		occup.setMarketTypeName(Optional.ofNullable(updatedOccupancy.getMarketType()).map(MasterMarketType::getName)
				.orElse("Unknown Market"));
		occup.setOccupancyId(updatedOccupancy.getId());

		return occup;
	}

	@Override
	@Transactional
	public void addMinimumLength(MinimumLengthDTO request) {
		// TODO Auto-generated method stub

		Hotel hotel = hotelRepository.findById(request.getHotelId())
				.orElseThrow(() -> new HotelNotFoundException("Hotel Not Found with id : " + request.getHotelId()));

		MinimumLength minimumLength = new MinimumLength();

		minimumLength.setHotel(hotel);
		minimumLength.setStatus(false);
		minimumLength.setIsDeleted(false);

		List<MinimumLengthStayDTO> hotelRoomDTOs = Optional.ofNullable(request.getHotelRooms())
				.orElse(Collections.emptyList());

		List<MinimumLengthStay> minimumLengthStayList = hotelRoomDTOs.stream().map(minimumLengthStayDTO -> {

			MinimumLengthStay minimumLengthStay = new MinimumLengthStay();

			HotelRoom hotelRoom = hotelRoomRepository.findById(minimumLengthStayDTO.getRoomId()).orElseThrow(
					() -> new EntityNotFoundException("Room Not Found with id " + minimumLengthStayDTO.getRoomId()));
			minimumLengthStay.setRoom(hotelRoom);
			minimumLengthStay.setMinimumLength(minimumLength);
			minimumLengthStay.setMinimumDays(minimumLengthStayDTO.getMinimumLength());

			return minimumLengthStay;
		}).collect(Collectors.toList());

		minimumLength.setMinmumLengthStay(minimumLengthStayList);

		MasterMarketType marketType = masterMarketTypeRepository.findById(request.getMarketTypeId()).orElseThrow(
				() -> new EntityNotFoundException("Market type Not Found with id : " + request.getMarketTypeId()));
		minimumLength.setMarketType(marketType);

		List<MinimumLengthValidityDTO> minimumLengthValidityDTOs = Optional.ofNullable(request.getValidityPeriods())
				.orElse(Collections.emptyList());

		List<MinimumLengthValidity> minimumLengthValidityList = minimumLengthValidityDTOs.stream().map(validity -> {

			MinimumLengthValidity minimumLengthValidity = new MinimumLengthValidity();
			minimumLengthValidity.setMinimumLength(minimumLength);
			minimumLengthValidity.setValidityFrom(validity.getValidityFrom());
			minimumLengthValidity.setValidityTo(validity.getValidityTo());

			return minimumLengthValidity;
		}).collect(Collectors.toList());

		minimumLength.setValidityPeriods(minimumLengthValidityList);

		minimumLengthRepository.save(minimumLength);

	}

	@Override
	@Transactional
	public List<MinimumLengthResponseDTO> getMinimumLengthOfAHottel(Long hotelId) {
		// TODO Auto-generated method stub

		Hotel hotel = hotelRepository.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel not Found with id : " + hotelId));

		List<MinimumLength> minimumLengthList = Optional.ofNullable(hotel.getMinimumLengths())
				.orElse(Collections.emptyList());

		return minimumLengthList.stream().map(minimumLength -> {

			MinimumLengthResponseDTO minimumLengthResponseDTO = new MinimumLengthResponseDTO();
			minimumLengthResponseDTO.setHotelId(hotelId);
			minimumLengthResponseDTO.setHotelName(hotel.getHotelName());
			minimumLengthResponseDTO.setMarketId(minimumLength.getMarketType().getMarketTypeId());
			minimumLengthResponseDTO.setMarketName(minimumLength.getMarketType().getName());
			minimumLengthResponseDTO.setMinimumLengthId(minimumLength.getId());
			minimumLengthResponseDTO.setStatus(minimumLength.getStatus());
			return minimumLengthResponseDTO;

		}).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public MinimumLengthDTO getAMinimumLengthOfHotel(Long hotelId, Long minimumLengthId) {
		// TODO Auto-generated method stub

		MinimumLength minimumLength = minimumLengthRepository.findById(minimumLengthId).orElseThrow(
				() -> new EntityNotFoundException(" Minimum Length not found with id : " + minimumLengthId));

		MinimumLengthDTO minimumLengthDTO = new MinimumLengthDTO();
		minimumLengthDTO.setHotelId(hotelId);
		minimumLengthDTO.setDeleted(minimumLength.getIsDeleted());

		List<MinimumLengthStay> minmumLengthStay = minimumLength.getMinmumLengthStay();

		List<MinimumLengthStayDTO> minimumLengthStayDTOList = minmumLengthStay.stream().map(minmumLength -> {

			MinimumLengthStayDTO minimumLengthStayDTO = new MinimumLengthStayDTO();
			minimumLengthStayDTO.setMinimumLength(minmumLength.getMinimumDays());
			minimumLengthStayDTO.setRoomId(minmumLength.getRoom().getId());
			minimumLengthStayDTO.setId(minmumLength.getId());
			return minimumLengthStayDTO;
		}).collect(Collectors.toList());

		minimumLengthDTO.setHotelRooms(minimumLengthStayDTOList);
		minimumLengthDTO.setId(minimumLength.getId());
		minimumLengthDTO.setLive(minimumLength.getStatus());
		minimumLengthDTO.setMarketTypeId(minimumLength.getMarketType().getMarketTypeId());
		minimumLengthDTO.setValidity(false);

		List<MinimumLengthValidity> validityPeriods = minimumLength.getValidityPeriods();

		List<MinimumLengthValidityDTO> minimumLengthValidityDTOList = validityPeriods.stream().map(validity -> {

			MinimumLengthValidityDTO minimumLengthValidityDTO = new MinimumLengthValidityDTO();
			minimumLengthValidityDTO.setId(validity.getId());
			minimumLengthValidityDTO.setMinimumLengthId(validity.getMinimumLength().getId());
			minimumLengthValidityDTO.setValidityFrom(validity.getValidityFrom());
			minimumLengthValidityDTO.setValidityTo(validity.getValidityTo());

			return minimumLengthValidityDTO;
		}).collect(Collectors.toList());

		minimumLengthDTO.setValidityPeriods(minimumLengthValidityDTOList);

		return minimumLengthDTO;

	}

	@Override
	@Transactional
	public void editHotelOccupancy(Long hotelId, Long occupancyId, HotelOccupancyDTO request) {
		// TODO Auto-generated method stub

		HotelOccupancy occupancy = occupancyRepository.findById(occupancyId)
				.orElseThrow(() -> new EntityNotFoundException("Occupancy Not Found with id : " + occupancyId));

		occupancy.setId(occupancyId);
		occupancy.setDeleted(false);
		occupancy.setHotel(occupancy.getHotel());
		occupancy.setLive(false);

		MasterMarketType marketType = masterMarketTypeRepository.findById(request.getMarketTypeId()).orElseThrow(
				() -> new EntityNotFoundException("Market Not Found With Id " + request.getMarketTypeId()));

		occupancy.setMarketType(marketType);

		List<OccupancyValidity> validityPeriods = occupancy.getValidityPeriods();

		validityPeriods.clear();

		List<OccupancyValidity> validities = request.getValidityPeriods().stream().map(validityPeriod -> {

			occupancy.getValidityPeriods();
			OccupancyValidity validity = new OccupancyValidity();
			validity.setHotelOccupancy(occupancy);
			validity.setId(validityPeriod.getHotelOccupancyId());
			validity.setValidityFrom(validityPeriod.getValidityFrom());
			validity.setValidityTo(validityPeriod.getValidityTo());

			return validity;
		}).collect(Collectors.toList());

		validityPeriods.addAll(validities);

		occupancy.setValidityPeriods(validityPeriods);

		request.getHotelRooms().stream()
				.forEach(a -> a.getRoomOccupancies().stream().forEach(b -> b.setRoomId(a.getRoomTypeId())));

		System.out.println(request);

		List<RoomOccupancy> roomOccupancies = occupancy.getRoomOccupancy();

		roomOccupancies.clear();

		List<RoomOccupancy> roomOccupancyList = request.getHotelRooms().stream().map(a -> a.getRoomOccupancies())
				.flatMap(List::stream).map(dto -> {

					RoomOccupancy roomOccupancy = new RoomOccupancy();
					roomOccupancy.setExtraAdult(dto.getExtraAdult());
					roomOccupancy.setExtraChild(dto.getExtraChild());
					roomOccupancy.setHotelOccupancy(occupancy);
					HotelRoom room = hotelRoomRepository.findById(dto.getRoomId()).orElseThrow(
							() -> new EntityNotFoundException("Room not found with id : " + dto.getRoomId()));

					roomOccupancy.setHotelRoom(room);

					MasterOccupancyType occupancyType = occupancyTypeRepository.findById(dto.getOccupancyTypeId())
							.orElseThrow(() -> new EntityNotFoundException(
									"Occupancy Type not found with " + dto.getOccupancyTypeId()));

					roomOccupancy.setOccupancyType(occupancyType);
					roomOccupancy.setTotalAdult(dto.getTotalAdult());
					roomOccupancy.setTotalChild(dto.getTotalChild());

					return roomOccupancy;

				}).collect(Collectors.toList());

		roomOccupancies.addAll(roomOccupancyList);

		occupancy.setRoomOccupancy(roomOccupancies);
		occupancy.setValidity(true);

		occupancyRepository.save(occupancy);

	}

}
