package com.choosenfly.hotelbookingsystem.service.masters.packageCategory;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterPackageCategoryDTO;

import jakarta.validation.Valid;

public interface PackageCategoryServiceInterface {

	Long saveMasterPackageCategory(@Valid MasterPackageCategoryDTO categoryDTO);

	MasterPackageCategoryDTO getPackageCategoryDetailsById(Long id);

	MasterPackageCategoryDTO editpackageCategory(Long id, @Valid MasterPackageCategoryDTO categoryDTO);

	ResponseEntity<String> deletepackageCategory(Long id);

}
