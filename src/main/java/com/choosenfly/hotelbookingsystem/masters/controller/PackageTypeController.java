package com.choosenfly.hotelbookingsystem.masters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterAgentCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterPackageCategoryDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterPackageTypeDTO;
import com.choosenfly.hotelbookingsystem.masters.service.packageCategory.PackageCategoryServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.packageType.PackageTypeService;
import com.choosenfly.hotelbookingsystem.masters.service.packageType.PackageTypeServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/packageType")
public class PackageTypeController {

	private final PackageTypeServiceInterface  packageTypeServiceInterface;
	
	@Autowired
	public PackageTypeController(PackageTypeServiceInterface packageTypeServiceInterface) {
		this.packageTypeServiceInterface = packageTypeServiceInterface;
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/save")
	private Long saveMasterPackageType(@Valid @RequestBody MasterPackageTypeDTO typeDTO){
		return packageTypeServiceInterface.saveMasterPackageType(typeDTO);
	}
	
	@GetMapping("/{id}")
	private MasterPackageTypeDTO getPackageTypeDetailsById(@PathVariable("id") Long id) {
		
		return packageTypeServiceInterface.getPackageTypeDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private MasterPackageTypeDTO editpackageType(@PathVariable("id") Long id , @Valid @RequestBody MasterPackageTypeDTO typeDTO) {
		return packageTypeServiceInterface.editpackageType(id , typeDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String> deletepackageType(@PathVariable("id") Long id) {
		return packageTypeServiceInterface.deletepackageType(id);
	}
}
