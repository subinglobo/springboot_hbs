package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.PackageSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterTermsAndConditionDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterState;
import com.choosenfly.hotelbookingsystem.entities.master.MasterTermsAndCondition;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterStateRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterTermsAndConditionRepository;
import com.choosenfly.hotelbookingsystem.service.masters.termsAndCondition.TermsAndConditionSerivice;



@ExtendWith(MockitoExtension.class)
class TermsAndConditionServiceTest {

    @Mock
    private MasterTermsAndConditionRepository termsAndConditionRepository;

    @Mock
    private MasterCountryRepository masterCountryRepository;

    @Mock
    private MasterStateRepository masterStateRepository;

    @InjectMocks
    private TermsAndConditionSerivice termsAndConditionService;

    private MasterTermsAndConditionDTO termsDTO;
    private MasterTermsAndCondition termsEntity;
    private MasterCountry country;
    private MasterState state;

    @BeforeEach
    void setUp() {
        country = new MasterCountry();
        country.setId(1L);
        country.setName("TestCountry");
        
        state = new MasterState();
        state.setId(1L);
        state.setName("TestState");
        
        termsDTO = new MasterTermsAndConditionDTO();
        termsDTO.setCountryId(1L);
        termsDTO.setStateId(1L);
        termsDTO.setDescription("Test Description");
        termsDTO.setDescriptionType(1);
        termsDTO.setTermsCode("TEST_CODE");
        
        termsEntity = new MasterTermsAndCondition();
        termsEntity.setTermsAndConditionsId(1L);
        termsEntity.setCountry(country);
        termsEntity.setState(state);
        termsEntity.setTagline("TestCountry-TestState");
        termsEntity.setDescription("Test Description");
        termsEntity.setDescriptionType(1);
        termsEntity.setTermsCode("TEST_CODE");
    }

    @Test
    void saveTermsAndCondition_Success() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(masterStateRepository.findById(1L)).thenReturn(Optional.of(state));
        when(termsAndConditionRepository.save(any(MasterTermsAndCondition.class))).thenReturn(termsEntity);
        
        Long result = termsAndConditionService.saveTermsAndCondition(termsDTO);
        
        assertNotNull(result);
        assertEquals(1L, result);
        verify(termsAndConditionRepository, times(1)).save(any(MasterTermsAndCondition.class));
    }

    @Test
    void saveTermsAndCondition_CountryNotFound() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            termsAndConditionService.saveTermsAndCondition(termsDTO);
        });
    }

    @Test
    void saveTermsAndCondition_StateNotFound() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(masterStateRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            termsAndConditionService.saveTermsAndCondition(termsDTO);
        });
    }

    @Test
    void getTermsAndConditionById_Success() {
        when(termsAndConditionRepository.findById(1L)).thenReturn(Optional.of(termsEntity));
        
        MasterTermsAndConditionDTO result = termsAndConditionService.getTermsAndConditionById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getTermsAndConditionsId());
        assertEquals(1L, result.getCountryId());
        assertEquals(1L, result.getStateId());
        assertEquals("Test Description", result.getDescription());
        assertEquals(1, result.getDescriptionType());
        assertEquals("TestCountry-TestState", result.getTagline());
        assertEquals("TEST_CODE", result.getTermsCode());
    }

    @Test
    void getTermsAndConditionById_NotFound() {
        when(termsAndConditionRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            termsAndConditionService.getTermsAndConditionById(1L);
        });
    }

    @Test
    void editTermsAndCondition_Success() {
        when(termsAndConditionRepository.findById(1L)).thenReturn(Optional.of(termsEntity));
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(masterStateRepository.findById(1L)).thenReturn(Optional.of(state));
        when(termsAndConditionRepository.save(any(MasterTermsAndCondition.class))).thenReturn(termsEntity);
        
        MasterTermsAndConditionDTO updatedDTO = new MasterTermsAndConditionDTO();
        updatedDTO.setCountryId(1L);
        updatedDTO.setStateId(1L);
        updatedDTO.setDescription("Updated Description");
        updatedDTO.setDescriptionType(2);
        updatedDTO.setTermsCode("UPDATED_CODE");
        
        MasterTermsAndConditionDTO result = termsAndConditionService.editTermsAndCondition(1L, updatedDTO);
        
        assertNotNull(result);
        assertEquals(1L, result.getTermsAndConditionsId());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(2, result.getDescriptionType());
        assertEquals("UPDATED_CODE", result.getTermsCode());
    }

    @Test
    void editTermsAndCondition_TermsNotFound() {
        when(termsAndConditionRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            termsAndConditionService.editTermsAndCondition(1L, termsDTO);
        });
    }

    @Test
    void deleteTermsAndCondition_Success() {
        when(termsAndConditionRepository.findById(1L)).thenReturn(Optional.of(termsEntity));
        doNothing().when(termsAndConditionRepository).delete(termsEntity);
        
        ResponseEntity<String> response = termsAndConditionService.deleteTermsAndCondition(1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("deleted successfully"));
        verify(termsAndConditionRepository, times(1)).delete(termsEntity);
    }

    @Test
    void deleteTermsAndCondition_NotFound() {
        when(termsAndConditionRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            termsAndConditionService.deleteTermsAndCondition(1L);
        });
    }

    @Test
    void getAllTermsAndCondition_WithSearch() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<MasterTermsAndCondition> page = new PageImpl<>(List.of(termsEntity));
        
        when(termsAndConditionRepository.findByTermsCodeContainingIgnoreCase("TEST", pageable)).thenReturn(page);
        
        Page<MasterTermsAndConditionDTO> result = termsAndConditionService.getAllTermsAndCondition(pageable, "TEST");
        
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("TEST_CODE", result.getContent().get(0).getTermsCode());
    }

    @Test
    void getAllTermsAndCondition_WithoutSearch() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<MasterTermsAndCondition> page = new PageImpl<>(List.of(termsEntity));
        
        when(termsAndConditionRepository.findAll(pageable)).thenReturn(page);
        
        Page<MasterTermsAndConditionDTO> result = termsAndConditionService.getAllTermsAndCondition(pageable, null);
        
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }
}
