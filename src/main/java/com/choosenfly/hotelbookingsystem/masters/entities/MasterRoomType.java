package com.choosenfly.hotelbookingsystem.masters.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_roomtype", schema = "public")
public class MasterRoomType extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomtype_seq")
	@SequenceGenerator(name = "roomtype_seq", sequenceName = "roomtype_roomtype_id_seq", allocationSize = 1)
	@Column(name = "roomtype_id", nullable = false)
	private Long roomtypeId;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "code", length = 100)
	private String code;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "meal_plan_id")
	private Long mealPlanId;
	
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<HotelRoomType> hotelRoomTypes  ;

	// Getters and Setters
	public Long getRoomtypeId() {
		return roomtypeId;
	}

	public void setRoomtypeId(Long roomtypeId) {
		this.roomtypeId = roomtypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getMealPlanId() {
		return mealPlanId;
	}

	public void setMealPlanId(Long mealPlanId) {
		this.mealPlanId = mealPlanId;
	}

	public List<HotelRoomType> getHotelRoomTypes() {
		return hotelRoomTypes;
	}

	public void setHotelRoomTypes(List<HotelRoomType> hotelRoomTypes) {
		this.hotelRoomTypes = hotelRoomTypes;
	}
	
}