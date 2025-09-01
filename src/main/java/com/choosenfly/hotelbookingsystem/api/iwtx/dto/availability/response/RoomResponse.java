package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class RoomResponse {
    
    private String roomNo;
    private String roomType;
    private String roomTypeCode;
    private String roomStatus;
    private BlackOut blackOut;
    private String currCode;
    private String contractTokenId;
    private String roomConfigurationId;
    private String ratePlanId;
    private String mealPlan;
    private String mealPlanCode;
    private String numberOfMeals;
    private String roomNumber;
    private String buyRate;
    private String sellCommissionAmount;
    private String commissionSellCharges;
    private String taxSellCharges;
    private String rate;
    private RateDetails rateDetails;
    private RoomStatusDetails roomStatusDetails;
    private String supplementDetails;
    private String discountDetails;
    private String cancellationPolicyDetails;
    private Messages messages;
    private String packageYN;
    private String nonRefundable;
    private String dynamicYN;
    private String recommendedRetailPrice;
    private String cashBackAmount;
    private String cashBackPercentage;
    private String contractLabel;
    
    @XmlElement(name = "RoomNo")
    public String getRoomNo() {
        return roomNo;
    }
    
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
    
    @XmlElement(name = "RoomType")
    public String getRoomType() {
        return roomType;
    }
    
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    @XmlElement(name = "RoomTypeCode")
    public String getRoomTypeCode() {
        return roomTypeCode;
    }
    
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }
    
    @XmlElement(name = "RoomStatus")
    public String getRoomStatus() {
        return roomStatus;
    }
    
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }
    
    @XmlElement(name = "BlackOut")
    public BlackOut getBlackOut() {
        return blackOut;
    }
    
    public void setBlackOut(BlackOut blackOut) {
        this.blackOut = blackOut;
    }
    
    @XmlElement(name = "CurrCode")
    public String getCurrCode() {
        return currCode;
    }
    
    public void setCurrCode(String currCode) {
        this.currCode = currCode;
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
    
    @XmlElement(name = "RatePlanId")
    public String getRatePlanId() {
        return ratePlanId;
    }
    
    public void setRatePlanId(String ratePlanId) {
        this.ratePlanId = ratePlanId;
    }
    
    @XmlElement(name = "MealPlan")
    public String getMealPlan() {
        return mealPlan;
    }
    
    public void setMealPlan(String mealPlan) {
        this.mealPlan = mealPlan;
    }
    
    @XmlElement(name = "MealPlanCode")
    public String getMealPlanCode() {
        return mealPlanCode;
    }
    
    public void setMealPlanCode(String mealPlanCode) {
        this.mealPlanCode = mealPlanCode;
    }
    
    @XmlElement(name = "NumberOfMeals")
    public String getNumberOfMeals() {
        return numberOfMeals;
    }
    
    public void setNumberOfMeals(String numberOfMeals) {
        this.numberOfMeals = numberOfMeals;
    }
    
    @XmlElement(name = "RoomNumber")
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    @XmlElement(name = "BuyRate")
    public String getBuyRate() {
        return buyRate;
    }
    
    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }
    
    @XmlElement(name = "SellCommissionAmount")
    public String getSellCommissionAmount() {
        return sellCommissionAmount;
    }
    
    public void setSellCommissionAmount(String sellCommissionAmount) {
        this.sellCommissionAmount = sellCommissionAmount;
    }
    
    @XmlElement(name = "CommissionSellCharges")
    public String getCommissionSellCharges() {
        return commissionSellCharges;
    }
    
    public void setCommissionSellCharges(String commissionSellCharges) {
        this.commissionSellCharges = commissionSellCharges;
    }
    
    @XmlElement(name = "TaxSellCharges")
    public String getTaxSellCharges() {
        return taxSellCharges;
    }
    
    public void setTaxSellCharges(String taxSellCharges) {
        this.taxSellCharges = taxSellCharges;
    }
    
    @XmlElement(name = "Rate")
    public String getRate() {
        return rate;
    }
    
    public void setRate(String rate) {
        this.rate = rate;
    }
    
    @XmlElement(name = "RateDetails")
    public RateDetails getRateDetails() {
        return rateDetails;
    }
    
    public void setRateDetails(RateDetails rateDetails) {
        this.rateDetails = rateDetails;
    }
    
    @XmlElement(name = "RoomStatusDetails")
    public RoomStatusDetails getRoomStatusDetails() {
        return roomStatusDetails;
    }
    
    public void setRoomStatusDetails(RoomStatusDetails roomStatusDetails) {
        this.roomStatusDetails = roomStatusDetails;
    }
    
    @XmlElement(name = "SupplementDetails")
    public String getSupplementDetails() {
        return supplementDetails;
    }
    
    public void setSupplementDetails(String supplementDetails) {
        this.supplementDetails = supplementDetails;
    }
    
    @XmlElement(name = "DiscountDetails")
    public String getDiscountDetails() {
        return discountDetails;
    }
    
    public void setDiscountDetails(String discountDetails) {
        this.discountDetails = discountDetails;
    }
    
    @XmlElement(name = "CancellationPolicyDetails")
    public String getCancellationPolicyDetails() {
        return cancellationPolicyDetails;
    }
    
    public void setCancellationPolicyDetails(String cancellationPolicyDetails) {
        this.cancellationPolicyDetails = cancellationPolicyDetails;
    }
    
    @XmlElement(name = "Messages")
    public Messages getMessages() {
        return messages;
    }
    
    public void setMessages(Messages messages) {
        this.messages = messages;
    }
    
    @XmlElement(name = "PackageYN")
    public String getPackageYN() {
        return packageYN;
    }
    
    public void setPackageYN(String packageYN) {
        this.packageYN = packageYN;
    }
    
    @XmlElement(name = "NonRefundable")
    public String getNonRefundable() {
        return nonRefundable;
    }
    
    public void setNonRefundable(String nonRefundable) {
        this.nonRefundable = nonRefundable;
    }
    
    @XmlElement(name = "DynamicYN")
    public String getDynamicYN() {
        return dynamicYN;
    }
    
    public void setDynamicYN(String dynamicYN) {
        this.dynamicYN = dynamicYN;
    }
    
    @XmlElement(name = "RecommendedRetailPrice")
    public String getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }
    
    public void setRecommendedRetailPrice(String recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }
    
    @XmlElement(name = "CashBackAmount")
    public String getCashBackAmount() {
        return cashBackAmount;
    }
    
    public void setCashBackAmount(String cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }
    
    @XmlElement(name = "CashBackPercentage")
    public String getCashBackPercentage() {
        return cashBackPercentage;
    }
    
    public void setCashBackPercentage(String cashBackPercentage) {
        this.cashBackPercentage = cashBackPercentage;
    }
    
    @XmlElement(name = "ContractLabel")
    public String getContractLabel() {
        return contractLabel;
    }
    
    public void setContractLabel(String contractLabel) {
        this.contractLabel = contractLabel;
    }
}
