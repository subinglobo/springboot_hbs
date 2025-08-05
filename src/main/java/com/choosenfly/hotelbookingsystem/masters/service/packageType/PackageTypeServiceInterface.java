package com.choosenfly.hotelbookingsystem.masters.service.packageType;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterPackageTypeDTO;

import jakarta.validation.Valid;

public interface PackageTypeServiceInterface {

	Long saveMasterPackageType(@Valid MasterPackageTypeDTO typeDTO);

	MasterPackageTypeDTO getPackageTypeDetailsById(Long id);

	MasterPackageTypeDTO editpackageType(Long id, @Valid MasterPackageTypeDTO typeDTO);

	ResponseEntity<String> deletepackageType(Long id);

}
