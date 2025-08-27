package com.choosenfly.hotelbookingsystem.masters.service.cityMapping.iwtx;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.dto.ApiCityMappingDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.ApiCityMapping;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;
import com.choosenfly.hotelbookingsystem.masters.repository.IwtxCityMappingRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterPlaceRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterStateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IwtxCityMappingService implements IwtxCityMappingServiceInterface {

	private final IwtxCityMappingRepository iwtxCityMappingRepository;

	private final MasterCountryRepository masterCountryRepository;

	private final MasterStateRepository masterStateRepository;

	public IwtxCityMappingService(IwtxCityMappingRepository iwtxCityMappingRepository,
			MasterCountryRepository masterCountryRepository, MasterStateRepository masterStateRepository) {
		this.iwtxCityMappingRepository = iwtxCityMappingRepository;
		this.masterCountryRepository = masterCountryRepository;
		this.masterStateRepository = masterStateRepository;
	}

	@Override
	@Transactional
	public Long saveIwtxCityMapping(ApiCityMappingDTO dto) {
		// TODO Auto-generated method stub
		
		System.err.println("dto::: " + dto);
		
		//if platform = Iwtx using countrycode , cityname and citycode fetch hotelcodes from iwt_hotels tables store json array of hotel_codes in mapping table

		MasterCountry countryEntity = masterCountryRepository.findById(dto.getMasterCountryId()).orElseThrow(
				() -> new EntityNotFoundException("Country not found for id : " + dto.getMasterCountryId()));
		String countryCode = countryEntity.getCountryCode();
		
	   MasterState stateEntity = masterStateRepository.findById(dto.getMasterCityId()).orElseThrow(
				() -> new EntityNotFoundException("State not found for id: " + dto.getMasterCityId()));
		String cityCode = stateEntity.getStateCode();
		String cityName = stateEntity.getName();
		
		System.err.println("countryCode::: " + countryCode);
		System.err.println("cityCode::: " + cityCode);
		System.err.println("cityName::: " + cityName);
		
		List<String> hotelCodes = iwtxCityMappingRepository.fetchIwtxHotelCodes(countryCode, cityCode, cityName);
		
		System.err.println("hotelCodes::: " + hotelCodes);
		ObjectMapper objectMapper = new ObjectMapper();
		// Convert to JSON string
		String hotelCodesJson = null;
		try {
			hotelCodesJson = objectMapper.writeValueAsString(hotelCodes); 
			System.err.println("hotelCodesJson::: " + hotelCodesJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
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
		entity.setApiHotelCodeList(hotelCodesJson);  // ✅ store JSON array
		entity.setIsDeleted(false);
		ApiCityMapping save = iwtxCityMappingRepository.save(entity);
		if (save.getId() != 0) {
			return save.getId(); 
		}

		return null;
	}

	@Override
	@Transactional
	public ApiCityMappingDTO getIwtxCityMappingById(Long id) {
		// TODO Auto-generated method stub
		ApiCityMapping iwtxCityaMappingEntity = iwtxCityMappingRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());

		if (iwtxCityaMappingEntity.getId() != 0) {

			ApiCityMappingDTO dto = new ApiCityMappingDTO();
			
			dto.setId(iwtxCityaMappingEntity.getId());
			dto.setMasterCountryId(iwtxCityaMappingEntity.getMasterCountry().getId());
			dto.setMasterCityId(iwtxCityaMappingEntity.getMasterState().getId());
			dto.setApiProvider(iwtxCityaMappingEntity.getApiProvider());
			dto.setApiCountryId(iwtxCityaMappingEntity.getApiCountryId());
			dto.setApiCountryCode(iwtxCityaMappingEntity.getMasterCountry().getCountryCode());
			dto.setApiCityId(iwtxCityaMappingEntity.getApiCityId());
			dto.setApiCityCode(iwtxCityaMappingEntity.getMasterState().getStateCode());
			dto.setIsDeleted(iwtxCityaMappingEntity.getIsDeleted());

			return dto;

		}
		return null;
	}

	@Override
	@Transactional
	public ApiCityMappingDTO editIwtxCityMapping(Long id, ApiCityMappingDTO placeDTO) {
		// TODO Auto-generated method stub
		ApiCityMapping iwtxCityaMappingEntity = iwtxCityMappingRepository.findById(id) 
				.orElseThrow(() -> new EntityNotFoundException());

		MasterCountry countryEntity = masterCountryRepository.findById(placeDTO.getMasterCountryId()).orElseThrow(
				() -> new EntityNotFoundException("Country not found for id : " + placeDTO.getMasterCountryId()));
		iwtxCityaMappingEntity.setMasterCountry(countryEntity);

	 MasterState stateEntity = masterStateRepository.findById(placeDTO.getMasterCityId()).orElseThrow(
				() -> new EntityNotFoundException("State not found for id: " + placeDTO.getMasterCityId()));
		iwtxCityaMappingEntity.setMasterState(stateEntity);

		iwtxCityaMappingEntity.setApiProvider(iwtxCityaMappingEntity.getApiProvider());
		iwtxCityaMappingEntity.setApiCountryId(iwtxCityaMappingEntity.getApiCountryId());
		iwtxCityaMappingEntity.setApiCityId(iwtxCityaMappingEntity.getApiCityId());
		iwtxCityaMappingEntity.setIsDeleted(iwtxCityaMappingEntity.getIsDeleted());
		ApiCityMapping updated = iwtxCityMappingRepository.save(iwtxCityaMappingEntity);

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
	public ResponseEntity<String> deleteIwtxCityMapping(Long id) {
		// TODO Auto-generated method stub
		ApiCityMapping iwtxCityMappingData = iwtxCityMappingRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());

		iwtxCityMappingRepository.delete(iwtxCityMappingData);

		return ResponseEntity.ok("IwtxCityMapping with id " + id + " deleted successfully");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ApiCityMappingDTO> getAllIwtxCityMappingList(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		Page<ApiCityMapping> iwtxCityMappingPage;

		if (StringUtils.hasText(search)) {
			iwtxCityMappingPage = iwtxCityMappingRepository.findByApiProviderContainingIgnoreCase(search, pageable);
		} else {
			iwtxCityMappingPage = iwtxCityMappingRepository.findAll(pageable);
		}

		return iwtxCityMappingPage.map(citymapping -> {
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
