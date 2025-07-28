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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterPackageCategoryDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterPackageCategoryDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterPackageCategory;
import com.choosenfly.hotelbookingsystem.entities.master.MasterPackageCategory;
import com.choosenfly.hotelbookingsystem.repository.master.MasterPackageCategoryRepository;
import com.choosenfly.hotelbookingsystem.service.masters.packageCategory.PackageCategoryService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PackageCategoryServceTest {

    @Mock
    private MasterPackageCategoryRepository packageCategoryRepository;

    @InjectMocks
    private PackageCategoryService categoryService;

    private MasterPackageCategory categoryEntity;
    private MasterPackageCategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryEntity = new MasterPackageCategory();
        categoryEntity.setPackageCategoryId(1L);;
        categoryEntity.setName("Test PackageCategory");
        categoryEntity.setCode("Test");

        categoryDTO = new MasterPackageCategoryDTO();
        categoryDTO.setName("Test PackageCategory");
        categoryDTO.setCode("Test");
    }

    @Test
    void testSaveMasterPackageCategory() {
        when(packageCategoryRepository.save(any(MasterPackageCategory.class))).thenReturn(categoryEntity);

        Long savedId = categoryService.saveMasterPackageCategory(categoryDTO);

        assertNotNull(savedId);
        assertEquals(1L, savedId);
        verify(packageCategoryRepository, times(1)).save(any(MasterPackageCategory.class));
    }

    @Test
    void testGetPackageCategoryById_Success() {
        when(packageCategoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        MasterPackageCategoryDTO result = categoryService.getPackageCategoryDetailsById(1L);

        assertNotNull(result);
        assertEquals("Test PackageCategory", result.getName());
        verify(packageCategoryRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPackageCategoryById_NotFound() {
        when(packageCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.getPackageCategoryDetailsById(1L));
    }

    @Test
    void testEditPackageCategory_Success() {
        when(packageCategoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(packageCategoryRepository.save(any(MasterPackageCategory.class))).thenReturn(categoryEntity);

        MasterPackageCategoryDTO updatedPackageCategory = new MasterPackageCategoryDTO();
        updatedPackageCategory.setName("Test packageCategory");
        updatedPackageCategory.setCode("test");
        MasterPackageCategoryDTO result = categoryService.editpackageCategory(1L, updatedPackageCategory);

        assertNotNull(result);
        assertEquals("Test packageCategory", result.getName());
        assertEquals("test",result.getCode());
        verify(packageCategoryRepository, times(1)).findById(1L);
        verify(packageCategoryRepository, times(1)).save(any(MasterPackageCategory.class));
    }

    @Test
    void testEditPackageCategory_NotFound() {
        when(packageCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.editpackageCategory(1L, categoryDTO));
    }

    @Test
    void testDeletePackageCategory_Success() {
        when(packageCategoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        ResponseEntity<String> response = categoryService.deletepackageCategory(1L);

        assertEquals("package category with id 1 deleted successfully", response.getBody());
        verify(packageCategoryRepository, times(1)).delete(categoryEntity);
    }

    @Test
    void testDeletePackageCategory_NotFound() {
        when(packageCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.deletepackageCategory(1L));
    }
}
