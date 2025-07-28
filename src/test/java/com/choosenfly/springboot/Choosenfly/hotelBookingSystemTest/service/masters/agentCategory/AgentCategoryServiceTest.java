package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.agentCategory;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterAgentCategory;
import com.choosenfly.hotelbookingsystem.repository.master.MasterAgentCategoryRepository;
import com.choosenfly.hotelbookingsystem.service.masters.agentCategory.AgentCategoryService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AgentCategoryServiceTest {
	
	@Mock
	private MasterAgentCategoryRepository agentCategoryRepository;
	
	@InjectMocks
	private AgentCategoryService agentCategoryService;
	
	private MasterAgentCategory agentCategoryEntity;
	 
	private MasterAgentCategoryDTO masterAgentCategoryDTO;

	    @BeforeEach
	    void setUp() {
	    	agentCategoryEntity = new MasterAgentCategory();
	    	agentCategoryEntity.setCategoryId(1L);
	    	agentCategoryEntity.setName("Test AgentCategory");
	    	agentCategoryEntity.setIsDeleted(false);

	    	masterAgentCategoryDTO = new MasterAgentCategoryDTO();
	    	masterAgentCategoryDTO.setName("Test AgentCategory");
	    	masterAgentCategoryDTO.setIsDeleted(false);
	    }

	    @Test
	    void testSaveMasterAgentCategory() {
	        when(agentCategoryRepository.save(any(MasterAgentCategory.class))).thenReturn(agentCategoryEntity);

	        Long savedId = agentCategoryService.saveMasterAgentCategory(masterAgentCategoryDTO);

	        assertNotNull(savedId);
	        assertEquals(1L, savedId);
	        verify(agentCategoryRepository, times(1)).save(any(MasterAgentCategory .class));
	    }

	    @Test
	    void testGetAgentCategoryById_Success() {
	        when(agentCategoryRepository.findById(1L)).thenReturn(Optional.of(agentCategoryEntity));

	        MasterAgentCategoryDTO  result = agentCategoryService.getAgentCategoryDetailsById(1L);

	        assertNotNull(result);
	        assertEquals("Test AgentCategory", result.getName());
	        verify(agentCategoryRepository, times(1)).findById(1L);
	    }

	    @Test
	    void testAgentCategoryById_NotFound() {
	        when(agentCategoryRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> agentCategoryService.getAgentCategoryDetailsById(1L));
	    }

	    @Test
	    void testEditAgentCategory_Success() {
	        when(agentCategoryRepository.findById(1L)).thenReturn(Optional.of(agentCategoryEntity));
	        when(agentCategoryRepository.save(any(MasterAgentCategory.class))).thenReturn(agentCategoryEntity);

	        MasterAgentCategoryDTO updatedCategoryDTO = new MasterAgentCategoryDTO();
	        updatedCategoryDTO.setName("Test Bank");

	        MasterAgentCategoryDTO result = agentCategoryService.editAgentCategory(1L, updatedCategoryDTO);

	        assertNotNull(result);
	        assertEquals("Test Bank", result.getName());
	        verify(agentCategoryRepository, times(1)).findById(1L);
	        verify(agentCategoryRepository, times(1)).save(any(MasterAgentCategory.class));
	    }

	    @Test
	    void testEditagentCategory_NotFound() {
	        when(agentCategoryRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> agentCategoryService.editAgentCategory(1L, masterAgentCategoryDTO));
	    }

	    @Test
	    void testDeleteAgentCategory_Success() {
	        when(agentCategoryRepository.findById(1L)).thenReturn(Optional.of(agentCategoryEntity));

	        ResponseEntity<String> response = agentCategoryService.deleteAgentCategory(1L);

	        assertEquals("Agent category with id 1 deleted successfully", response.getBody());
	        verify(agentCategoryRepository, times(1)).delete(agentCategoryEntity);
	    }

	    @Test
	    void testDeleteAgentCategory_NotFound() {
	        when(agentCategoryRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> agentCategoryService.deleteAgentCategory(1L));
	    }
	
	
}
