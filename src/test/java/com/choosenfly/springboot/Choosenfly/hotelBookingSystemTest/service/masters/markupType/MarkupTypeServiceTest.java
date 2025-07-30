package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.markupType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterMarkupTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarkupType;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMarkupTypeRepository;
import com.choosenfly.hotelbookingsystem.service.masters.markupType.MarkupTypeService;



@ExtendWith(MockitoExtension.class)
public class MarkupTypeServiceTest {
	
    @Mock
    private MasterMarkupTypeRepository masterMarkupTypeRepository;

    @InjectMocks
    private MarkupTypeService mastermaMarkupTypeService; 

    private MasterMarkupType markupEntity;
    private MasterMarkupTypeDTO markupDTO;

    @BeforeEach
    void setUp() {
    	markupEntity = new MasterMarkupType();
    	markupEntity.setId(1L);
    	markupEntity.setName("markup test");
    	markupEntity.setMarkup("10");
    	markupEntity.setIsType("testistype");
    	markupEntity.setMarkupType(MasterMarkupType.Markup.valueOf("PERCENT"));
    	markupEntity.setIsDeleted(false);

    	markupDTO = new MasterMarkupTypeDTO();
    	markupDTO.setName("markup test");
    	markupDTO.setMarkup("10");
    	markupDTO.setIsType("testistype");
    	markupDTO.setMarkupType(MasterMarkupType.Markup.valueOf("PERCENT"));
    	markupDTO.setIsDeleted(false);
    }

    @Test
    void testSaveMasterMarkupType() {
        when(masterMarkupTypeRepository.save(any(MasterMarkupType.class))).thenReturn(markupEntity);

        Long savedId = mastermaMarkupTypeService.saveMarkupType(markupDTO);

        assertNotNull(savedId);
        assertEquals(1L, savedId);
        verify(masterMarkupTypeRepository, times(1)).save(any(MasterMarkupType.class));
    }
    
    @Test
    void testSaveMasterMarkupType_NullOrEmptyDTO() {
        // Null case
        assertThrows(NullPointerException.class, () -> mastermaMarkupTypeService.saveMarkupType(null));
        verify(masterMarkupTypeRepository, never()).save(any());

       
    }

    @Test
    void testGetMasterMarkupTypeDetailsById_Success() {
        when(masterMarkupTypeRepository.findById(1L)).thenReturn(Optional.of(markupEntity));

        MasterMarkupTypeDTO result = mastermaMarkupTypeService.getMarkupTypeById(1L);

        assertNotNull(result);
        assertEquals("markup test", result.getName());
        assertEquals("10", result.getMarkup());
        assertEquals("testistype", result.getIsType());
        assertEquals(MasterMarkupType.Markup.valueOf("PERCENT"), result.getMarkupType());
        verify(masterMarkupTypeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetMasterMarkupTypeDetailsById_NotFound() {
        when(masterMarkupTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mastermaMarkupTypeService.getMarkupTypeById(1L));
    }

    @Test
    void testEditMasterMarkupType_Success() {
        when(masterMarkupTypeRepository.findById(1L)).thenReturn(Optional.of(markupEntity));
        when(masterMarkupTypeRepository.save(any(MasterMarkupType.class))).thenReturn(markupEntity);

        MasterMarkupTypeDTO updateMarkup = new MasterMarkupTypeDTO();
        updateMarkup.setName("markup test");
        updateMarkup.setMarkup("10");
        updateMarkup.setIsType("testistype");
        updateMarkup.setMarkupType(MasterMarkupType.Markup.valueOf("PERCENT"));
        updateMarkup.setIsDeleted(false);

        MasterMarkupTypeDTO result = mastermaMarkupTypeService.editMarkupType(1L, updateMarkup);

        assertNotNull(result);
        assertEquals("markup test", result.getName());
        assertEquals("10", result.getMarkup());
        assertEquals("testistype", result.getIsType());
        assertEquals(MasterMarkupType.Markup.valueOf("PERCENT"), result.getMarkupType());
        verify(masterMarkupTypeRepository, times(1)).findById(1L);
        verify(masterMarkupTypeRepository, times(1)).save(any(MasterMarkupType.class));
    }

    @Test
    void testEditMasterMarkupType_NotFound() {
        when(masterMarkupTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mastermaMarkupTypeService.editMarkupType(1L, markupDTO));
    }

    @Test
    void testDeleteMasterMarkupType_Success() {
        when(masterMarkupTypeRepository.findById(1L)).thenReturn(Optional.of(markupEntity));

        ResponseEntity<String> response = mastermaMarkupTypeService.deleteMarkupType(1L);

        assertEquals("Markup Type with id 1 deleted successfully", response.getBody());
        verify(masterMarkupTypeRepository, times(1)).delete(markupEntity);
    }

    @Test
    void testDeleteMasterMarkupType_NotFound() {
        when(masterMarkupTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mastermaMarkupTypeService.deleteMarkupType(1L));
    }

}
