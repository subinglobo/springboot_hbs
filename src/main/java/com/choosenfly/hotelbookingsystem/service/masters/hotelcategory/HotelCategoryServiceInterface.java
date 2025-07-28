package com.choosenfly.hotelbookingsystem.service.masters.hotelcategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterHotelCategoryDTO;

public interface HotelCategoryServiceInterface {

	Long saveHotelCategory(MasterHotelCategoryDTO dto);

	MasterHotelCategoryDTO getHotelCategoryById(Long id);

	MasterHotelCategoryDTO editHotelCategory(Long id, MasterHotelCategoryDTO hotelcatDTO);

	ResponseEntity<String> deleteHotelCategory(Long id);

	Page<MasterHotelCategoryDTO> getAllHotelCategory(Pageable pageable, String search);

}
