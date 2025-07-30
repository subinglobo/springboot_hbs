package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.bank;



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
import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.repository.master.MasterBankRepository;
import com.choosenfly.hotelbookingsystem.service.masters.bank.BankService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private MasterBankRepository masterBankRepository;

    @InjectMocks
    private BankService bankService;

    private MasterBank bankEntity;
    private MasterBankDTO bankDTO;

    @BeforeEach
    void setUp() {
        bankEntity = new MasterBank();
        bankEntity.setBankId(1L);
        bankEntity.setName("Test Bank");
        bankEntity.setIsDeleted(false);

        bankDTO = new MasterBankDTO();
        bankDTO.setName("Test Bank");
        bankDTO.setIsDeleted(false);
    }

    @Test
    void testSaveMasterBank() {
        when(masterBankRepository.save(any(MasterBank.class))).thenReturn(bankEntity);

        Long savedId = bankService.saveMasterBank(bankDTO);

        assertNotNull(savedId);
        assertEquals(1L, savedId);
        verify(masterBankRepository, times(1)).save(any(MasterBank.class));
    }

    @Test
    void testGetBankDetailsById_Success() {
        when(masterBankRepository.findById(1L)).thenReturn(Optional.of(bankEntity));

        MasterBankDTO result = bankService.getBankDetailsById(1L);

        assertNotNull(result);
        assertEquals("Test Bank", result.getName());
        verify(masterBankRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBankDetailsById_NotFound() {
        when(masterBankRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bankService.getBankDetailsById(1L));
    }

    @Test
    void testEditBank_Success() {
        when(masterBankRepository.findById(1L)).thenReturn(Optional.of(bankEntity));
        when(masterBankRepository.save(any(MasterBank.class))).thenReturn(bankEntity);

        MasterBankDTO updatedBank = new MasterBankDTO();
        updatedBank.setName("Test Bank");

        MasterBankDTO result = bankService.editBank(1L, updatedBank);

        assertNotNull(result);
        assertEquals("Test Bank", result.getName());
        verify(masterBankRepository, times(1)).findById(1L);
        verify(masterBankRepository, times(1)).save(any(MasterBank.class));
    }

    @Test
    void testEditBank_NotFound() {
        when(masterBankRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bankService.editBank(1L, bankDTO));
    }

    @Test
    void testDeleteBank_Success() {
        when(masterBankRepository.findById(1L)).thenReturn(Optional.of(bankEntity));

        ResponseEntity<String> response = bankService.deleteBank(1L);

        assertEquals("Bank with id 1 deleted successfully", response.getBody());
        verify(masterBankRepository, times(1)).delete(bankEntity);
    }

    @Test
    void testDeleteBank_NotFound() {
        when(masterBankRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bankService.deleteBank(1L));
    }
}
