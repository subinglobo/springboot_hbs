package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

/**
 * Grouped room response DTO for IWTX API
 */
public class IwtxGroupedRoomResponse {
    
    @JsonProperty("roomCategory")
    private String roomCategory;
    
    @JsonProperty("roomTypeCode")
    private String roomTypeCode;
    
    @JsonProperty("baseRoomType")
    private String baseRoomType;
    
    @JsonProperty("availableRates")
    private List<IwtxRateOption> availableRates;
    
    // Constructors
    public IwtxGroupedRoomResponse() {}
    
    public IwtxGroupedRoomResponse(String roomCategory, String roomTypeCode, String baseRoomType) {
        this.roomCategory = roomCategory;
        this.roomTypeCode = roomTypeCode;
        this.baseRoomType = baseRoomType;
    }
    
    // Getters and Setters
    public String getRoomCategory() { return roomCategory; }
    public void setRoomCategory(String roomCategory) { this.roomCategory = roomCategory; }
    
    public String getRoomTypeCode() { return roomTypeCode; }
    public void setRoomTypeCode(String roomTypeCode) { this.roomTypeCode = roomTypeCode; }
    
    public String getBaseRoomType() { return baseRoomType; }
    public void setBaseRoomType(String baseRoomType) { this.baseRoomType = baseRoomType; }
    
    public List<IwtxRateOption> getAvailableRates() { return availableRates; }
    public void setAvailableRates(List<IwtxRateOption> availableRates) { this.availableRates = availableRates; }
    
    /**
     * Rate option within a room category
     */
    public static class IwtxRateOption {
        @JsonProperty("mealPlan")
        private String mealPlan;
        
        @JsonProperty("mealPlanCode")
        private String mealPlanCode;
        
        @JsonProperty("currency")
        private String currency;
        
        @JsonProperty("rate")
        private BigDecimal rate;
        
        @JsonProperty("totalRate")
        private BigDecimal totalRate;
        
        @JsonProperty("rateBeforeTax")
        private BigDecimal rateBeforeTax;
        
        @JsonProperty("recommendedRetailPrice")
        private BigDecimal recommendedRetailPrice;
        
        @JsonProperty("roomStatus")
        private String roomStatus;
        
        @JsonProperty("nonRefundable")
        private boolean nonRefundable;
        
        @JsonProperty("contractLabel")
        private String contractLabel;
        
        @JsonProperty("contractTokenId")
        private String contractTokenId;
        
        @JsonProperty("cancellationPolicies")
        private List<IwtxCancellationPolicy> cancellationPolicies;
        
        // Constructors
        public IwtxRateOption() {}
        
        // Getters and Setters
        public String getMealPlan() { return mealPlan; }
        public void setMealPlan(String mealPlan) { this.mealPlan = mealPlan; }
        
        public String getMealPlanCode() { return mealPlanCode; }
        public void setMealPlanCode(String mealPlanCode) { this.mealPlanCode = mealPlanCode; }
        
        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
        
        public BigDecimal getRate() { return rate; }
        public void setRate(BigDecimal rate) { this.rate = rate; }
        
        public BigDecimal getTotalRate() { return totalRate; }
        public void setTotalRate(BigDecimal totalRate) { this.totalRate = totalRate; }
        
        public BigDecimal getRateBeforeTax() { return rateBeforeTax; }
        public void setRateBeforeTax(BigDecimal rateBeforeTax) { this.rateBeforeTax = rateBeforeTax; }
        
        public BigDecimal getRecommendedRetailPrice() { return recommendedRetailPrice; }
        public void setRecommendedRetailPrice(BigDecimal recommendedRetailPrice) { this.recommendedRetailPrice = recommendedRetailPrice; }
        
        public String getRoomStatus() { return roomStatus; }
        public void setRoomStatus(String roomStatus) { this.roomStatus = roomStatus; }
        
        public boolean isNonRefundable() { return nonRefundable; }
        public void setNonRefundable(boolean nonRefundable) { this.nonRefundable = nonRefundable; }
        
        public String getContractLabel() { return contractLabel; }
        public void setContractLabel(String contractLabel) { this.contractLabel = contractLabel; }
        
        public String getContractTokenId() { return contractTokenId; }
        public void setContractTokenId(String contractTokenId) { this.contractTokenId = contractTokenId; }
        
        public List<IwtxCancellationPolicy> getCancellationPolicies() { return cancellationPolicies; }
        public void setCancellationPolicies(List<IwtxCancellationPolicy> cancellationPolicies) { this.cancellationPolicies = cancellationPolicies; }
    }
    
    /**
     * Cancellation policy for rate options
     */
    public static class IwtxCancellationPolicy {
        @JsonProperty("fromDate")
        private String fromDate;
        
        @JsonProperty("toDate")
        private String toDate;
        
        @JsonProperty("percentOrAmount")
        private String percentOrAmount;
        
        @JsonProperty("value")
        private BigDecimal value;
        
        // Constructors
        public IwtxCancellationPolicy() {}
        
        public IwtxCancellationPolicy(String fromDate, String toDate, String percentOrAmount, BigDecimal value) {
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.percentOrAmount = percentOrAmount;
            this.value = value;
        }
        
        // Getters and Setters
        public String getFromDate() { return fromDate; }
        public void setFromDate(String fromDate) { this.fromDate = fromDate; }
        
        public String getToDate() { return toDate; }
        public void setToDate(String toDate) { this.toDate = toDate; }
        
        public String getPercentOrAmount() { return percentOrAmount; }
        public void setPercentOrAmount(String percentOrAmount) { this.percentOrAmount = percentOrAmount; }
        
        public BigDecimal getValue() { return value; }
        public void setValue(BigDecimal value) { this.value = value; }
    }
}
