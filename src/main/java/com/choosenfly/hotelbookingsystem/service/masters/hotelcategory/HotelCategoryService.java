package com.choosenfly.hotelbookingsystem.service.masters.hotelcategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelCategoryDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelCategory;
import com.choosenfly.hotelbookingsystem.repository.master.MasterHotelCategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HotelCategoryService implements HotelCategoryServiceInterface {

	private final MasterHotelCategoryRepository masterHotelCategoryRepository;

	public HotelCategoryService(MasterHotelCategoryRepository masterHotelCategoryRepository) {
		this.masterHotelCategoryRepository = masterHotelCategoryRepository;

	}

	@Override
	@Transactional
	public Long saveHotelCategory(MasterHotelCategoryDTO dto) {
		// TODO Auto-generated method stub

		System.err.println("dto for hotelcategory::" + dto);
		MasterHotelCategory entity = new MasterHotelCategory();
		entity.setName(dto.getHotelCategory());
		entity.setTagLine(dto.getTagLine()); 
		entity.setIsDeleted(false);

		MasterHotelCategory save = masterHotelCategoryRepository.save(entity);
		if (save.getHotelCategoryId() != 0) {
			return save.getHotelCategoryId();
		}

		return null;
	}

	@Override
	@Transactional
	public MasterHotelCategoryDTO getHotelCategoryById(Long id) {
		// TODO Auto-generated method stub

		MasterHotelCategory categoryEntity = masterHotelCategoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Hotel Category not found for id :" + id));

		MasterHotelCategoryDTO masterHotelCategoryDTO = new MasterHotelCategoryDTO();
		masterHotelCategoryDTO.setHotelCategoryId(categoryEntity.getHotelCategoryId());
		masterHotelCategoryDTO.setHotelCategory(categoryEntity.getName());
		masterHotelCategoryDTO.setTagLine(categoryEntity.getTagLine());
		masterHotelCategoryDTO.setIsDeleted(false);

		return masterHotelCategoryDTO;
	}

	@Override
	@Transactional
	public MasterHotelCategoryDTO editHotelCategory(Long id, MasterHotelCategoryDTO hotelcatDTO) {
		// TODO Auto-generated method stub
		
		MasterHotelCategory categoryEntity = masterHotelCategoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Hotel Category not found for id :" + id));
		
		categoryEntity.setName(hotelcatDTO.getHotelCategory());
		categoryEntity.setTagLine(hotelcatDTO.getTagLine());
		categoryEntity.setIsDeleted(false);
		MasterHotelCategory save = masterHotelCategoryRepository.save(categoryEntity);

		MasterHotelCategoryDTO masterHotelCategoryDTO = new MasterHotelCategoryDTO();
		masterHotelCategoryDTO.setHotelCategoryId(categoryEntity.getHotelCategoryId());
		masterHotelCategoryDTO.setHotelCategory(save.getName());
		masterHotelCategoryDTO.setTagLine(save.getTagLine());
		masterHotelCategoryDTO.setIsDeleted(save.getIsDeleted());

		return masterHotelCategoryDTO;
		
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteHotelCategory(Long id) {
		// TODO Auto-generated method stub
		
		MasterHotelCategory categoryEntity = masterHotelCategoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Hotel Category not found for id :" + id));
		
		masterHotelCategoryRepository.delete(categoryEntity);
		return ResponseEntity.ok("Hotel Category with id " + id + " deleted successfully");
		
	}

	@Override
	@Transactional
	public Page<MasterHotelCategoryDTO> getAllHotelCategory(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		
	    Page<MasterHotelCategory> hotelCatPage;

	    if (StringUtils.hasText(search)) {
	    	hotelCatPage = masterHotelCategoryRepository.findByNameContainingIgnoreCase(search, pageable);
	    } else {
	    	hotelCatPage = masterHotelCategoryRepository.findAll(pageable);
	    }

	    return hotelCatPage.map(hotelcat -> {
	    	MasterHotelCategoryDTO masterHotelCategoryDTO = new MasterHotelCategoryDTO();
	    	masterHotelCategoryDTO.setHotelCategoryId(hotelcat.getHotelCategoryId());
	    	masterHotelCategoryDTO.setHotelCategory(hotelcat.getName());
	    	masterHotelCategoryDTO.setTagLine(hotelcat.getTagLine());
	    	masterHotelCategoryDTO.setIsDeleted(hotelcat.getIsDeleted());

	        return masterHotelCategoryDTO;
	    });
	}

}
