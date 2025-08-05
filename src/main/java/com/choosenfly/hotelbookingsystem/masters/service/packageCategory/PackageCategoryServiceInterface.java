package com.choosenfly.hotelbookingsystem.masters.service.packageCategory;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterPackageCategoryDTO;

import jakarta.validation.Valid;

public interface PackageCategoryServiceInterface {

	Long saveMasterPackageCategory(@Valid MasterPackageCategoryDTO categoryDTO);

	MasterPackageCategoryDTO getPackageCategoryDetailsById(Long id);

	MasterPackageCategoryDTO editpackageCategory(Long id, @Valid MasterPackageCategoryDTO categoryDTO);

	ResponseEntity<String> deletepackageCategory(Long id);

}
