package com.choosenfly.hotelbookingsystem.masters.service.ContactType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterContactTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterBank;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterContactType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterContactTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ContactTypeService implements ContactTypeServiceInterface {
	
	@Autowired
	private MasterContactTypeRepository masterContactTypeRepository;

	@Override
	@Transactional
	public Long saveContactType(MasterContactTypeDTO masterContactTypeDTO) {
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

	@Override
	public Page<MasterContactTypeDTO> getAllContactTypes(Pageable pageable, String searchTerm) {
		// TODO Auto-generated method stub
		Page<MasterContactType> contactTypePage;

		if (StringUtils.hasText(searchTerm)) {

			contactTypePage = masterContactTypeRepository.findByNameStartingWithIgnoreCase(searchTerm, pageable);
		} else {
			contactTypePage = masterContactTypeRepository.findAll(pageable);
		}

		return contactTypePage.map(contactType -> {
			MasterContactTypeDTO dto = new MasterContactTypeDTO();
			dto.setContacttypeId(contactType.getContacttypeId());
			dto.setName(contactType.getName());
			dto.setIsDeleted(contactType.getIsDeleted());
			return dto;
		});
	}


}
