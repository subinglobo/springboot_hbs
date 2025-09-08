package com.choosenfly.hotelbookingsystem.masters.service.cityMapping.x3;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.api.x3.repository.X3HotelsRepository;
import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.ApiCityMapping;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.IwtxCityMappingRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class X3CityMappingService implements X3CityMappingServiceInterface {

	private final X3HotelsRepository x3HotelsRepository;
	private final IwtxCityMappingRepository iwtxCityMappingRepository;
	private final MasterCountryRepository masterCountryRepository;
	private final MasterStateRepository masterStateRepository;

	public X3CityMappingService(X3HotelsRepository x3HotelsRepository,
			IwtxCityMappingRepository iwtxCityMappingRepository,
			MasterCountryRepository masterCountryRepository, 
			MasterStateRepository masterStateRepository) {
		this.x3HotelsRepository = x3HotelsRepository;
		this.iwtxCityMappingRepository = iwtxCityMappingRepository;
		this.masterCountryRepository = masterCountryRepository;
		this.masterStateRepository = masterStateRepository;
	}

	@Override
	@Transactional
	public Long saveX3CityMapping(ApiCityMappingDTO dto) {
		System.err.println("X3 dto::: " + dto);
		
		// Fetch country and state entities
		MasterCountry countryEntity = masterCountryRepository.findById(dto.getMasterCountryId()).orElseThrow(
				() -> new EntityNotFoundException("Country not found for id : " + dto.getMasterCountryId()));
		String countryCode = countryEntity.getCountryCode();
		
		MasterState stateEntity = masterStateRepository.findById(dto.getMasterCityId()).orElseThrow(
				() -> new EntityNotFoundException("State not found for id: " + dto.getMasterCityId()));
		String cityCode = stateEntity.getStateCode();
		String cityName = stateEntity.getName();
		
		System.err.println("City Code :: "+cityCode);
		System.err.println("City cityName :: "+cityName);
		System.err.println("countryCode countryCode :: "+countryCode);
		
		// Fetch hotel codes from X3Hotels table using the new repository method
		List<String> hotelCodes = x3HotelsRepository.fetchX3HotelCodes(countryCode, cityCode, cityName);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String hotelCodesJson = null;
		try {
			hotelCodesJson = objectMapper.writeValueAsString(hotelCodes); 
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// Save into mapping entity
		ApiCityMapping entity = new ApiCityMapping();
		entity.setApiProvider(dto.getApiProvider());
		entity.setMasterCountry(countryEntity);
		entity.setMasterState(stateEntity);
		entity.setApiCountryId(countryEntity.getId()+"");
		entity.setApiCountryCode(countryCode);
		entity.setApiCityId(stateEntity.getId()+"");
		entity.setApiCityCode(cityCode);
		entity.setApiHotelCodeList(hotelCodesJson);  // store JSON array of X3 hotel codes
		entity.setIsDeleted(false);
		ApiCityMapping save = iwtxCityMappingRepository.save(entity);
		if (save.getId() != 0) {
			return save.getId(); 
		}

		return null;
	}

	@Override
	@Transactional
	public ApiCityMappingDTO getX3CityMappingById(Long id) {
		ApiCityMapping x3CityMappingEntity = iwtxCityMappingRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());

		if (x3CityMappingEntity.getId() != 0) {
			ApiCityMappingDTO dto = new ApiCityMappingDTO();
			
			dto.setId(x3CityMappingEntity.getId());
			dto.setMasterCountryId(x3CityMappingEntity.getMasterCountry().getId());
			dto.setMasterCityId(x3CityMappingEntity.getMasterState().getId());
			dto.setApiProvider(x3CityMappingEntity.getApiProvider());
			dto.setApiCountryId(x3CityMappingEntity.getApiCountryId());
			dto.setApiCountryCode(x3CityMappingEntity.getMasterCountry().getCountryCode());
			dto.setApiCityId(x3CityMappingEntity.getApiCityId());
			dto.setApiCityCode(x3CityMappingEntity.getMasterState().getStateCode());
			dto.setIsDeleted(x3CityMappingEntity.getIsDeleted());

			return dto;
		}
		return null;
	}

	@Override
	@Transactional
	public ApiCityMappingDTO editX3CityMapping(Long id, ApiCityMappingDTO placeDTO) {
		ApiCityMapping x3CityMappingEntity = iwtxCityMappingRepository.findById(id) 
				.orElseThrow(() -> new EntityNotFoundException());

		MasterCountry countryEntity = masterCountryRepository.findById(placeDTO.getMasterCountryId()).orElseThrow(
				() -> new EntityNotFoundException("Country not found for id : " + placeDTO.getMasterCountryId()));
		x3CityMappingEntity.setMasterCountry(countryEntity);

		MasterState stateEntity = masterStateRepository.findById(placeDTO.getMasterCityId()).orElseThrow(
				() -> new EntityNotFoundException("State not found for id: " + placeDTO.getMasterCityId()));
		x3CityMappingEntity.setMasterState(stateEntity);

		x3CityMappingEntity.setApiProvider(x3CityMappingEntity.getApiProvider());
		x3CityMappingEntity.setApiCountryId(x3CityMappingEntity.getApiCountryId());
		x3CityMappingEntity.setApiCityId(x3CityMappingEntity.getApiCityId());
		x3CityMappingEntity.setIsDeleted(x3CityMappingEntity.getIsDeleted());
		ApiCityMapping updated = iwtxCityMappingRepository.save(x3CityMappingEntity);

		ApiCityMappingDTO dto = new ApiCityMappingDTO();
		dto.setMasterCountryId(updated.getMasterCountry().getId());
		dto.setMasterCityId(updated.getMasterState().getId());
		dto.setApiProvider(updated.getApiProvider());
		dto.setApiCountryId(updated.getApiCountryId());
		dto.setApiCityId(updated.getApiCityId());
		dto.setIsDeleted(updated.getIsDeleted());
		dto.setApiCityCode(updated.getMasterState().getStateCode());
		dto.setApiCountryCode(updated.getMasterCountry().getCountryCode());
		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteX3CityMapping(Long id) {
		ApiCityMapping x3CityMappingData = iwtxCityMappingRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());

		iwtxCityMappingRepository.delete(x3CityMappingData);

		return ResponseEntity.ok("X3CityMapping with id " + id + " deleted successfully");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ApiCityMappingDTO> getAllX3CityMappingList(Pageable pageable, String search) {
		Page<ApiCityMapping> x3CityMappingPage;

		if (StringUtils.hasText(search)) {
			x3CityMappingPage = iwtxCityMappingRepository.findByApiProviderContainingIgnoreCase(search, pageable);
		} else {
			x3CityMappingPage = iwtxCityMappingRepository.findAll(pageable);
		}

		return x3CityMappingPage.map(citymapping -> {
			ApiCityMappingDTO dto = new ApiCityMappingDTO();
			dto.setMasterCountryId(citymapping.getMasterCountry().getId());
			dto.setMasterCityId(citymapping.getMasterState().getId());
			dto.setApiProvider(citymapping.getApiProvider());
			dto.setApiCountryId(citymapping.getApiCountryId());
			dto.setApiCityId(citymapping.getApiCityId());
			dto.setIsDeleted(citymapping.getIsDeleted());
			return dto;
		});
	}
}
