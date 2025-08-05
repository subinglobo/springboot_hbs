package com.choosenfly.hotelbookingsystem.masters.service.roomCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterRoomCategoryDTO;

import jakarta.validation.Valid;

public interface RoomCategoryServiceImpl {

	Long saveRoomCategory(MasterRoomCategoryDTO dto);

	MasterRoomCategoryDTO getRoomCategoryById(Long id);

	MasterRoomCategoryDTO editRoomCategory(Long id, MasterRoomCategoryDTO roomDTO);

	ResponseEntity<String> deleteRoomCategory(Long id);

	Page<MasterRoomCategoryDTO> getAllRoomCategories(Pageable pageable, String search);


}
