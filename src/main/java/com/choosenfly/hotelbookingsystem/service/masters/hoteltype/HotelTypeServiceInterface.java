package com.choosenfly.hotelbookingsystem.service.masters.hoteltype;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelTypeDTO;

public interface HotelTypeServiceInterface {

	Long saveHotelType(MasterHotelTypeDTO dto);

	MasterHotelTypeDTO getHotelTypeById(Long id);

	MasterHotelTypeDTO editHotelType(Long id, MasterHotelTypeDTO hotelTypeDTO);

	ResponseEntity<String> deleteHotelType(Long id);

	Page<MasterHotelTypeDTO> getAllHotelType(Pageable pageable, String search);



}
