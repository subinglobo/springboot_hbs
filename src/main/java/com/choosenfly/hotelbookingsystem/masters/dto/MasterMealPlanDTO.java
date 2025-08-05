package com.choosenfly.hotelbookingsystem.masters.dto;

import jakarta.validation.constraints.NotBlank;

public class MasterMealPlanDTO {

    private Long mealPlanId;
    
    private Boolean isDeleted;
    
    private Boolean mainMeals;
    
    @NotBlank(message = "name cannot be null or empty")
    private String name;
    
    private Boolean statusBreakfast;
    
    private Boolean statusDinner;
    
    private Boolean statusLunch;

	public Long getMealPlanId() {
		return mealPlanId;
	}

	public void setMealPlanId(Long mealPlanId) {
		this.mealPlanId = mealPlanId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getMainMeals() {
		return mainMeals;
	}

	public void setMainMeals(Boolean mainMeals) {
		this.mainMeals = mainMeals;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatusBreakfast() {
		return statusBreakfast;
	}

	public void setStatusBreakfast(Boolean statusBreakfast) {
		this.statusBreakfast = statusBreakfast;
	}

	public Boolean getStatusDinner() {
		return statusDinner;
	}

	public void setStatusDinner(Boolean statusDinner) {
		this.statusDinner = statusDinner;
	}

	public Boolean getStatusLunch() {
		return statusLunch;
	}

	public void setStatusLunch(Boolean statusLunch) {
		this.statusLunch = statusLunch;
	}

	@Override
	public String toString() {
		return "MasterMealPlanDTO [mealPlanId=" + mealPlanId + ", isDeleted=" + isDeleted + ", mainMeals=" + mainMeals
				+ ", name=" + name + ", statusBreakfast=" + statusBreakfast + ", statusDinner=" + statusDinner
				+ ", statusLunch=" + statusLunch + "]";
	}

    
    
    
}
