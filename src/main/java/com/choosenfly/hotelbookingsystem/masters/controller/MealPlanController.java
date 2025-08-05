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

import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterMealPlanDTO;
import com.choosenfly.hotelbookingsystem.masters.service.bank.BankServiceInterface;
import com.choosenfly.hotelbookingsystem.masters.service.mealPlan.MealPlanService;
import com.choosenfly.hotelbookingsystem.masters.service.mealPlan.MealPlanServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mealplan")
public class MealPlanController {

	private final MealPlanServiceInterface mealPlanServiceInterface;
	
	@Autowired
	public MealPlanController(MealPlanServiceInterface mealPlanServiceInterface) {
		this.mealPlanServiceInterface = mealPlanServiceInterface;
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping("/save")
	private Long saveMasterMealPlan(@Valid @RequestBody MasterMealPlanDTO mealDTO){
		return mealPlanServiceInterface.saveMasterMealPlan(mealDTO);
	}
	
	@GetMapping("/{id}")
	private MasterMealPlanDTO getMealDetailsById(@PathVariable("id") Long id) {
		
		return mealPlanServiceInterface.getMealDetailsById(id);
	}
	
	@PutMapping("/{id}")
	private MasterMealPlanDTO editMealPlan(@PathVariable("id") Long id , @Valid @RequestBody MasterMealPlanDTO mealDTO) {
		return mealPlanServiceInterface.editMealPlan(id , mealDTO);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String>  deleteMealPlan(@PathVariable("id") Long id) {
		return mealPlanServiceInterface.deleteMealPlan(id);
	}
}
