package com.choosenfly.hotelbookingsystem.masters.service.cityMapping.iwtx;

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

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class IwtxCityMappingService implements IwtxCityMappingServiceInterface {

	private final IwtxCityMappingRepository iwtxCityMappingRepository;

	private final MasterCountryRepository masterCountryRepository;

	private final MasterPlaceRepository masterPlaceRepository;

	public IwtxCityMappingService(IwtxCityMappingRepository iwtxCityMappingRepository,
			MasterCountryRepository masterCountryRepository, MasterPlaceRepository masterPlaceRepository) {
		this.iwtxCityMappingRepository = iwtxCityMappingRepository;
		this.masterCountryRepository = masterCountryRepository;
		this.masterPlaceRepository = masterPlaceRepository;
	}

	@Override
	@Transactional
	public Long saveIwtxCityMapping(@Valid ApiCityMappingDTO dto) {
		// TODO Auto-generated method stub

		ApiCityMapping entity = new ApiCityMapping();

		MasterCountry countryEntity = masterCountryRepository.findById(dto.getMasterCountryId()).orElseThrow(
				() -> new EntityNotFoundException("Country not found for id : " + dto.getMasterCountryId()));
		entity.setMasterCountry(countryEntity);

		MasterPlace placeEntity = masterPlaceRepository.findById(dto.getMasterCityId()).orElseThrow(
				() -> new EntityNotFoundException("Place or City not found for id: " + dto.getMasterCityId()));
		entity.setMasterCity(placeEntity);

		entity.setApiProvider(dto.getApiProvider());
		entity.setApiCountryId(dto.getApiCountryId());
		entity.setApiCityId(dto.getApiCityId());
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
			dto.setMasterCityId(iwtxCityaMappingEntity.getMasterCity().getId());
			dto.setApiProvider(iwtxCityaMappingEntity.getApiProvider());
			dto.setApiCountryId(iwtxCityaMappingEntity.getApiCountryId());
			dto.setApiCountryCode(iwtxCityaMappingEntity.getMasterCountry().getCountryCode());
			dto.setApiCityId(iwtxCityaMappingEntity.getApiCityId());
			dto.setApiCityCode(iwtxCityaMappingEntity.getMasterCity().getPlaceCode());
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

		MasterPlace placeEntity = masterPlaceRepository.findById(placeDTO.getMasterCityId()).orElseThrow(
				() -> new EntityNotFoundException("Place or City not found for id: " + placeDTO.getMasterCityId()));
		iwtxCityaMappingEntity.setMasterCity(placeEntity);

		iwtxCityaMappingEntity.setApiProvider(iwtxCityaMappingEntity.getApiProvider());
		iwtxCityaMappingEntity.setApiCountryId(iwtxCityaMappingEntity.getApiCountryId());
		iwtxCityaMappingEntity.setApiCityId(iwtxCityaMappingEntity.getApiCityId());
		iwtxCityaMappingEntity.setIsDeleted(iwtxCityaMappingEntity.getIsDeleted());
		ApiCityMapping updated = iwtxCityMappingRepository.save(iwtxCityaMappingEntity);

		ApiCityMappingDTO dto = new ApiCityMappingDTO();

		dto.setMasterCountryId(updated.getMasterCountry().getId());
		dto.setMasterCityId(updated.getMasterCity().getId());
		dto.setApiProvider(updated.getApiProvider());
		dto.setApiCountryId(updated.getApiCountryId());
		dto.setApiCityId(updated.getApiCityId());
		dto.setIsDeleted(updated.getIsDeleted());
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
			dto.setMasterCityId(citymapping.getMasterCity().getId());
			dto.setApiProvider(citymapping.getApiProvider());
			dto.setApiCountryId(citymapping.getApiCountryId());
			dto.setApiCityId(citymapping.getApiCityId());
			dto.setIsDeleted(citymapping.getIsDeleted());
			return dto;
		});
	}

}
