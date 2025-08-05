package com.choosenfly.hotelbookingsystem.masters.service.province;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterStateDTO;

import jakarta.validation.Valid;

public interface ProvinceServiceInterface {

	Long saveProvince(MasterStateDTO stateDTO);

	MasterStateDTO getProvinveById(Long id);

	MasterStateDTO editProvince(Long id, MasterStateDTO stateDTO);

	ResponseEntity<String> deleteProvince(Long id);

	Page<MasterStateDTO> getAllProvince(Pageable pageable, String search);

}
