package com.choosenfly.hotelbookingsystem.masters.service.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.exceptions.DataNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.InvalidFeildException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterCurrencyDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterBank;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCurrency;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCurrencyRepository;



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

	@Override
	public Page<MasterCurrencyDTO> getAllCurrencyList(Pageable pageable, String searchTerm) {
		// TODO Auto-generated method stub
		Page<MasterCurrency> currencyPage;

	    if (StringUtils.hasText(searchTerm)) {
	    	
	    	currencyPage = masterCurrencyRepository.findByNameStartingWithIgnoreCase(searchTerm, pageable);
	    } else {
	    	currencyPage = masterCurrencyRepository.findAll(pageable);
	    }

	    return currencyPage.map(currency -> {
	    MasterCurrencyDTO dto = new	MasterCurrencyDTO();
	    dto.setCurrencyId(currency.getCurrencyId());
	    dto.setName(currency.getName());
	    dto.setCurrencyCode(currency.getCurrencyCode());
	    dto.setValue(currency.getValue());
	    dto.setIsDeleted(currency.getIsDeleted());
	    return dto;
	    });
	}

}
