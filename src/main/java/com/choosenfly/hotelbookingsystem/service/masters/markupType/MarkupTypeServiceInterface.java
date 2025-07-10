package com.choosenfly.hotelbookingsystem.service.masters.markupType;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterMarkupTypeDTO;

public interface MarkupTypeServiceInterface {

	Long saveMarkupType(MasterMarkupTypeDTO markupDTO);

	MasterMarkupTypeDTO getMarkupTypeById(Long id);

	MasterMarkupTypeDTO editMarkupType(Long id, MasterMarkupTypeDTO markupDTO);

	ResponseEntity<String> deleteMarkupType(Long id);

}
