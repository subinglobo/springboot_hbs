package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.currency;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterCurrencyDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterCurrency;
import com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.repository.master.MasterCurrencyRepository;
import com.choosenfly.hotelbookingsystem.service.masters.currency.CurrencyService;



@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private MasterCurrencyRepository masterCurrencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    private MasterCurrency currencyEntity;
    private MasterCurrencyDTO currencyDTO;

    @BeforeEach
    void setUp() {
    	currencyEntity = new MasterCurrency();
    	currencyEntity.setCurrencyId(1L);
    	currencyEntity.setName("Test Currency");
    	currencyEntity.setValue("10");
    	currencyEntity.setCurrencyCode("Inr");
    	currencyEntity.setIsDeleted(false);

    	currencyDTO = new MasterCurrencyDTO();
    	currencyDTO.setName("Test Currency");
    	currencyDTO.setValue("10");
    	currencyDTO.setCurrencyCode("Inr");
    	currencyDTO.setIsDeleted(false);
    }

    @Test
    void testSaveCurrency() {
        when(masterCurrencyRepository.save(any(MasterCurrency.class))).thenReturn(currencyEntity);

        Long savedId = currencyService.saveCurrency(currencyDTO);

        assertNotNull(savedId);
        assertEquals(1L, savedId);
        verify(masterCurrencyRepository, times(1)).save(any(MasterCurrency.class));
    }
    
    @Test
    void testSaveCurrency_NullOrEmptyDTO() {
        // Null case
        assertThrows(MissingRequestBodyException.class, () -> currencyService.saveCurrency(null));
        verify(masterCurrencyRepository, never()).save(any());

       
    }


    @Test
    void testGetCurrencyDetailsById_Success() {
        when(masterCurrencyRepository.findById(1L)).thenReturn(Optional.of(currencyEntity));

        MasterCurrencyDTO result = currencyService.getCurrecnyById(1L);

        assertNotNull(result);
        assertEquals("Test Currency", result.getName());
        verify(masterCurrencyRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCurrencyDetailsById_NotFound() {
        when(masterCurrencyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> currencyService.getCurrecnyById(1L));
    }

    @Test
    void testEditCurrency_Success() {
        when(masterCurrencyRepository.findById(1L)).thenReturn(Optional.of(currencyEntity));
        when(masterCurrencyRepository.save(any(MasterCurrency.class))).thenReturn(currencyEntity);

        MasterCurrencyDTO updatedCurrency = new MasterCurrencyDTO();
        updatedCurrency.setName("Test Currency");

       MasterCurrencyDTO result = currencyService.editCurrency(1L, updatedCurrency);

        assertNotNull(result);
        assertEquals("Test Currency", result.getName());
        verify(masterCurrencyRepository, times(1)).findById(1L);
        verify(masterCurrencyRepository, times(1)).save(any(MasterCurrency.class));
    }

    @Test
    void testEditCurrecny_NotFound() {
        when(masterCurrencyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> currencyService.editCurrency(1L, currencyDTO));
    }

    @Test
    void testDeleteCurrency_Success() {
        when(masterCurrencyRepository.findById(1L)).thenReturn(Optional.of(currencyEntity));

        ResponseEntity<String> response = currencyService.deleteCurrency(1L);

        assertEquals("Currency with id 1 deleted successfully", response.getBody());
        verify(masterCurrencyRepository, times(1)).delete(currencyEntity);
    }

    @Test
    void testDeleteCurrency_NotFound() {
        when(masterCurrencyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> currencyService.deleteCurrency(1L));
    }
}
