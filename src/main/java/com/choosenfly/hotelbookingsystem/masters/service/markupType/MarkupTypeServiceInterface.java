package com.choosenfly.hotelbookingsystem.masters.service.markupType;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterMarkupTypeDTO;

public interface MarkupTypeServiceInterface {

	Long saveMarkupType(MasterMarkupTypeDTO markupDTO);

	MasterMarkupTypeDTO getMarkupTypeById(Long id);

	MasterMarkupTypeDTO editMarkupType(Long id, MasterMarkupTypeDTO markupDTO);

	ResponseEntity<String> deleteMarkupType(Long id);

}
