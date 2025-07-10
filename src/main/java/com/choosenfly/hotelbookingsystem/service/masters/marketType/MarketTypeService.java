package com.choosenfly.hotelbookingsystem.service.masters.marketType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterMarketTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarketType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMarketTypeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarketTypeService implements MarketTypeServiceInterface {
	
	@Autowired
	private MasterMarketTypeRepository masterMarketTypeRepository;

	@Override
	@Transactional
	public Long saveMarketType(MasterMarketTypeDTO marketDTO) {
		// TODO Auto-generated method stub
		
		MasterMarketType entity = new MasterMarketType();
		entity.setName(marketDTO.getName());
		entity.setIsDeleted(false);
		MasterMarketType save = masterMarketTypeRepository.save(entity);
		if(save.getMarketTypeId() != 0) {
			return save.getMarketTypeId();
		}
		
		return null;
	}

	@Override
	@Transactional
	public MasterMarketTypeDTO getMarketTypeById(Long id) {
		// TODO Auto-generated method stub
		
		MasterMarketType marketData = 
				masterMarketTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + id));
		
		MasterMarketTypeDTO masterMarketTypeDTO = new MasterMarketTypeDTO();
		masterMarketTypeDTO.setName(marketData.getName());
		masterMarketTypeDTO.setIsDeleted(marketData.getIsDeleted());
		return masterMarketTypeDTO;
	}

	@Override
	@Transactional
	public MasterMarketTypeDTO editMarketType(Long id, MasterMarketTypeDTO marketDTO) {
		// TODO Auto-generated method stub
		MasterMarketType marketData = 
				masterMarketTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + id));
		
		marketData.setName(marketDTO.getName());
		marketData.setIsDeleted(marketDTO.getIsDeleted());
		MasterMarketType save = masterMarketTypeRepository.save(marketData);
		
		MasterMarketTypeDTO masterMarketTypeDTO = new MasterMarketTypeDTO();
		masterMarketTypeDTO.setName(save.getName());
		masterMarketTypeDTO.setIsDeleted(save.getIsDeleted());
		
		return masterMarketTypeDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteMarketType(Long id) {
		// TODO Auto-generated method stub
		MasterMarketType marketData = 
				masterMarketTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Market Type not found for id :" + id));
		
		masterMarketTypeRepository.delete(marketData);
		return ResponseEntity.ok("Market Type  with id " + id + " deleted successfully");
	}

}
