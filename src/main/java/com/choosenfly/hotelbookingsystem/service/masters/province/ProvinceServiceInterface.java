package com.choosenfly.hotelbookingsystem.service.masters.province;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterStateDTO;

import jakarta.validation.Valid;

public interface ProvinceServiceInterface {

	Long saveProvince(MasterStateDTO stateDTO);

	MasterStateDTO getProvinveById(Long id);

	MasterStateDTO editProvince(Long id, MasterStateDTO stateDTO);

	ResponseEntity<String> deleteProvince(Long id);

	Page<MasterStateDTO> getAllProvince(Pageable pageable, String search);

}
