package com.choosenfly.hotelbookingsystem.dto.iwtx;

import java.util.Arrays;

public class IwtxRoomDTO {

	
	private IwtxAdultDTO[] adult;
	
	private IwtxAdultDTO[] child;
	
	private int roomTypeCode;
	
	private int mealPlanCode;
	
	private String contractTokenId;
	
	private int roomConfigurationId;
	
	private int[] adultAge;
	
	private int[] childAge;
	
	

	
	

	public int[] getAdultAge() {
		return adultAge;
	}

	public void setAdultAge(int[] adultAge) {
		this.adultAge = adultAge;
	}

	public int[] getChildAge() {
		return childAge;
	}

	public void setChildAge(int[] childAge) {
		this.childAge = childAge;
	}

	public IwtxAdultDTO[] getAdult() {
		return adult;
	}

	public void setAdult(IwtxAdultDTO[] adult) {
		this.adult = adult;
	}



	public IwtxAdultDTO[] getChild() {
		return child;
	}

	public void setChild(IwtxAdultDTO[] child) {
		this.child = child;
	}

	public int getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(int roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public int getMealPlanCode() {
		return mealPlanCode;
	}

	public void setMealPlanCode(int mealPlanCode) {
		this.mealPlanCode = mealPlanCode;
	}



	public String getContractTokenId() {
		return contractTokenId;
	}

	public void setContractTokenId(String contractTokenId) {
		this.contractTokenId = contractTokenId;
	}

	public int getRoomConfigurationId() {
		return roomConfigurationId;
	}

	public void setRoomConfigurationId(int roomConfigurationId) {
		this.roomConfigurationId = roomConfigurationId;
	}

	@Override
	public String toString() {
		return "IwtxRoomDTO [adult=" + Arrays.toString(adult) + ", child=" + Arrays.toString(child) + ", roomTypeCode="
				+ roomTypeCode + ", mealPlanCode=" + mealPlanCode + ", contractTokenId=" + contractTokenId
				+ ", roomConfigurationId=" + roomConfigurationId + "]";
	}

	

	
	
	
	
}
