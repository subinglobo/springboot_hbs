package com.choosenfly.hotelbookingsystem.service.masters.hotelAmenity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelAmenityDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelAmenities;
import com.choosenfly.hotelbookingsystem.repository.master.MasterHotelAmenitiesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HotelAmenityService implements HotelAmenityServiceImpl {
	
	private MasterHotelAmenitiesRepository masterHotelAmenitiesRepository;
	
	public HotelAmenityService(MasterHotelAmenitiesRepository masterHotelAmenitiesRepository) {
		this.masterHotelAmenitiesRepository = masterHotelAmenitiesRepository;
	}

	@Override
	@Transactional
	public Long saveHotelAmenity(MasterHotelAmenityDTO dto) {
		// TODO Auto-generated method stub
		
		MasterHotelAmenities entity = new MasterHotelAmenities();
		entity.setName(dto.getAmenityName());
		entity.setIsDeleted(false);
		MasterHotelAmenities save = masterHotelAmenitiesRepository.save(entity);
		if(save.getAmenitiesId() != 0) {
			return save.getAmenitiesId();
		}
		
		return null;
	}

	@Override
	@Transactional
	public MasterHotelAmenityDTO getHotelAmenityById(Long id) {
		// TODO Auto-generated method stub
		
		MasterHotelAmenities hotelAmenityEntity = 
				masterHotelAmenitiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel Amentiy not found for id:" +id));
		
		MasterHotelAmenityDTO dto = new MasterHotelAmenityDTO();
		dto.setAmenitiesId(hotelAmenityEntity.getAmenitiesId());
		dto.setAmenityName(hotelAmenityEntity.getName());
		dto.setIsDeleted(hotelAmenityEntity.getIsDeleted());
		
		return dto;
	}

	@Override
	@Transactional
	public MasterHotelAmenityDTO editHotelAmenity(Long id, MasterHotelAmenityDTO amenityDTO) {
		// TODO Auto-generated method stub
		
		MasterHotelAmenities hotelAmenityEntity = 
				masterHotelAmenitiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel Amentiy not found for id:" +id));
		
		hotelAmenityEntity.setName(amenityDTO.getAmenityName());
		hotelAmenityEntity.setIsDeleted(false);
		MasterHotelAmenities save = masterHotelAmenitiesRepository.save(hotelAmenityEntity);
		
		MasterHotelAmenityDTO dto = new MasterHotelAmenityDTO();
		dto.setAmenitiesId(save.getAmenitiesId());
		dto.setAmenityName(save.getName());
		dto.setIsDeleted(save.getIsDeleted());
		return dto;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteHotelAmenity(Long id) {
		// TODO Auto-generated method stub
		MasterHotelAmenities hotelAmenityEntity = 
				masterHotelAmenitiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hotel Amentiy not found for id:" +id));
		
		masterHotelAmenitiesRepository.delete(hotelAmenityEntity);
		
		return ResponseEntity.ok("Hotel Amenity with id " + id + " deleted successfully");
	}

	@Override
	@Transactional
	public Page<MasterHotelAmenityDTO> getAllHotelAmenities(Pageable pageable, String search) {
		// TODO Auto-generated method stub
		
		Page<MasterHotelAmenities> hotelAmenityPage;

		if (StringUtils.hasText(search)) {
			hotelAmenityPage = masterHotelAmenitiesRepository.findByNameContainingIgnoreCase(search, pageable);
		} else {
			hotelAmenityPage = masterHotelAmenitiesRepository.findAll(pageable);
		}

		return hotelAmenityPage.map(hotelAmenity -> {
			MasterHotelAmenityDTO dto = new MasterHotelAmenityDTO();
			dto.setAmenitiesId(hotelAmenity.getAmenitiesId());
			dto.setAmenityName(hotelAmenity.getName());
			dto.setIsDeleted(hotelAmenity.getIsDeleted());
			return dto;
		});
	}
	
	

}
