package com.choosenfly.hotelbookingsystem.masters.service.roomAmenity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterRoomAmenityDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterSeasonTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRoomAmenities;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterSeasonType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterRoomAmenitiesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomAmenityService implements RoomAmenityServiceImpl{
	
	private MasterRoomAmenitiesRepository masterRoomAmenitiesRepository;
	
	public RoomAmenityService(MasterRoomAmenitiesRepository masterRoomAmenitiesRepository) {
		this.masterRoomAmenitiesRepository = masterRoomAmenitiesRepository;
	}

	@Override
	@Transactional
	public Long saveRoomAmenity(MasterRoomAmenityDTO dto) {
		// TODO Auto-generated method stub
		
		 MasterRoomAmenities entity = new MasterRoomAmenities();
		 entity.setName(dto.getRoomAmenity());
		 entity.setIsDeleted(false);
		 MasterRoomAmenities save = masterRoomAmenitiesRepository.save(entity);
		 if(save.getAmenitiesId() != 0) {
			 return save.getAmenitiesId();
		 }
		return null;
	}

	@Override
	@Transactional
	public MasterRoomAmenityDTO getRoomAmenityById(Long id) {
		// TODO Auto-generated method stub
		MasterRoomAmenities roomAmenityEntity =
				masterRoomAmenitiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room Amenity not found for id : " + id));
		
		MasterRoomAmenityDTO dto = new MasterRoomAmenityDTO();
		dto.setAmenitiesId(roomAmenityEntity.getAmenitiesId());
		dto.setRoomAmenity(roomAmenityEntity.getName());
		dto.setIsDeleted(roomAmenityEntity.getIsDeleted());
		return dto;
	}

	@Override
	@Transactional
	public MasterRoomAmenityDTO editRoomAmenity(Long id, MasterRoomAmenityDTO roomAmenityDTO) {
		// TODO Auto-generated method stub
		MasterRoomAmenities roomAmenityEntity = masterRoomAmenitiesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Room Amenity not found for id : " + id));

		roomAmenityEntity.setName(roomAmenityDTO.getRoomAmenity());
		roomAmenityEntity.setIsDeleted(false);
		MasterRoomAmenities save = masterRoomAmenitiesRepository.save(roomAmenityEntity);
		
		MasterRoomAmenityDTO dto = new MasterRoomAmenityDTO();
		dto.setAmenitiesId(save.getAmenitiesId());
		dto.setRoomAmenity(save.getName());
		dto.setIsDeleted(save.getIsDeleted());
		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteRoomAmenity(Long id) {
		// TODO Auto-generated method stub
		
		MasterRoomAmenities roomAmenityEntity = masterRoomAmenitiesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Room Amenity not found for id : " + id));
		
		masterRoomAmenitiesRepository.delete(roomAmenityEntity);
		return ResponseEntity.ok("Room Amenity with id "+ id + " deleted successfully");
		
	}

	@Override
	@Transactional
	public Page<MasterRoomAmenityDTO> getAllRoomAmenities(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		 Page<MasterRoomAmenities> roomAmenityPage;

		    if (StringUtils.hasText(search)) {
		    	roomAmenityPage = masterRoomAmenitiesRepository.findByNameContainingIgnoreCase(search, pageable);
		    } else {
		    	roomAmenityPage = masterRoomAmenitiesRepository.findAll(pageable);
		    }

		    return roomAmenityPage.map(roomAmenity -> {
		    	MasterRoomAmenityDTO dto = new MasterRoomAmenityDTO();
				dto.setAmenitiesId(roomAmenity.getAmenitiesId());
				dto.setRoomAmenity(roomAmenity.getName());
				dto.setIsDeleted(roomAmenity.getIsDeleted());
				return dto;
		    });
	}

}
