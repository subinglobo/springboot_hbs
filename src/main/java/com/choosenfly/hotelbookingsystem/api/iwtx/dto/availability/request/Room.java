package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request;

import jakarta.xml.bind.annotation.XmlElement;

public class Room {
    
    private Adult adult;
    private String roomTypeCode;
    private String mealPlanCode;
    private String contractTokenId;
    private String roomConfigurationId;
    
    @XmlElement(name = "Adult")
    public Adult getAdult() {
        return adult;
    }
    
    public void setAdult(Adult adult) {
        this.adult = adult;
    }
    
    @XmlElement(name = "RoomTypeCode")
    public String getRoomTypeCode() {
        return roomTypeCode;
    }
    
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }
    
    @XmlElement(name = "MealPlanCode")
    public String getMealPlanCode() {
        return mealPlanCode;
    }
    
    public void setMealPlanCode(String mealPlanCode) {
        this.mealPlanCode = mealPlanCode;
    }
    
    @XmlElement(name = "ContractTokenId")
    public String getContractTokenId() {
        return contractTokenId;
    }
    
    public void setContractTokenId(String contractTokenId) {
        this.contractTokenId = contractTokenId;
    }
    
    @XmlElement(name = "RoomConfigurationId")
    public String getRoomConfigurationId() {
        return roomConfigurationId;
    }
    
    public void setRoomConfigurationId(String roomConfigurationId) {
        this.roomConfigurationId = roomConfigurationId;
    }
}
