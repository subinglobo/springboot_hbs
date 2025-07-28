package com.choosenfly.hotelbookingsystem.service.masters.hoteltype;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterHotelTypeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HotelTypeService implements HotelTypeServiceInterface{
	
	private final MasterHotelTypeRepository masterHotelTypeRepository;
	
	public HotelTypeService(MasterHotelTypeRepository masterHotelTypeRepository) {
		this.masterHotelTypeRepository = masterHotelTypeRepository;
	}

	@Override
	public Long saveHotelType(MasterHotelTypeDTO dto) {
		// TODO Auto-generated method stub
		
		MasterHotelType entity = new MasterHotelType();
		entity.setName(dto.getName());
		entity.setIsDeleted(false);
		MasterHotelType save = masterHotelTypeRepository.save(entity);
		if(save.getHotelTypeId() != 0) {
			return save.getHotelTypeId();
		}
		return null;
	}

	@Override
	public MasterHotelTypeDTO getHotelTypeById(Long id) {
		// TODO Auto-generated method stub
		
		MasterHotelType hotelTypeEntity = 
				masterHotelTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel Type not found for id:" + id));
		
		MasterHotelTypeDTO dto = new MasterHotelTypeDTO();
		dto.setHotelTypeId(hotelTypeEntity.getHotelTypeId());
		dto.setName(hotelTypeEntity.getName());
		dto.setIsDeleted(hotelTypeEntity.getIsDeleted());
		return dto;
	}

	@Override
	public MasterHotelTypeDTO editHotelType(Long id, MasterHotelTypeDTO hotelTypeDTO) {
		// TODO Auto-generated method stub
		
		MasterHotelType hotelTypeEntity = 
				masterHotelTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel Type not found for id:" + id));
		
		hotelTypeEntity.setName(hotelTypeDTO.getName());
		hotelTypeEntity.setIsDeleted(false);
		MasterHotelType save = masterHotelTypeRepository.save(hotelTypeEntity);
		
		
		
		MasterHotelTypeDTO dto = new MasterHotelTypeDTO();
		dto.setHotelTypeId(save.getHotelTypeId());
		dto.setName(save.getName());
		dto.setIsDeleted(save.getIsDeleted());
		return dto;
	}

	@Override
	public ResponseEntity<String> deleteHotelType(Long id) {
		// TODO Auto-generated method stub
		
		MasterHotelType hotelTypeEntity = 
				masterHotelTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel Type not found for id:" + id));
		
		masterHotelTypeRepository.delete(hotelTypeEntity);
		return ResponseEntity.ok("Hotel Type with id " + id + " deleted successfully");
	}

	@Override
	public Page<MasterHotelTypeDTO> getAllHotelType(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		 Page<MasterHotelType> hotelTypePage;

		    if (StringUtils.hasText(search)) {
		    	hotelTypePage = masterHotelTypeRepository.findByNameContainingIgnoreCase(search, pageable);
		    } else {
		    	hotelTypePage = masterHotelTypeRepository.findAll(pageable);
		    }

		    return hotelTypePage.map(hotelType -> {
		    	MasterHotelTypeDTO masterHotelTypeDTO = new MasterHotelTypeDTO();
		    	masterHotelTypeDTO.setHotelTypeId(hotelType.getHotelTypeId());
		    	masterHotelTypeDTO.setName(hotelType.getName());
		    	masterHotelTypeDTO.setIsDeleted(hotelType.getIsDeleted());

		        return masterHotelTypeDTO;
		    });
	}

}
