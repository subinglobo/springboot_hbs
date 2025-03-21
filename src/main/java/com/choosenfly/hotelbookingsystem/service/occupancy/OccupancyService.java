package com.choosenfly.hotelbookingsystem.service.occupancy;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.dto.hotel.HotelRoomDTO;
import com.choosenfly.hotelbookingsystem.dto.hotel.RoomOccupancyDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyPatchDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.HotelOccupancyResponseDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.ListOccupanyDTO;
import com.choosenfly.hotelbookingsystem.dto.occupancy.OccupancyValidityDTO;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelRoom;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarketType;
import com.choosenfly.hotelbookingsystem.entities.occupancy.HotelOccupancy;
import com.choosenfly.hotelbookingsystem.entities.occupancy.OccupancyValidity;
import com.choosenfly.hotelbookingsystem.entities.occupancy.RoomOccupancy;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException;
import com.choosenfly.hotelbookingsystem.repository.hotel.HotelRepository;
import com.choosenfly.hotelbookingsystem.repository.hotel.HotelRoomRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterOccupancyType;
import com.choosenfly.hotelbookingsystem.repository.occupancy.OccupancyRepository;
import com.choosenfly.hotelbookingsystem.repository.occupancy.OccupancyTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class OccupancyService implements OccupancyServiceInterface {

	private final HotelRepository hotelRepository;

	private final OccupancyRepository occupancyRepository;

	private final HotelRoomRepository hotelRoomRepository;

	private final OccupancyTypeRepository occupancyTypeRepository;

	private final MasterMarketTypeRepository masterMarketTypeRepository;

	@Autowired
	public OccupancyService(OccupancyRepository occupancyRepository, HotelRepository hotelRepository,
			HotelRoomRepository hotelRoomRepository, OccupancyTypeRepository occupancyTypeRepository,
			MasterMarketTypeRepository masterMarketTypeRepository) {
		this.occupancyRepository = occupancyRepository;
		this.hotelRepository = hotelRepository;
		this.hotelRoomRepository = hotelRoomRepository;
		this.occupancyTypeRepository = occupancyTypeRepository;
		this.masterMarketTypeRepository = masterMarketTypeRepository;
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
		HotelOccupancy persistedhotelOccupancy = occupancyRepository.save(hotelOccupancy);

		// Fetch all required hotel rooms in a batch to optimize performance
		List<Long> roomIds = request.getHotelRooms().stream().map(HotelRoomDTO::getId).toList();
		List<HotelRoom> rooms = hotelRoomRepository.findAllById(roomIds);

		// Create a map of room IDs to their respective HotelRoom objects for quick
		// lookup
		Map<Long, HotelRoom> roomMap = rooms.stream().collect(Collectors.toMap(HotelRoom::getId, r -> r));

		// Process each hotel room received in the request
		List<HotelRoom> hotelRooms = request.getHotelRooms().stream().map(hotelRoomDTO -> {
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
			room.setHotelOccupancy(persistedhotelOccupancy); // Now safe because hotelOccupancy is persisted
			return room;
		}).collect(Collectors.toList());

		// Set the hotel rooms list in the hotel occupancy entity
		persistedhotelOccupancy.setHotelRoom(hotelRooms);

		// Process the validity periods from the request
		List<OccupancyValidity> occupancyValidityList = Optional.ofNullable(request.getValidityPeriods())
				.orElse(Collections.emptyList()).stream().map(validity -> {
					OccupancyValidity occupancyValidity = new OccupancyValidity();
					occupancyValidity.setHotelOccupancy(persistedhotelOccupancy); // Now safe because hotelOccupancy is
																					// persisted
					occupancyValidity.setValidityFrom(validity.getValidityFrom());
					occupancyValidity.setValidityTo(validity.getValidityTo());
					return occupancyValidity;
				}).collect(Collectors.toList());

		// Set the validity periods in the hotel occupancy entity
		persistedhotelOccupancy.setValidityPeriods(occupancyValidityList);

		// Save the updated hotel occupancy entity with all associations
		return occupancyRepository.save(persistedhotelOccupancy);
	}

	@Override
	@Transactional
	public List<ListOccupanyDTO> getHotelOccupancies(Long hotelId) {
		// TODO Auto-generated method stub

		Hotel hotel = hotelRepository.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("Hotel Not Found with Id " + hotelId));

		List<ListOccupanyDTO> listOccupancies = hotel.getHotelOccupancies().stream().map(occupancy -> {

			ListOccupanyDTO occup = new ListOccupanyDTO();
			occup.setHotelName(hotel.getHotelName());
			occup.setIsLive(occupancy.isLive());
			occup.setMarketTypeName(Optional.ofNullable(occupancy.getMarketType())
					.map(MasterMarketType::getName)
					.orElse("-"));
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
				Optional.ofNullable(hotelOccupancy.getMarketType()).map(MasterMarketType::getName).orElse("-") 
		);
		hotelOccupancyResponseDTO.setMarketTypeId(Optional.ofNullable(hotelOccupancy.getMarketType())
				.map(MasterMarketType::getMarketTypeId).orElse(null));

		// Safely handle hotelRoom list
		List<HotelRoom> hotelRoom = Optional.ofNullable(hotelOccupancy.getHotelRoom()).orElse(Collections.emptyList());

		List<HotelRoomDTO> hotelRoomDTOList = hotelRoom.stream().map(room -> {
			HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
			hotelRoomDTO.setRoomName(Optional.ofNullable(room.getRoomName()).orElse("-") // or "Unknown Room" if
																							// preferred
			);
			hotelRoomDTO.setId(Optional.ofNullable(room.getId()).orElse(null));

			// Safely handle roomOccupancies list
			List<RoomOccupancy> roomOccupancies = Optional.ofNullable(room.getRoomOccupancies())
					.orElse(Collections.emptyList());

			List<RoomOccupancyDTO> roomOccupancyDTOs = roomOccupancies.stream().map(roomOccupancy -> {
				RoomOccupancyDTO roomOccupancyDTO = new RoomOccupancyDTO();
				roomOccupancyDTO.setExtraAdult(Optional.ofNullable(roomOccupancy.getExtraAdult()).orElse(0));
				roomOccupancyDTO.setExtraChild(Optional.ofNullable(roomOccupancy.getExtraChild()).orElse(0));
				roomOccupancyDTO.setId(Optional.ofNullable(roomOccupancy.getId()).orElse(null));
				roomOccupancyDTO.setOccupancyTypeId(Optional.ofNullable(roomOccupancy.getOccupancyType())
						.map(MasterOccupancyType::getOccupancyTypeId).orElse(null));
				roomOccupancyDTO.setOccupancyTypeName(Optional.ofNullable(roomOccupancy.getOccupancyType())
						.map(MasterOccupancyType::getName).orElse(null) // or "Unknown Type" if preferred
				);
				roomOccupancyDTO.setTotalAdult(Optional.ofNullable(roomOccupancy.getTotalAdult()).orElse(0));
				roomOccupancyDTO.setTotalChild(Optional.ofNullable(roomOccupancy.getTotalChild()).orElse(0));
				return roomOccupancyDTO;
			}).collect(Collectors.toList());

			hotelRoomDTO.setRoomOccupancies(roomOccupancyDTOs);
			return hotelRoomDTO;
		}).collect(Collectors.toList());

		hotelOccupancyResponseDTO.setRooms(hotelRoomDTOList);

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

}
