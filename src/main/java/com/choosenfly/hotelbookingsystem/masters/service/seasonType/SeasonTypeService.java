package com.choosenfly.hotelbookingsystem.masters.service.seasonType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterSeasonTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterSeasonType;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterSeasonTypeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SeasonTypeService implements SeasonTypeServiceInterface{
	
	private final MasterSeasonTypeRepository masterSeasonTypeRepository;
	
	public SeasonTypeService(MasterSeasonTypeRepository masterSeasonTypeRepository) {
		this.masterSeasonTypeRepository = masterSeasonTypeRepository;
	}

	@Override
	@Transactional
	public Long saveSeasonType(MasterSeasonTypeDTO dto) {
		// TODO Auto-generated method stub
		
		MasterSeasonType entity = new MasterSeasonType();
		entity.setName(dto.getSeason());
		entity.setIsDeleted(false);
		MasterSeasonType save = masterSeasonTypeRepository.save(entity);
		if(save.getSeasonTypeId() != 0) {
			return save.getSeasonTypeId();
		}
		return null;
	}

	@Override
	@Transactional
	public MasterSeasonTypeDTO getSeasonTypeById(Long id) {
		// TODO Auto-generated method stub
		
		MasterSeasonType seasonEntity = 
				masterSeasonTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Season Type not found for id :"+id));
		
		MasterSeasonTypeDTO dto = new MasterSeasonTypeDTO();
		dto.setSeasonTypeId(seasonEntity.getSeasonTypeId());
		dto.setSeason(seasonEntity.getName());
		dto.setIsDeleted(seasonEntity.getIsDeleted());
		return dto;
	}

	@Override
	@Transactional
	public MasterSeasonTypeDTO editSeasonType(Long id, MasterSeasonTypeDTO seasonDTO) {
		// TODO Auto-generated method stub
		
		MasterSeasonType seasonEntity = 
				masterSeasonTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Season Type not found for id :"+id));
		seasonEntity.setName(seasonDTO.getSeason());
		seasonEntity.setIsDeleted(false);
		MasterSeasonType save = masterSeasonTypeRepository.save(seasonEntity);
		
		MasterSeasonTypeDTO dto = new MasterSeasonTypeDTO();
		dto.setSeasonTypeId(save.getSeasonTypeId());
		dto.setSeason(save.getName());
		dto.setIsDeleted(save.getIsDeleted());
		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteSeasonType(Long id) {
		// TODO Auto-generated method stub
		
		MasterSeasonType seasonEntity = 
				masterSeasonTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Season Type not found for id :"+id));
		
		masterSeasonTypeRepository.delete(seasonEntity);
		
		return ResponseEntity.ok("Season with " + id + " deleted successfully");
	}

	@Override
	@Transactional
	public Page<MasterSeasonTypeDTO> getAllSeasonTypes(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		 Page<MasterSeasonType> seasonPage;

		    if (StringUtils.hasText(search)) {
		    	seasonPage = masterSeasonTypeRepository.findByNameContainingIgnoreCase(search, pageable);
		    } else {
		    	seasonPage = masterSeasonTypeRepository.findAll(pageable);
		    }

		    return seasonPage.map(season -> {
		    	MasterSeasonTypeDTO dto = new MasterSeasonTypeDTO();
		    	dto.setSeasonTypeId(season.getSeasonTypeId());
		    	dto.setSeason(season.getName());
		    	dto.setIsDeleted(season.getIsDeleted());
		        return dto;
		    });
	}

}
