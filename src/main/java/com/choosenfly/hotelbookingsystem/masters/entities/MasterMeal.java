package com.choosenfly.hotelbookingsystem.masters.entities;

import java.time.LocalDateTime;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_meal_plan")
public class MasterMeal extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_plan_id" , nullable = false)
    private Long mealPlanId;

    @Column(name = "main_meals")
    private Boolean mainMeals;

    @Column(name = "name" , length = 100)
    private String name;

    @Column(name = "status_breakfast")
    private Boolean statusBreakfast;

    @Column(name = "status_dinner")
    private Boolean statusDinner;

    @Column(name = "status_lunch")
    private Boolean statusLunch;
    
    @Column(name = "isDeleted")
    private Boolean isDeleted;

	public Long getMealPlanId() {
		return mealPlanId;
	}

	public void setMealPlanId(Long mealPlanId) {
		this.mealPlanId = mealPlanId;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "MasterMeal [mealPlanId=" + mealPlanId + ", mainMeals=" + mainMeals + ", name=" + name
				+ ", statusBreakfast=" + statusBreakfast + ", statusDinner=" + statusDinner + ", statusLunch="
				+ statusLunch + ", isDeleted=" + isDeleted + "]";
	}
    
    


    
    
}

