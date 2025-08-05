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
import com.choosenfly.hotelbookingsystem.masters.service.agentCategory.AgentCategoryServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.packageCategory.PackageCategoryServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/packageCategory")
public class PackageCategoryController {

	private final PackageCategoryServiceInterface packageCategoryServiceInterface;
	
	@Autowired
	public PackageCategoryController(PackageCategoryServiceInterface packageCategoryServiceInterface) {
		this.packageCategoryServiceInterface = packageCategoryServiceInterface;
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/save")
	private Long saveMasterPackageCategory(@Valid @RequestBody MasterPackageCategoryDTO CategoryDTO){
		return packageCategoryServiceInterface.saveMasterPackageCategory(CategoryDTO);
	}
	
	@GetMapping("/{id}")
	private MasterPackageCategoryDTO getPackageCategoryDetailsById(@PathVariable("id") Long id) {
		
		return packageCategoryServiceInterface.getPackageCategoryDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private MasterPackageCategoryDTO editpackageCategory(@PathVariable("id") Long id , @Valid @RequestBody MasterPackageCategoryDTO CategoryDTO) {
		return packageCategoryServiceInterface.editpackageCategory(id , CategoryDTO);
	}
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deletepackageCategory(@PathVariable("id") Long id) {
		return packageCategoryServiceInterface.deletepackageCategory(id);
	}
}
