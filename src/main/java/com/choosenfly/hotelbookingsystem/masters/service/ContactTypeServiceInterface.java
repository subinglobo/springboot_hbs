package com.choosenfly.hotelbookingsystem.masters.service;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterContactTypeDTO;

public interface ContactTypeServiceInterface {

	Long saveContactType( MasterContactTypeDTO masterContactTypeDTO);

	MasterContactTypeDTO getContactTypeById(Long id);

	MasterContactTypeDTO editContactType(Long id, MasterContactTypeDTO masterContactTypeDTO);

	ResponseEntity<String> deleteContactType(Long id);

}
