package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.PackageSettings;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterPackageTypeDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterPackageTypeDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterPackageType;
import com.choosenfly.hotelbookingsystem.entities.master.MasterPackageType;
import com.choosenfly.hotelbookingsystem.repository.master.MasterPackageTypeRepository;
import com.choosenfly.hotelbookingsystem.service.masters.packageType.PackageTypeService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PackageTypeServiceTest {

	 @Mock
	    private MasterPackageTypeRepository packageTypeRepository;

	    @InjectMocks
	    private PackageTypeService packageTypeService;

	    private MasterPackageType packageTypeEntity;
	    private MasterPackageTypeDTO packageTypeDTO;

	    @BeforeEach
	    void setUp() {
	        packageTypeEntity = new MasterPackageType();
	        packageTypeEntity.setPackageTypeId(1L);;
	        packageTypeEntity.setName("Test packageType");
	        packageTypeEntity.setCode("Test");

	        packageTypeDTO = new MasterPackageTypeDTO();
	        packageTypeDTO.setName("Test packageType");
	        packageTypeDTO.setCode("Test");
	    }

	    @Test
	    void testSaveMasterPackageType() {
	        when(packageTypeRepository.save(any(MasterPackageType.class))).thenReturn(packageTypeEntity);

	        Long savedId = packageTypeService.saveMasterPackageType(packageTypeDTO);

	        assertNotNull(savedId);
	        assertEquals(1L, savedId);
	        verify(packageTypeRepository, times(1)).save(any(MasterPackageType.class));
	    }

	    @Test
	    void testGetpackageTypeById_Success() {
	        when(packageTypeRepository.findById(1L)).thenReturn(Optional.of(packageTypeEntity));

	        MasterPackageTypeDTO result = packageTypeService.getPackageTypeDetailsById(1L);

	        assertNotNull(result);
	        assertEquals("Test packageType", result.getName());
	        assertEquals("Test",result.getCode());
	        verify(packageTypeRepository, times(1)).findById(1L);
	    }

	    @Test
	    void testGetpackageTypeById_NotFound() {
	        when(packageTypeRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> packageTypeService.getPackageTypeDetailsById(1L));
	    }

	    @Test
	    void testEditpackageType_Success() {
	        when(packageTypeRepository.findById(1L)).thenReturn(Optional.of(packageTypeEntity));
	        when(packageTypeRepository.save(any(MasterPackageType.class))).thenReturn(packageTypeEntity);

	        MasterPackageTypeDTO updatedpackageType = new MasterPackageTypeDTO();
	        updatedpackageType.setName("Test packageType");
	        updatedpackageType.setCode("test");
	        MasterPackageTypeDTO result = packageTypeService.editpackageType(1L, updatedpackageType);

	        assertNotNull(result);
	        assertEquals("Test packageType", result.getName());
	        assertEquals("test",result.getCode());
	        verify(packageTypeRepository, times(1)).findById(1L);
	        verify(packageTypeRepository, times(1)).save(any(MasterPackageType.class));
	    }

	    @Test
	    void testEditpackageType_NotFound() {
	        when(packageTypeRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> packageTypeService.editpackageType(1L, packageTypeDTO));
	    }

	    @Test
	    void testDeletepackageType_Success() {
	        when(packageTypeRepository.findById(1L)).thenReturn(Optional.of(packageTypeEntity));

	        ResponseEntity<String> response = packageTypeService.deletepackageType(1L);

	        assertEquals("package type with id 1 deleted successfully", response.getBody());
	        verify(packageTypeRepository, times(1)).delete(packageTypeEntity);
	    }

	    @Test
	    void testDeletepackageType_NotFound() {
	        when(packageTypeRepository.findById(1L)).thenReturn(Optional.empty());

	        assertThrows(EntityNotFoundException.class, () -> packageTypeService.deletepackageType(1L));
	    }
}
