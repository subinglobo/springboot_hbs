package com.choosenfly.hotelbookingsystem.service.masters.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.controller.masters.MasterCountryDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarketType;
import com.choosenfly.hotelbookingsystem.entities.master.MasterRegion;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCountryRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class CountryService implements CountryServiceInterface{
	
	@Autowired
	private  MasterCountryRepository masterCountryRepository;

	@Override
	public Long saveCountry(@Valid MasterCountryDTO countryDTO) {
		// TODO Auto-generated method stub
		
		MasterCountry entity = new MasterCountry();
		
		MasterMarketType market = new MasterMarketType();
		market.setMarketTypeId(countryDTO.getMarketTypeId());
		entity.setMarketType(market);
		
		MasterRegion region = new MasterRegion();
		region.setId(countryDTO.getRegionId());
		entity.setRegion(region);
		
		entity.setName(countryDTO.getName());
		entity.setCountryCode(countryDTO.getCountryCode());
		entity.setIsDeleted(false);
		
		MasterCountry save = masterCountryRepository.save(entity);
		if(save.getId() != 0) {
			return save.getId();
		}
		return null;
	}

	@Override
	public MasterCountryDTO getCountryById(Long id) {
		// TODO Auto-generated method stub
		
		MasterCountry countryEntity =
				masterCountryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country noy found for id :"+id));
		
		MasterCountryDTO masterCountryDTO = new MasterCountryDTO();
		masterCountryDTO.setMarketTypeId(countryEntity.getMarketType().getMarketTypeId());
		masterCountryDTO.setId(countryEntity.getId());
		masterCountryDTO.setRegionId(countryEntity.getRegion().getId());
		masterCountryDTO.setName(countryEntity.getName());
		masterCountryDTO.setCountryCode(countryEntity.getCountryCode());
		masterCountryDTO.setIsDeleted(countryEntity.getIsDeleted());
		
		return masterCountryDTO;
	}

	@Override
	public MasterCountryDTO editCountry(Long id, @Valid MasterCountryDTO countryDTO) {
		// TODO Auto-generated method stub
		
		MasterCountry countryEntity =
				masterCountryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country noy found for id :"+id));
		
		MasterMarketType market = new MasterMarketType();
		market.setMarketTypeId(countryDTO.getMarketTypeId());
		countryEntity.setMarketType(market);
		
		MasterRegion region = new MasterRegion();
		region.setId(countryDTO.getRegionId());
		countryEntity.setRegion(region);
		
		countryEntity.setName(countryDTO.getName());
		countryEntity.setCountryCode(countryDTO.getCountryCode());
		countryEntity.setIsDeleted(false);
		MasterCountry save = masterCountryRepository.save(countryEntity);
		
		MasterCountryDTO masterCountryDTO = new MasterCountryDTO();
		masterCountryDTO.setMarketTypeId(save.getMarketType().getMarketTypeId());
		masterCountryDTO.setId(save.getId());
		masterCountryDTO.setRegionId(save.getRegion().getId());
		masterCountryDTO.setName(save.getName());
		masterCountryDTO.setCountryCode(save.getCountryCode());
		masterCountryDTO.setIsDeleted(save.getIsDeleted());
		
		return masterCountryDTO;
	}

	@Override
	public ResponseEntity<String> deleteCountry(Long id) {
		// TODO Auto-generated method stub
		
		MasterCountry countryEntity =
				masterCountryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country noy found for id :"+id));
		
		masterCountryRepository.delete(countryEntity);

		return ResponseEntity.ok("Country with id " + id + " deleted successfully");
	}

}
