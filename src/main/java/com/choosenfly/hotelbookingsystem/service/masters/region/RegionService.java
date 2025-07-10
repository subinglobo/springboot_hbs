package com.choosenfly.hotelbookingsystem.service.masters.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterRegionDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterRegion;
import com.choosenfly.hotelbookingsystem.repository.master.MasterRegionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class RegionService  implements RegionServiceInterface{
	
	@Autowired
	private MasterRegionRepository masterRegionRepository;

	@Override
	@Transactional
	public Long saveRegion(@Valid MasterRegionDTO regionDTO) {
		// TODO Auto-generated method stub
		
		MasterRegion entity = new MasterRegion();
		entity.setName(regionDTO.getName());
		entity.setIsDeleted(false);
		MasterRegion save = masterRegionRepository.save(entity);
		if(save.getId() != 0) {
			return save.getId();
		}
		
		return null;
	}

	@Override
	@Transactional
	public MasterRegionDTO getRegionById(Long id) {
		// TODO Auto-generated method stub
		
		MasterRegion regionEntityData =
				masterRegionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Region not found for id:"+id));
		
		MasterRegionDTO masterRegionDTO = new MasterRegionDTO();
		masterRegionDTO.setName(regionEntityData.getName());
		masterRegionDTO.setIsDeleted(regionEntityData.getIsDeleted());
		
		return masterRegionDTO;
	}

	@Override
	@Transactional
	public MasterRegionDTO editRegion(Long id, @Valid MasterRegionDTO regionDTO) {
		// TODO Auto-generated method stub
		
		MasterRegion regionEntityData =
				masterRegionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Region not found for id:"+id));
		
		regionEntityData.setName(regionDTO.getName());
		regionEntityData.setIsDeleted(regionDTO.getIsDeleted());
		MasterRegion save = masterRegionRepository.save(regionEntityData);
		
		MasterRegionDTO masterRegionDTO = new MasterRegionDTO();
		masterRegionDTO.setName(save.getName());
		masterRegionDTO.setIsDeleted(save.getIsDeleted());
		
		return masterRegionDTO;
	}

	@Override
	public ResponseEntity<String> deleteRegion(Long id) {
		// TODO Auto-generated method stub
		
		MasterRegion regionEntityData =
				masterRegionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Region not found for id:"+id));
		
		masterRegionRepository.delete(regionEntityData);
		return ResponseEntity.ok("Region with id " + id + " deleted successfully");
	}
	
	

}
