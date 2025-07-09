package com.choosenfly.hotelbookingsystem.service.masters.ContactType;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterContactTypeDTO;

import jakarta.validation.Valid;

public interface ContactTypeServiceInterface {

	Long saveContactType( MasterContactTypeDTO masterContactTypeDTO);

	MasterContactTypeDTO getContactTypeById(Long id);

	MasterContactTypeDTO editContactType(Long id, MasterContactTypeDTO masterContactTypeDTO);

	ResponseEntity<String> deleteContactType(Long id);

}
