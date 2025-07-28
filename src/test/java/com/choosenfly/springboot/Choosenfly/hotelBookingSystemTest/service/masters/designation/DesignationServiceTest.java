package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.designation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterDesignationDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.entities.master.MasterDesignation;
import com.choosenfly.hotelbookingsystem.repository.master.MasterDesignationRepository;
import com.choosenfly.hotelbookingsystem.service.masters.designation.DesignationService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class DesignationServiceTest {
	
	@Mock
	MasterDesignationRepository masterDesignationRepository;
	
	@InjectMocks
	DesignationService designationService;
	
	private MasterDesignation entity;
	private MasterDesignationDTO desigDTO;
	
	@BeforeEach
	void setUp() {
		entity = new MasterDesignation();
		entity.setDesignationId(1L);
		entity.setName("Test Designation");
		entity.setIsDeleted(false);
		
		desigDTO = new MasterDesignationDTO();
		desigDTO.setName("Test Designation");
		desigDTO.setIsDeleted(false);
	}
	
	@Test
	void testDesignationSave() {
		when(masterDesignationRepository.save(any(MasterDesignation.class))).thenReturn(entity);
		
		Long saveId = designationService.save(desigDTO);
		
		assertNotNull(saveId);
		assertEquals(1L, saveId);
		verify(masterDesignationRepository , times(1)).save(any(MasterDesignation.class));
		
	}
	
	@Test
	void testGetDesignationDetailsById_Success() {
		 when(masterDesignationRepository.findById(1L)).thenReturn(Optional.of(entity));
		 
		 MasterDesignationDTO result = designationService.getDesigDetailsById(1L);
		 
		 assertNotNull(result);
		 assertEquals("Test Designation", result.getName());
		 verify(masterDesignationRepository, times(1)).findById(1L);
	}
	
	@Test
	void testGetDesignationDetailsById_Failure() {
		when(masterDesignationRepository.findById(1L)).thenReturn(Optional.empty());
		
		 assertThrows(EntityNotFoundException.class, () -> designationService.getDesigDetailsById(1L));
		 
	}
	
	@Test
	void testDesignationEdit_Success() {
		when(masterDesignationRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(masterDesignationRepository.save(any(MasterDesignation.class))).thenReturn(entity);

		MasterDesignationDTO updatedDesignation = new MasterDesignationDTO();
		updatedDesignation.setName("Test Designation");

		MasterDesignationDTO result = designationService.editDesignation(1L, updatedDesignation);

		assertNotNull(result);
		assertEquals("Test Designation", result.getName());
		verify(masterDesignationRepository, times(1)).findById(1L);
		verify(masterDesignationRepository, times(1)).save(any(MasterDesignation.class));

	}
	
	 @Test
	    void testEditDesignation_NotFound() {
	        when(masterDesignationRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> designationService.editDesignation(1L, desigDTO));
	    }

	    @Test
	    void testDeleteDesignation_Success() {
	        when(masterDesignationRepository.findById(1L)).thenReturn(Optional.of(entity));

	        ResponseEntity<String> response = designationService.deleteDesignation(1L);

	        assertEquals("Designation with id 1 deleted successfully", response.getBody());
	        verify(masterDesignationRepository, times(1)).delete(entity);
	    }

	    @Test
	    void testDeleteDesignation_NotFound() {
	        when(masterDesignationRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> designationService.deleteDesignation(1L));
	    }
	
	

}
