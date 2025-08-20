package com.choosenfly.hotelbookingsystem.masters.service.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.controller.MasterCountryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMarketType;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterRegion;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;

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

	@Transactional(readOnly = true)
	@Override
	public Page<MasterCountryDTO> getAllCountries(Pageable pageable, String search) {

	    Page<MasterCountry> countryPage;

	    if (StringUtils.hasText(search)) {
	    	countryPage = masterCountryRepository.findByNameContainingIgnoreCase(search, pageable);
	    } else {
	    	countryPage = masterCountryRepository.findAll(pageable);
	    }

	    return countryPage.map(country -> {
	       MasterCountryDTO dto = new MasterCountryDTO();
	      dto.setId(country.getId());
	      dto.setName(country.getName());
	      dto.setCountryCode(country.getCountryCode());
	       dto.setIsDeleted(country.getIsDeleted());
	       dto.setMarketTypeId(country.getMarketType().getMarketTypeId());
	       dto.setRegionId(country.getRegion().getId());
	       dto.setRegion(country.getRegion().getName());
	       dto.setMarketType(country.getMarketType().getName());

	        return dto;
	    });
	}


}
