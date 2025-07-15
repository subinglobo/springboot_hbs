package com.choosenfly.hotelbookingsystem.service.masters.province;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterStateDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;
import com.choosenfly.hotelbookingsystem.repository.master.MasterStateRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ProvinceService implements ProvinceServiceInterface {
	
	private final MasterStateRepository masterStateRepository;
	
	public ProvinceService(MasterStateRepository masterStateRepository) {
		this.masterStateRepository = masterStateRepository;
	}

	@Override
	@Transactional
	public Long saveProvince(@Valid MasterStateDTO stateDTO) {
		// TODO Auto-generated method stub
		
		MasterState entity = new MasterState();
		
		MasterCountry masterCountry = new MasterCountry();
		masterCountry.setName(stateDTO.getCountry());
		entity.setCountry(masterCountry);
		entity.setName(stateDTO.getName());
		entity.setStateCode(stateDTO.getStateCode());
		entity.setIsDeleted(false);
		MasterState save = masterStateRepository.save(entity);
		Long stateId = save.getId();
		if(stateId != 0) {
			return stateId;
		}
		return null;
	}

	@Override
	public MasterStateDTO getProvinveById(Long id) {
		// TODO Auto-generated method stub

		MasterState provinceData = masterStateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Province not found for id :" + id));

		if (provinceData.getId() != null) {

			MasterStateDTO masterStateDTO = new MasterStateDTO();
			masterStateDTO.setName(provinceData.getName());
			masterStateDTO.setStateCode(provinceData.getStateCode());
			
			MasterCountry masterCountry = new MasterCountry();
			masterCountry.setName(null);
			masterStateDTO.setCountry(provinceData.getCountry());
			masterStateDTO.setIsDeleted(provinceData.getIsDeleted());
			return masterStateDTO;
		}
		return null;
	}

	@Override
	public MasterStateDTO editProvince(Long id, @Valid MasterStateDTO stateDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteProvince(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
