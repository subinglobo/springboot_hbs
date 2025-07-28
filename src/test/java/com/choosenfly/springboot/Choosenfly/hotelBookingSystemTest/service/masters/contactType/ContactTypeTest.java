package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.contactType;



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
import com.choosenfly.hotelbookingsystem.dto.masters.MasterContactTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.entities.master.MasterContactType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterBankRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterContactTypeRepository;
import com.choosenfly.hotelbookingsystem.service.masters.ContactType.ContactTypeService;
import com.choosenfly.hotelbookingsystem.service.masters.bank.BankService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class ContactTypeTest {

    @Mock
    private MasterContactTypeRepository masterContactTypeRepository;

    @InjectMocks
    private ContactTypeService contactTypeService;

    private MasterContactType contactEntity;
    private MasterContactTypeDTO contactDTO;

    @BeforeEach
    void setUp() {
    	contactEntity = new MasterContactType();
    	contactEntity.setContacttypeId(1L);
    	contactEntity.setName("Test Contacttype");
    	contactEntity.setIsDeleted(false);

    	contactDTO = new MasterContactTypeDTO();
    	contactDTO.setName("Test Contacttype");
    	contactDTO.setIsDeleted(false);
    }

    @Test
    void testSaveMasterCotactType() {
        when(masterContactTypeRepository.save(any(MasterContactType.class))).thenReturn(contactEntity);

        Long savedId = contactTypeService.saveContactType(contactDTO);

        assertNotNull(savedId);
        assertEquals(1L, savedId);
        verify(masterContactTypeRepository, times(1)).save(any(MasterContactType.class));
    }

    @Test
    void testGetContactTypeDetailsById_Success() {
        when(masterContactTypeRepository.findById(1L)).thenReturn(Optional.of(contactEntity));

        MasterContactTypeDTO result = contactTypeService.getContactTypeById(1L);

        assertNotNull(result);
        assertEquals("Test Contacttype", result.getName());
        verify(masterContactTypeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetContactTypeDetailsById_NotFound() {
        when(masterContactTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> contactTypeService.getContactTypeById(1L));
    }

    @Test
    void testEditContactType_Success() {
        when(masterContactTypeRepository.findById(1L)).thenReturn(Optional.of(contactEntity));
        when(masterContactTypeRepository.save(any(MasterContactType.class))).thenReturn(contactEntity);

        MasterContactTypeDTO updatedContactType = new MasterContactTypeDTO();
        updatedContactType.setName("Test Contacttype");

        MasterContactTypeDTO result = contactTypeService.editContactType(1L, updatedContactType);

        assertNotNull(result);
        assertEquals("Test Contacttype", result.getName());
        verify(masterContactTypeRepository, times(1)).findById(1L);
        verify(masterContactTypeRepository, times(1)).save(any(MasterContactType.class));
    }

    @Test
    void testEditContactType_NotFound() {
        when(masterContactTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> contactTypeService.editContactType(1L, contactDTO));
    }

    @Test
    void testDeleteContactType_Success() {
        when(masterContactTypeRepository.findById(1L)).thenReturn(Optional.of(contactEntity));

        ResponseEntity<String> response = contactTypeService.deleteContactType(1L);

        assertEquals("Contact Type with id 1 deleted successfully", response.getBody());
        verify(masterContactTypeRepository, times(1)).delete(contactEntity);
    }

    @Test
    void testDeleteContactType_NotFound() {
        when(masterContactTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> contactTypeService.deleteContactType(1L));
    }
}
