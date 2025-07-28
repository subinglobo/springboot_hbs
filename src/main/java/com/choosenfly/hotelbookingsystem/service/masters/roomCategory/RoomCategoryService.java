package com.choosenfly.hotelbookingsystem.service.masters.roomCategory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterRoomCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterSeasonTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterRoomCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterSeasonType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterRoomCategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomCategoryService implements RoomCategoryServiceImpl {

	private MasterRoomCategoryRepository masterRoomCategoryRepository;

	public RoomCategoryService(MasterRoomCategoryRepository masterRoomCategoryRepository) {
		this.masterRoomCategoryRepository = masterRoomCategoryRepository;
	}

	@Override
	@Transactional
	public Long saveRoomCategory(MasterRoomCategoryDTO dto) {
		// TODO Auto-generated method stub

		MasterRoomCategory entity = new MasterRoomCategory();
		entity.setName(dto.getRoomCategory());
		entity.setCategoryCode(dto.getCategoryCode());
		entity.setIsDeleted(false);
		MasterRoomCategory save = masterRoomCategoryRepository.save(entity);
		if (save.getRoomCategoryId() != 0) {
			return save.getRoomCategoryId();
		}
		return null;
	}

	@Override
	@Transactional
	public MasterRoomCategoryDTO getRoomCategoryById(Long id) {
		// TODO Auto-generated method stub

		MasterRoomCategory roomCategoryEntity = masterRoomCategoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Room Category not found for id : " + id));

		MasterRoomCategoryDTO dto = new MasterRoomCategoryDTO();
		dto.setRoomCategoryId(roomCategoryEntity.getRoomCategoryId());
		dto.setRoomCategory(roomCategoryEntity.getName());
		dto.setCategoryCode(roomCategoryEntity.getCategoryCode());
		dto.setIsDeleted(roomCategoryEntity.getIsDeleted());

		return dto;
	}

	@Override
	@Transactional
	public MasterRoomCategoryDTO editRoomCategory(Long id, MasterRoomCategoryDTO roomDTO) {
		// TODO Auto-generated method stub

		MasterRoomCategory roomCategoryEntity = masterRoomCategoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Room Category not found for id : " + id));

		roomCategoryEntity.setName(roomDTO.getRoomCategory());
		roomCategoryEntity.setCategoryCode(roomDTO.getCategoryCode());
		roomCategoryEntity.setIsDeleted(false);
		MasterRoomCategory save = masterRoomCategoryRepository.save(roomCategoryEntity);

		MasterRoomCategoryDTO dto = new MasterRoomCategoryDTO();
		dto.setRoomCategoryId(save.getRoomCategoryId());
		dto.setRoomCategory(save.getName());
		dto.setCategoryCode(save.getCategoryCode());
		dto.setIsDeleted(save.getIsDeleted());

		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteRoomCategory(Long id) {
		// TODO Auto-generated method stub

		MasterRoomCategory roomCategoryEntity = masterRoomCategoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Room Category not found for id : " + id));

		masterRoomCategoryRepository.delete(roomCategoryEntity);

		return ResponseEntity.ok("Room Category  with id " + id + " deleted successfully");
	}

	@Override
	public Page<MasterRoomCategoryDTO> getAllRoomCategories(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		Page<MasterRoomCategory> roomCategoryPage;

		if (StringUtils.hasText(search)) {
			roomCategoryPage = masterRoomCategoryRepository.findByNameContainingIgnoreCase(search, pageable);
		} else {
			roomCategoryPage = masterRoomCategoryRepository.findAll(pageable);
		}

		return roomCategoryPage.map(roomCat -> {
			MasterRoomCategoryDTO dto = new MasterRoomCategoryDTO();
			dto.setRoomCategoryId(roomCat.getRoomCategoryId());
			dto.setRoomCategory(roomCat.getName());
			dto.setCategoryCode(roomCat.getCategoryCode());
			dto.setIsDeleted(roomCat.getIsDeleted());
			return dto;
		});
	}

}
