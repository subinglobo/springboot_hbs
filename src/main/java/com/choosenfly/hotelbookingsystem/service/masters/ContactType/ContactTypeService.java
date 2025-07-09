package com.choosenfly.hotelbookingsystem.service.masters.ContactType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterContactTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterContactType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterContactTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ContactTypeService implements ContactTypeServiceInterface {
	
	@Autowired
	private MasterContactTypeRepository masterContactTypeRepository;

	@Override
	@Transactional
	public Long saveContactType(@Valid MasterContactTypeDTO masterContactTypeDTO) {
		// TODO Auto-generated method stub
		
		MasterContactType entity = new MasterContactType();
		entity.setName(masterContactTypeDTO.getName());
		entity.setIsDeleted(false);
		
		MasterContactType save = masterContactTypeRepository.save(entity);
		if(save.getContacttypeId() != null) {
			return save.getContacttypeId();
		}
		return null;
	}

	@Override
	@Transactional
	public MasterContactTypeDTO getContactTypeById(Long id) {
		// TODO Auto-generated method stub
		
		MasterContactType contactType = 
				masterContactTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contct Type not found with id :"+id));
		
		MasterContactTypeDTO masterContactTypeDTO = new MasterContactTypeDTO();
		masterContactTypeDTO.setContacttypeId(id);
		masterContactTypeDTO.setName(contactType.getName());
		masterContactTypeDTO.setIsDeleted(contactType.getIsDeleted());
		
		
		return masterContactTypeDTO;
	}

	@Override
	@Transactional
	public MasterContactTypeDTO editContactType(Long id , MasterContactTypeDTO masterContactTypeDTO) {
		// TODO Auto-generated method stub
		MasterContactType updateEntity = 
				masterContactTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contact Type not found with id :"+id));
		
		updateEntity.setName(masterContactTypeDTO.getName());
		MasterContactType save = masterContactTypeRepository.save(updateEntity);
		
		MasterContactTypeDTO dto = new MasterContactTypeDTO();
		dto.setContacttypeId(save.getContacttypeId());
		dto.setIsDeleted(save.getIsDeleted());
		dto.setName(save.getName());
		
		return dto;
	}

	@Override
	public ResponseEntity<String> deleteContactType(Long id) {
		// TODO Auto-generated method stub
		
		MasterContactType contactType = 
				masterContactTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contct Type not found with id :"+id));
		
		masterContactTypeRepository.delete(contactType);
		
		return ResponseEntity.ok("Contact Type with id " + id + " deleted successfully");
	}


}
