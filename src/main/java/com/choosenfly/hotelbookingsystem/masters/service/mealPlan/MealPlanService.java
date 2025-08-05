package com.choosenfly.hotelbookingsystem.masters.service.mealPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.exceptions.EntityCreationException;
import com.choosenfly.hotelbookingsystem.exceptions.MissingRequestBodyException;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.masters.dto.MasterMealPlanDTO;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterBank;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterMeal;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterBankRepository;
import com.choosenfly.hotelbookingsystem.masters.repository.MasterMealPlanRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class MealPlanService implements MealPlanServiceInterface{
	
	private final MasterMealPlanRepository mealPlanRepository;
	
	@Autowired
	public MealPlanService(MasterMealPlanRepository mealPlanRepository) {
		this.mealPlanRepository = mealPlanRepository;
		
	}

	@Override
	@Transactional
	public Long saveMasterMealPlan(@Valid MasterMealPlanDTO mealDTO) {
		// TODO Auto-generated method stub
			if (mealDTO == null) {
			throw new MissingRequestBodyException("mealDTO data is null.");
			}

			MasterMeal entity = new MasterMeal();
			entity.setName(mealDTO.getName());
			entity.setIsDeleted(false);
			entity.setMainMeals(mealDTO.getMainMeals());
			entity.setStatusBreakfast(mealDTO.getStatusBreakfast());
			entity.setStatusLunch(mealDTO.getStatusLunch());
			entity.setStatusDinner(mealDTO.getStatusDinner());

		    try {
		        MasterMeal savedEntity = mealPlanRepository.save(entity);

		        if (savedEntity == null || savedEntity.getMealPlanId() == null || savedEntity.getMealPlanId() == 0) {
		            throw new EntityCreationException("Failed to persist Meal Plan.");
		        }

		        return savedEntity.getMealPlanId();

		    } catch (IllegalArgumentException e) {
		        throw new EntityCreationException("Entity cannot be null while saving Meal Plan.", e);
		    } catch (OptimisticLockingFailureException e) {
		        throw new EntityCreationException("Meal Plan version conflict occurred during save.", e);
		    } catch (Exception e) {
		        throw new EntityCreationException("Unexpected error while saving Meal Plan: " + e.getMessage(), e);
		    }
		}

	@Override
	@Transactional
	public MasterMealPlanDTO getMealDetailsById(Long id) {
		// TODO Auto-generated method stub

		MasterMeal meal = mealPlanRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Meal plan is not found "+id));
		if(meal.getMealPlanId()!=null) {
			MasterMealPlanDTO mealPlanDTO = new MasterMealPlanDTO();
			mealPlanDTO.setMealPlanId(meal.getMealPlanId());
			mealPlanDTO.setName(meal.getName());
			mealPlanDTO.setMainMeals(meal.getMainMeals());
			mealPlanDTO.setStatusBreakfast(meal.getStatusBreakfast());
			mealPlanDTO.setStatusLunch(meal.getStatusLunch());
			mealPlanDTO.setStatusDinner(meal.getStatusDinner());
			mealPlanDTO.setIsDeleted(meal.getIsDeleted());
			
			return mealPlanDTO;
		}
		return null;
		
		
	}

	@Override
	@Transactional
	public MasterMealPlanDTO editMealPlan(Long id, @Valid MasterMealPlanDTO mealDTO) {
		// TODO Auto-generated method stub
		MasterMeal meal = mealPlanRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Meal plan is not found "+id));

			meal.setName(mealDTO.getName());
			meal.setMainMeals(mealDTO.getMainMeals());
			meal.setStatusBreakfast(mealDTO.getStatusBreakfast());
			meal.setStatusLunch(mealDTO.getStatusLunch());
			meal.setStatusDinner(mealDTO.getStatusDinner());

			MasterMeal masterMeal = mealPlanRepository.save(meal);
			
			MasterMealPlanDTO mealPlanDTO = new MasterMealPlanDTO();
			mealPlanDTO.setMealPlanId(masterMeal.getMealPlanId());
			mealPlanDTO.setName(masterMeal.getName());
			mealPlanDTO.setMainMeals(masterMeal.getMainMeals());
			mealPlanDTO.setStatusBreakfast(masterMeal.getStatusBreakfast());
			mealPlanDTO.setStatusLunch(masterMeal.getStatusLunch());
			mealPlanDTO.setStatusDinner(masterMeal.getStatusDinner());
			mealPlanDTO.setIsDeleted(masterMeal.getIsDeleted());
			
		return mealPlanDTO;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteMealPlan(Long id) {
		// TODO Auto-generated method stub
		
		MasterMeal meal = mealPlanRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Meal plan is not found "+id));
		
		mealPlanRepository.delete(meal);
		
		return ResponseEntity.ok("meal plan with id " + id + " deleted successfully");
	
	}

	}


