package com.choosenfly.hotelbookingsystem.masters.service.hotelcategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterHotelCategoryDTO;

public interface HotelCategoryServiceInterface {

	Long saveHotelCategory(MasterHotelCategoryDTO dto);

	MasterHotelCategoryDTO getHotelCategoryById(Long id);

	MasterHotelCategoryDTO editHotelCategory(Long id, MasterHotelCategoryDTO hotelcatDTO);

	ResponseEntity<String> deleteHotelCategory(Long id);

	Page<MasterHotelCategoryDTO> getAllHotelCategory(Pageable pageable, String search);

}
