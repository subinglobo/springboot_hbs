package com.choosenfly.hotelbookingsystem.service.masters.visaInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterDayActivitiesDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterVisaInformationDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterDayActivities;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;
import com.choosenfly.hotelbookingsystem.entities.master.MasterVisaInformation;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterDayActivitiesRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterStateRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterVisaInformationRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class VisaInformationService implements VisaInformationServiceInterface{
	
	private final MasterVisaInformationRepository informationRepository;

	private final MasterCountryRepository masterCountryRepository;

	public VisaInformationService(MasterVisaInformationRepository informationRepository,
			MasterCountryRepository masterCountryRepository,MasterStateRepository masterStateRepository) {
		this.informationRepository = informationRepository;
		this.masterCountryRepository = masterCountryRepository;
	}

	@Override
	@Transactional
	public Long saveVisaInformation(@Valid MasterVisaInformationDTO visaDTO) {
		// TODO Auto-generated method stub
		

		MasterVisaInformation entity = new MasterVisaInformation();

			MasterCountry countryEntity = 
					masterCountryRepository.findById(visaDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +visaDTO.getCountryId() ));
			entity.setCountry(countryEntity);
			MasterCountry passCountryEntity = 
					masterCountryRepository.findById(visaDTO.getPaxPassportCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +visaDTO.getCountryId() ));
			entity.setPassportCountry(passCountryEntity);
			entity.setPassportCode(visaDTO.getPassportCode());
			entity.setVisaDescription(visaDTO.getVisaDescription());
			MasterVisaInformation save = informationRepository.save(entity);
			Long visaId = save.getVisaId();
			if (visaId != 0) {
				return visaId;
			}
			return null;
	}

	@Override
	@Transactional
	public MasterVisaInformationDTO getVisaInformationById(Long id) {
		// TODO Auto-generated method stub
		MasterVisaInformation visaData = 
				informationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("visa information not found for id :" + id));

		if (visaData.getVisaId()!= null) {
			
			MasterVisaInformationDTO dto = new MasterVisaInformationDTO();
			dto.setVisaId(visaData.getVisaId());
			dto.setCountryId(visaData.getCountry().getId());
			dto.setPaxPassportCountryId(visaData.getPassportCountry().getId());
			dto.setPassportCode(visaData.getPassportCode());
			dto.setVisaDescription(visaData.getVisaDescription());
			
			return dto;
		}
		return null;
	}

	@Override
	@Transactional
	public MasterVisaInformationDTO editVisaInfo(Long id, @Valid MasterVisaInformationDTO visaDTO) {
		// TODO Auto-generated method stub
		
		MasterVisaInformation visaData = 
				informationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("visa information not found for id :" + id));

		MasterCountry countryEntity = 
				masterCountryRepository.findById(visaDTO.getCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +visaDTO.getCountryId() ));
		visaData.setCountry(countryEntity);
		MasterCountry passCountryEntity = 
				masterCountryRepository.findById(visaDTO.getPaxPassportCountryId()).orElseThrow(() -> new EntityNotFoundException("Country not found for id : " +visaDTO.getCountryId() ));
		visaData.setPassportCountry(passCountryEntity);
		visaData.setPassportCode(visaDTO.getPassportCode());
		visaData.setVisaDescription(visaDTO.getVisaDescription());
		
		MasterVisaInformation save = informationRepository.save(visaData);
		MasterVisaInformationDTO dto = new MasterVisaInformationDTO();
		dto.setVisaId(save.getVisaId());
		dto.setCountryId(save.getCountry().getId());
		dto.setPassportCode(save.getPassportCode());
		dto.setPaxPassportCountryId(save.getPassportCountry().getId());
		dto.setVisaDescription(save.getVisaDescription());

		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteVisaInfo(Long id) {
		// TODO Auto-generated method stub
		MasterVisaInformation visaData = 
				informationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("visa information not found for id :" + id));

		informationRepository.delete(visaData);

		return ResponseEntity.ok("\"visa information with id " + id + " deleted successfully");
	}

	@Override
	@Transactional
	public Page<MasterVisaInformationDTO> getAllVisaInfo(Pageable pageable, String search) {
		// TODO Auto-generated method stub
	    Page<MasterVisaInformation> visaInfoPage;

	    if (StringUtils.hasText(search)) {
	    	visaInfoPage = informationRepository.findByPassportCodeContainingIgnoreCase(search, pageable);
	    } else {
	    	visaInfoPage = informationRepository.findAll(pageable);
	    }

	    return visaInfoPage.map(dayActivity -> {
			MasterVisaInformationDTO dto = new MasterVisaInformationDTO();
			dto.setVisaId(dayActivity.getVisaId());
			dto.setCountryId(dayActivity.getCountry().getId());
			dto.setPassportCode(dayActivity.getPassportCode());
			dto.setPaxPassportCountryId(dayActivity.getPassportCountry().getId());
			dto.setVisaDescription(dayActivity.getVisaDescription());

	        return dto;
	    });
	}

}
