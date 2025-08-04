package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.PackageSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterVisaInformationDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCountry;
import com.choosenfly.hotelbookingsystem.entities.master.MasterVisaInformation;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCountryRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterVisaInformationRepository;
import com.choosenfly.hotelbookingsystem.service.masters.visaInformation.VisaInformationService;

@ExtendWith(MockitoExtension.class)
public class VisaInformationServiceTest {

    @Mock
    private MasterVisaInformationRepository informationRepository;

    @Mock
    private MasterCountryRepository masterCountryRepository;

    @InjectMocks
    private VisaInformationService visaInformationService;

    private MasterVisaInformationDTO visaDTO;
    private MasterVisaInformation visaEntity;
    private MasterCountry country;
    private MasterCountry passportCountry;

    @BeforeEach
    void setUp() {
        country = new MasterCountry();
        country.setId(1L);
        
        passportCountry = new MasterCountry();
        passportCountry.setId(2L);
        
        visaDTO = new MasterVisaInformationDTO();
        visaDTO.setCountryId(1L);
        visaDTO.setPaxPassportCountryId(2L);
        visaDTO.setPassportCode("US");
        visaDTO.setVisaDescription("Test Visa Description");
        
        visaEntity = new MasterVisaInformation();
        visaEntity.setVisaId(1L);
        visaEntity.setCountry(country);
        visaEntity.setPassportCountry(passportCountry);
        visaEntity.setPassportCode("US");
        visaEntity.setVisaDescription("Test Visa Description");
    }

    @Test
    void saveVisaInformation_Success() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(masterCountryRepository.findById(2L)).thenReturn(Optional.of(passportCountry));
        when(informationRepository.save(any(MasterVisaInformation.class))).thenReturn(visaEntity);
        
        Long result = visaInformationService.saveVisaInformation(visaDTO);
        
        assertNotNull(result);
        assertEquals(1L, result);
        verify(informationRepository, times(1)).save(any(MasterVisaInformation.class));
    }

    @Test
    void saveVisaInformation_CountryNotFound() {
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            visaInformationService.saveVisaInformation(visaDTO);
        });
    }

    @Test
    void getVisaInformationById_Success() {
        when(informationRepository.findById(1L)).thenReturn(Optional.of(visaEntity));
        
        MasterVisaInformationDTO result = visaInformationService.getVisaInformationById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getVisaId());
        assertEquals(1L, result.getCountryId());
        assertEquals(2L, result.getPaxPassportCountryId());
        assertEquals("US", result.getPassportCode());
        assertEquals("Test Visa Description", result.getVisaDescription());
    }

    @Test
    void getVisaInformationById_NotFound() {
        when(informationRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            visaInformationService.getVisaInformationById(1L);
        });
    }

    @Test
    void editVisaInfo_Success() {
        when(informationRepository.findById(1L)).thenReturn(Optional.of(visaEntity));
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(masterCountryRepository.findById(2L)).thenReturn(Optional.of(passportCountry));
        when(informationRepository.save(any(MasterVisaInformation.class))).thenReturn(visaEntity);
        
        MasterVisaInformationDTO updatedDTO = new MasterVisaInformationDTO();
        updatedDTO.setCountryId(1L);
        updatedDTO.setPaxPassportCountryId(2L);
        updatedDTO.setPassportCode("UpdatedCode");
        updatedDTO.setVisaDescription("Updated Description");
        
        MasterVisaInformationDTO result = visaInformationService.editVisaInfo(1L, updatedDTO);
        
        assertNotNull(result);
        assertEquals(1L, result.getVisaId());
        assertEquals("UpdatedCode", result.getPassportCode());
        assertEquals("Updated Description", result.getVisaDescription());
    }

    @Test
    void editVisaInfo_VisaNotFound() {
        when(informationRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            visaInformationService.editVisaInfo(1L, visaDTO);
        });
    }

    @Test
    void editVisaInfo_CountryNotFound() {
        when(informationRepository.findById(1L)).thenReturn(Optional.of(visaEntity));
        when(masterCountryRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            visaInformationService.editVisaInfo(1L, visaDTO);
        });
    }

    @Test
    void deleteVisaInfo_Success() {
        when(informationRepository.findById(1L)).thenReturn(Optional.of(visaEntity));
        doNothing().when(informationRepository).delete(visaEntity);
        
        ResponseEntity<String> response = visaInformationService.deleteVisaInfo(1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("deleted successfully"));
        verify(informationRepository, times(1)).delete(visaEntity);
    }

    @Test
    void deleteVisaInfo_NotFound() {
        when(informationRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> {
            visaInformationService.deleteVisaInfo(1L);
        });
    }

    @Test
    void getAllVisaInfo_WithSearch() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<MasterVisaInformation> page = new PageImpl<>(List.of(visaEntity));
        
        when(informationRepository.findByPassportCodeContainingIgnoreCase("US", pageable)).thenReturn(page);
        
        Page<MasterVisaInformationDTO> result = visaInformationService.getAllVisaInfo(pageable, "US");
        
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("US", result.getContent().get(0).getPassportCode());
    }

    @Test
    void getAllVisaInfo_WithoutSearch() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<MasterVisaInformation> page = new PageImpl<>(List.of(visaEntity));
        
        when(informationRepository.findAll(pageable)).thenReturn(page);
        
        Page<MasterVisaInformationDTO> result = visaInformationService.getAllVisaInfo(pageable, null);
        
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void getAllVisaInfo_EmptySearch() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<MasterVisaInformation> page = new PageImpl<>(List.of(visaEntity));
        
        when(informationRepository.findAll(pageable)).thenReturn(page);
        
        Page<MasterVisaInformationDTO> result = visaInformationService.getAllVisaInfo(pageable, "");
        
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }
}
