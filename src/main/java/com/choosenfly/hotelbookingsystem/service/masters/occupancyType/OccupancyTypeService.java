package com.choosenfly.hotelbookingsystem.service.masters.occupancyType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterOccupancyTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterOccupancyType;
import com.choosenfly.hotelbookingsystem.repository.occupancy.OccupancyTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class OccupancyTypeService implements OccupancyTypeServiceInterface{
	
	private final OccupancyTypeRepository  occupancyTypeRepository;
	
	public OccupancyTypeService(OccupancyTypeRepository  occupancyTypeRepository) {
		this.occupancyTypeRepository = occupancyTypeRepository;
		
	}

	@Override
	@Transactional
	public Long saveOccupancyType(@Valid MasterOccupancyTypeDTO dto) {
		// TODO Auto-generated method stub
	
		MasterOccupancyType entity = new MasterOccupancyType();
		entity.setName(dto.getOccupancy());
		entity.setIsDeleted(false);
		MasterOccupancyType save = occupancyTypeRepository.save(entity);
		if(save.getOccupancyTypeId() != 0) {
			return save.getOccupancyTypeId();
		}
		
		return null;
	}

	@Override
	@Transactional
	public MasterOccupancyTypeDTO getOccupancyTypeById(Long id) {
		// TODO Auto-generated method stub
		
		MasterOccupancyType entityData = 
				occupancyTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Occupancy Type not found for id :" +id));
		
		MasterOccupancyTypeDTO masterOccupancyTypeDTO = new MasterOccupancyTypeDTO();
		masterOccupancyTypeDTO.setOccupancyTypeId(entityData.getOccupancyTypeId());
		masterOccupancyTypeDTO.setOccupancy(entityData.getName());
		masterOccupancyTypeDTO.setIsDeleted(entityData.getIsDeleted());
	
		return masterOccupancyTypeDTO;
	}

	@Override
	@Transactional
	public MasterOccupancyTypeDTO editOccupancyType(Long id, @Valid MasterOccupancyTypeDTO occupancyDTO) {
		// TODO Auto-generated method stub

		MasterOccupancyType entityData = occupancyTypeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Occupancy Type not found for id :" + id));
		entityData.setName(occupancyDTO.getOccupancy());
		entityData.setIsDeleted(false);
		MasterOccupancyType save = occupancyTypeRepository.save(entityData);
		
		MasterOccupancyTypeDTO masterOccupancyTypeDTO = new MasterOccupancyTypeDTO();
		masterOccupancyTypeDTO.setOccupancyTypeId(save.getOccupancyTypeId());
		masterOccupancyTypeDTO.setOccupancy(save.getName());
		masterOccupancyTypeDTO.setIsDeleted(save.getIsDeleted());
		
		return masterOccupancyTypeDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteOccupancyType(Long id) {
		// TODO Auto-generated method stub
		
		MasterOccupancyType entityData = occupancyTypeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Occupancy Type not found for id :" + id));
		
		occupancyTypeRepository.delete(entityData);
		
		return ResponseEntity.ok("Province with id " + id + " deleted successfully");
	}

	@Override
	@Transactional
	public Page<MasterOccupancyTypeDTO> getAllOccupanctTypes(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		
	    Page<MasterOccupancyType> occupancyPage;

	    if (StringUtils.hasText(search)) {
	    	occupancyPage = occupancyTypeRepository.findByNameContainingIgnoreCase(search, pageable);
	    } else {
	    	occupancyPage = occupancyTypeRepository.findAll(pageable);
	    }

	    return occupancyPage.map(occupancy -> {
	    	MasterOccupancyTypeDTO dto = new MasterOccupancyTypeDTO();
	    	dto.setOccupancyTypeId(occupancy.getOccupancyTypeId());
	    	dto.setOccupancy(occupancy.getName());
	    	dto.setIsDeleted(occupancy.getIsDeleted());
	        return dto;
	    });
	}

}
