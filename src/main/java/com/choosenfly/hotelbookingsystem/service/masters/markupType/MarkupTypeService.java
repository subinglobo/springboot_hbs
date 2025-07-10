package com.choosenfly.hotelbookingsystem.service.masters.markupType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterMarkupTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarkupType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMarkupTypeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarkupTypeService implements MarkupTypeServiceInterface{

	private final MasterMarkupTypeRepository masterMarkupTypeRepository;
	
	@Autowired
	public MarkupTypeService(MasterMarkupTypeRepository masterMarkupTypeRepository) {
		this.masterMarkupTypeRepository = masterMarkupTypeRepository;
	}

	@Override
	@Transactional
	public Long saveMarkupType(MasterMarkupTypeDTO markupDTO) {
		// TODO Auto-generated method stub
		
		MasterMarkupType entity = new MasterMarkupType();
		entity.setName(markupDTO.getName());
		entity.setMarkup(markupDTO.getMarkup());
		entity.setMarkupType(markupDTO.getMarkupType());
		entity.setIsType(markupDTO.getIsType());
		entity.setIsDeleted(false);
		
		
		MasterMarkupType save = masterMarkupTypeRepository.save(entity); 
		if(save.getId() != 0) {
			return save.getId();
		}
		return null;
	}

	@Override
	@Transactional
	public MasterMarkupTypeDTO getMarkupTypeById(Long id) {
		// TODO Auto-generated method stub
		
		MasterMarkupType entityData = 
				masterMarkupTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Markup Type not found for id :" + id));
		
		MasterMarkupTypeDTO masterMarkupTypeDTO = new MasterMarkupTypeDTO();
		masterMarkupTypeDTO.setName(entityData.getName());
		masterMarkupTypeDTO.setMarkup(entityData.getMarkup());
		masterMarkupTypeDTO.setMarkupType(entityData.getMarkupType());
		masterMarkupTypeDTO.setIsType(entityData.getIsType());
		masterMarkupTypeDTO.setIsDeleted(entityData.getIsDeleted());
		masterMarkupTypeDTO.setId(id);
		return masterMarkupTypeDTO;
	}

	@Override
	@Transactional
	public MasterMarkupTypeDTO editMarkupType(Long id, MasterMarkupTypeDTO markupDTO) {
		// TODO Auto-generated method stub
		
		MasterMarkupType entityDatas = 
				masterMarkupTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Markup Type not found for id :" + id));
		
		entityDatas.setName(markupDTO.getName());
		entityDatas.setMarkup(markupDTO.getMarkup());
		entityDatas.setMarkupType(markupDTO.getMarkupType());
		entityDatas.setIsType(markupDTO.getIsType());
		entityDatas.setIsDeleted(false);
		MasterMarkupType updatedData = masterMarkupTypeRepository.save(entityDatas);
		
		MasterMarkupTypeDTO masterMarkupTypeDTO = new MasterMarkupTypeDTO();
		masterMarkupTypeDTO.setId(id);
		masterMarkupTypeDTO.setName(updatedData.getName());
		masterMarkupTypeDTO.setMarkup(updatedData.getMarkup());
		masterMarkupTypeDTO.setMarkupType(updatedData.getMarkupType());
		masterMarkupTypeDTO.setIsType(updatedData.getIsType());
		masterMarkupTypeDTO.setIsDeleted(updatedData.getIsDeleted());
		
		return masterMarkupTypeDTO;
	}

	@Override
	public ResponseEntity<String> deleteMarkupType(Long id) {
		// TODO Auto-generated method stub
		
		MasterMarkupType entityDatas = 
				masterMarkupTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Markup Type not found for id :" + id));
		
		masterMarkupTypeRepository.delete(entityDatas);
		
		return ResponseEntity.ok("Markup Type with id " + id + " deleted successfully");
	}
}
