package com.choosenfly.hotelbookingsystem.service.masters.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterCurrencyDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCurrency;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidFeildException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCurrencyRepository;



@Service
public class CurrencyService implements CurrencyServiceInterface {
	
	@Autowired
	private MasterCurrencyRepository masterCurrencyRepository;

	@Override
	@Transactional
	public Long saveCurrency(MasterCurrencyDTO currecyDTO) {
		// TODO Auto-generated method stub
		
		if (currecyDTO == null) {
	        throw new MissingRequestBodyException("Request Body cannot be null"); 
	        
	    }
		if (currecyDTO.getCurrencyId() != null) {
	        throw new InvalidFeildException("Currency ID should not be provided when creating a new currency");
	    }
		
		MasterCurrency entity = new MasterCurrency();
		if (currecyDTO.getCurrencyId() == null) {
			entity.setName(currecyDTO.getName());
			entity.setCurrencyCode(currecyDTO.getCurrencyCode());
			entity.setValue(currecyDTO.getValue());
			entity.setIsDeleted(false);
			MasterCurrency save = masterCurrencyRepository.save(entity);
			if (save.getCurrencyId() != 0) {
				return save.getCurrencyId();
			}
		}
		return null;
	}

	@Override
	@Transactional
	public MasterCurrencyDTO getCurrecnyById(Long id) {
		// TODO Auto-generated method stub
		
		MasterCurrency entityData = 
				masterCurrencyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Currency not found for id :"+id));
		
		MasterCurrencyDTO masterCurrencyDTO = new MasterCurrencyDTO();
		masterCurrencyDTO.setName(entityData.getName());
		masterCurrencyDTO.setCurrencyCode(entityData.getCurrencyCode());
		masterCurrencyDTO.setValue(entityData.getValue());
		masterCurrencyDTO.setIsDeleted(entityData.getIsDeleted());
		masterCurrencyDTO.setCurrencyId(entityData.getCurrencyId());
		
		return masterCurrencyDTO;
	}

	@Override
	@Transactional
	public MasterCurrencyDTO editCurrency(Long id, MasterCurrencyDTO currecyDTO) {
		// TODO Auto-generated method stub
		MasterCurrency entityData = 
				masterCurrencyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Currency not found for id :"+id));
		
		entityData.setName(currecyDTO.getName());
		entityData.setCurrencyCode(currecyDTO.getCurrencyCode());
		entityData.setValue(currecyDTO.getValue());
		entityData.setIsDeleted(currecyDTO.getIsDeleted());
		masterCurrencyRepository.save(entityData);
		
		MasterCurrencyDTO masterCurrencyDTO = new MasterCurrencyDTO();
		masterCurrencyDTO.setName(entityData.getName());
		masterCurrencyDTO.setCurrencyCode(entityData.getCurrencyCode());
		masterCurrencyDTO.setValue(entityData.getValue());
		masterCurrencyDTO.setIsDeleted(entityData.getIsDeleted());
		
		return masterCurrencyDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteCurrency(Long id) {
		// TODO Auto-generated method stub
		
		MasterCurrency entityData = 
				masterCurrencyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Currency not found for id :"+id));
		
		masterCurrencyRepository.delete(entityData);
		
		
		return ResponseEntity.ok("Currency with id " + id + " deleted successfully");
	}

}
