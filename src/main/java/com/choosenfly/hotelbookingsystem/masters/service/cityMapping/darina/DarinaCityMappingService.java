package com.choosenfly.hotelbookingsystem.masters.service.cityMapping.darina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.masters.controller.MasterCountryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.DarinaCityMappingRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.DarinaCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;

@Service
public class DarinaCityMappingService implements DarinaCityMappingServiceInterface {
	
	private DarinaCityMappingRepository darinaCityMappingRepository;
	
	private MasterCountryRepository masterCountryRepository;
	
	private MasterStateRepository masterStateRepository;
	
	private DarinaCountryRepository darinaCountryRepository;
	
	@Autowired
	public DarinaCityMappingService(DarinaCityMappingRepository darinaCityMappingRepository , DarinaCountryRepository darinaCountryRepository) {
		this.darinaCityMappingRepository = darinaCityMappingRepository;
		this.masterCountryRepository = masterCountryRepository;
		this.masterStateRepository = masterStateRepository;
		this.darinaCountryRepository = darinaCountryRepository;
	}

	@Override
	@Transactional
	public Boolean saveDarinaCityMapping(ApiCityMappingDTO dto) {
		// TODO Auto-generated method stub
		
		MasterCountry countryEntity = masterCountryRepository.findById(dto.getMasterCountryId()).orElseThrow(
				() -> new EntityNotFoundException("Country not found for id : " + dto.getMasterCountryId()));
		
	   MasterState stateEntity = masterStateRepository.findById(dto.getMasterCityId()).orElseThrow(
				() -> new EntityNotFoundException("State not found for id: " + dto.getMasterCityId()));
		
		
		
		return null;
	}

	@Override
	@Transactional
	public Page<MasterCountryDTO> getAllDarinaCountry(Pageable pageable, String search) {
		// TODO Auto-generated method stub
//		  Page<MasterCountry> countryPage;
//
//		    if (StringUtils.hasText(search)) {
//		    	countryPage = darinaCountryRepository.findByNameContainingIgnoreCase(search, pageable);
//		    } else {
//		    	countryPage = darinaCountryRepository.findAll(pageable);
//		    }
//
//		    return countryPage.map(country -> {
//		       MasterCountryDTO dto = new MasterCountryDTO();
//		      dto.setId(country.getId());
//		      dto.setName(country.getName());
//		      dto.setCountryCode(country.getCountryCode());
//		       dto.setIsDeleted(country.getIsDeleted());
//		       dto.setMarketTypeId(country.getMarketType().getMarketTypeId());
//		       dto.setRegionId(country.getRegion().getId());
//		       dto.setRegion(country.getRegion().getName());
//		       dto.setMarketType(country.getMarketType().getName());
//
//		        return dto;
//		    });
		
		return null;
	}

}
