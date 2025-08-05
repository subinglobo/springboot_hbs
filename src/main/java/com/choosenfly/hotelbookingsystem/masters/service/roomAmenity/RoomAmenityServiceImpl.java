package com.choosenfly.hotelbookingsystem.masters.service.roomAmenity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterRoomAmenityDTO;

public interface RoomAmenityServiceImpl {

	Long saveRoomAmenity(MasterRoomAmenityDTO dto);

	MasterRoomAmenityDTO getRoomAmenityById(Long id);

	MasterRoomAmenityDTO editRoomAmenity(Long id,MasterRoomAmenityDTO roomAmenityDTO);

	ResponseEntity<String> deleteRoomAmenity(Long id);

	Page<MasterRoomAmenityDTO> getAllRoomAmenities(Pageable pageable, String search);

}
