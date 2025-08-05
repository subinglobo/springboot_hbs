package com.choosenfly.hotelbookingsystem.masters.service.mealPlan;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.masters.dto.MasterMealPlanDTO;

import jakarta.validation.Valid;

public interface MealPlanServiceInterface {

	Long saveMasterMealPlan(@Valid MasterMealPlanDTO mealDTO);

	MasterMealPlanDTO getMealDetailsById(Long id);

	MasterMealPlanDTO editMealPlan(Long id, @Valid MasterMealPlanDTO mealDTO);

	ResponseEntity<String> deleteMealPlan(Long id);

}
