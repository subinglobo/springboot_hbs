package com.choosenfly.springboot.Choosenfly.hotelBookingSystemTest.service.masters.mealPlan;

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

import com.choosenfly.hotelbookingsystem.dto.masters.MasterBankDTO;
import com.choosenfly.hotelbookingsystem.dto.masters.MasterMealPlanDTO;
import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMeal;
import com.choosenfly.hotelbookingsystem.repository.master.MasterMealPlanRepository;
import com.choosenfly.hotelbookingsystem.service.masters.mealPlan.MealPlanService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class MealPlanServiceTest {

	@Mock
	private MasterMealPlanRepository mealPlanRepository;
	
	@InjectMocks
	private MealPlanService mealPlanService;
	
    private MasterMeal masterMealEntity;
    private MasterMealPlanDTO masterMealPlanDTO;
    
    @BeforeEach
    void setUp() {
    	masterMealEntity = new MasterMeal();
    	masterMealEntity.setMealPlanId(1L);
    	masterMealEntity.setName("Test MealPlan");
    	masterMealEntity.setIsDeleted(false);
    	masterMealEntity.setMainMeals(true);
    	masterMealEntity.setStatusBreakfast(true);
    	masterMealEntity.setStatusDinner(true);
    	masterMealEntity.setStatusLunch(false);

    	masterMealPlanDTO = new MasterMealPlanDTO();
    	masterMealPlanDTO.setIsDeleted(false);
    	masterMealPlanDTO.setMainMeals(true);
    	masterMealPlanDTO.setName("Test MealPlan");
    	masterMealPlanDTO.setStatusBreakfast(true);
    	masterMealPlanDTO.setStatusLunch(false);
    	masterMealPlanDTO.setStatusDinner(true);
    }
    
    @Test
    void testSaveMasterMealPlan() {
        when(mealPlanRepository.save(any(MasterMeal.class))).thenReturn(masterMealEntity);

        Long savedId = mealPlanService.saveMasterMealPlan(masterMealPlanDTO);

        assertNotNull(savedId);
        assertEquals(1L, savedId);
        verify(mealPlanRepository, times(1)).save(any(MasterMeal.class));
    }

    @Test
    void testGetMealPlanDetailsById_Success() {
        when(mealPlanRepository.findById(1L)).thenReturn(Optional.of(masterMealEntity));

        MasterMealPlanDTO result = mealPlanService.getMealDetailsById(1L);

        assertNotNull(result);
        assertEquals("Test MealPlan", result.getName());
        assertEquals(false,result.getIsDeleted());
        assertEquals(true,result.getMainMeals());
        assertEquals(true,result.getStatusBreakfast());
        assertEquals(true,result.getStatusDinner());
        assertEquals(false,result.getStatusLunch());
        verify(mealPlanRepository, times(1)).findById(1L);
    }

    @Test
    void testGetMealPlanById_NotFound() {
        when(mealPlanRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealPlanService.getMealDetailsById(1L));
    }

    @Test
    void testEditMealPlan_Success() {
        when(mealPlanRepository.findById(1L)).thenReturn(Optional.of(masterMealEntity));
        when(mealPlanRepository.save(any(MasterMeal.class))).thenReturn(masterMealEntity);

        MasterMealPlanDTO updatedMealPlan = new MasterMealPlanDTO();
        updatedMealPlan.setName("Test MealPlan");
        updatedMealPlan.setMainMeals(true);
        updatedMealPlan.setStatusBreakfast(true);
        updatedMealPlan.setStatusDinner(true);
        updatedMealPlan.setStatusLunch(false);
        updatedMealPlan.setIsDeleted(false);
        MasterMealPlanDTO result = mealPlanService.editMealPlan(1L, updatedMealPlan);

        assertNotNull(result);
        assertEquals("Test MealPlan", result.getName());
        assertEquals(false,result.getIsDeleted());
        assertEquals(true,result.getMainMeals());
        assertEquals(true,result.getStatusBreakfast());
        assertEquals(true,result.getStatusDinner());
        assertEquals(false,result.getStatusLunch());
        verify(mealPlanRepository, times(1)).findById(1L);
        verify(mealPlanRepository, times(1)).save(any(MasterMeal.class));
    }

    @Test
    void testEditMeal_NotFound() {
        when(mealPlanRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealPlanService.editMealPlan(1L, masterMealPlanDTO));
    }

    @Test
    void testDeleteMealPlan_Success() {
        when(mealPlanRepository.findById(1L)).thenReturn(Optional.of(masterMealEntity));

        ResponseEntity<String> response = mealPlanService.deleteMealPlan(1L);

        assertEquals("meal plan with id 1 deleted successfully", response.getBody());
        verify(mealPlanRepository, times(1)).delete(masterMealEntity);
    }

    @Test
    void testDeleteMealPlan_NotFound() {
        when(mealPlanRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mealPlanService.deleteMealPlan(1L));
    }
}
