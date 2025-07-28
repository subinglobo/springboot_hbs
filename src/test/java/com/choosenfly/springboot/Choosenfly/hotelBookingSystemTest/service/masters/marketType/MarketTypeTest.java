package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.marketType;



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
import com.choosenfly.hotelbookingsystem.dto.masters.MasterMarketTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarketType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterBankRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMarketTypeRepository;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMarkupTypeRepository;
import com.choosenfly.hotelbookingsystem.service.masters.bank.BankService;
import com.choosenfly.hotelbookingsystem.service.masters.marketType.MarketTypeService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class MarketTypeTest {

    @Mock
    private MasterMarketTypeRepository masterMarketTypeRepository;

    @InjectMocks
    private MarketTypeService marketTypeService;

    private MasterMarketType marketEntity;
    private MasterMarketTypeDTO marketTypeDTO;

    @BeforeEach
    void setUp() {
    	marketEntity = new MasterMarketType();
    	marketEntity.setMarketTypeId(1L);
    	marketEntity.setName("Test MarketType");
    	marketEntity.setIsDeleted(false);

    	marketTypeDTO = new MasterMarketTypeDTO();
    	marketTypeDTO.setName("Test MarketType");
    	marketTypeDTO.setIsDeleted(false);
    }

    @Test
    void testSaveContactType() {
        when(masterMarketTypeRepository.save(any(MasterMarketType.class))).thenReturn(marketEntity);

        Long savedId = marketTypeService.saveMarketType(marketTypeDTO);

        assertNotNull(savedId);
        assertEquals(1L, savedId);
        verify(masterMarketTypeRepository, times(1)).save(any(MasterMarketType.class));
    }

    @Test
    void testGetMarketTypeDetailsById_Success() {
        when(masterMarketTypeRepository.findById(1L)).thenReturn(Optional.of(marketEntity));

       MasterMarketTypeDTO result = marketTypeService.getMarketTypeById(1L);

        assertNotNull(result);
        assertEquals("Test MarketType", result.getName());
        verify(masterMarketTypeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetMarketTypeDetailsById_NotFound() {
        when(masterMarketTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> marketTypeService.getMarketTypeById(1L));
    }

    @Test
    void testEditMarketType_Success() {
        when(masterMarketTypeRepository.findById(1L)).thenReturn(Optional.of(marketEntity));
        when(masterMarketTypeRepository.save(any(MasterMarketType.class))).thenReturn(marketEntity);

        MasterMarketTypeDTO updatedMarketType = new MasterMarketTypeDTO();
        updatedMarketType.setName("Test MarketType");

        MasterMarketTypeDTO result = marketTypeService.editMarketType(1L, updatedMarketType);

        assertNotNull(result);
        assertEquals("Test MarketType", result.getName());
        verify(masterMarketTypeRepository, times(1)).findById(1L);
        verify(masterMarketTypeRepository, times(1)).save(any(MasterMarketType.class));
    }

    @Test
    void testEditMarketType_NotFound() {
        when(masterMarketTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> marketTypeService.editMarketType(1L, marketTypeDTO));
    }

    @Test
    void testDeleteMarketType_Success() {
        when(masterMarketTypeRepository.findById(1L)).thenReturn(Optional.of(marketEntity));

        ResponseEntity<String> response = marketTypeService.deleteMarketType(1L);

        assertEquals("Market Type with id 1 deleted successfully", response.getBody());
        verify(masterMarketTypeRepository, times(1)).delete(marketEntity);
    }

    @Test
    void testDeleteMarketType_NotFound() {
        when(masterMarketTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> marketTypeService.deleteMarketType(1L));
    }
}
