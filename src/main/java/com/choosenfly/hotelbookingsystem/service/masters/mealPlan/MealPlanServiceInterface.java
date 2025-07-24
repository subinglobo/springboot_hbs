package com.choosenfly.hotelbookingsystem.service.masters.mealPlan;

import org.springframework.http.ResponseEntity;

import com.choosenfly.hotelbookingsystem.dto.masters.MasterMealPlanDTO;

import jakarta.validation.Valid;

public interface MealPlanServiceInterface {

	Long saveMasterMealPlan(@Valid MasterMealPlanDTO mealDTO);

	MasterMealPlanDTO getMealDetailsById(Long id);

	MasterMealPlanDTO editMealPlan(Long id, @Valid MasterMealPlanDTO mealDTO);

	ResponseEntity<String> deleteMealPlan(Long id);

}
