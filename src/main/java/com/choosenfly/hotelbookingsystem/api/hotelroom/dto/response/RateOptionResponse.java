package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO representing a rate option within a room category
 */
public class RateOptionResponse {

    private String roomCategory;
    private String roomTypeDescription;
    private String mealPlan;
    private String mealPlanCode;
    private String currency;
    private BigDecimal rate;
    private BigDecimal totalRate;
    private BigDecimal rateBeforeTax;
    private BigDecimal recommendedRetailPrice;
    private String roomStatus;
    private boolean nonRefundable;
    private String refundStatus;
    private String contractLabel;
    private List<CancellationPolicy> cancellationPolicies;

    // Constructors
    public RateOptionResponse() {}

    public RateOptionResponse(String mealPlan, String mealPlanCode, String currency,
                             BigDecimal rate, BigDecimal totalRate, BigDecimal rateBeforeTax,
                             BigDecimal recommendedRetailPrice, String roomStatus,
                             boolean nonRefundable, String contractLabel,
                             List<CancellationPolicy> cancellationPolicies) {
        this.mealPlan = mealPlan;
        this.mealPlanCode = mealPlanCode;
        this.currency = currency;
        this.rate = rate;
        this.totalRate = totalRate;
        this.rateBeforeTax = rateBeforeTax;
        this.recommendedRetailPrice = recommendedRetailPrice;
        this.roomStatus = roomStatus;
        this.nonRefundable = nonRefundable;
        this.contractLabel = contractLabel;
        this.cancellationPolicies = cancellationPolicies;
    }

    // Getters and Setters
    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getRoomTypeDescription() {
        return roomTypeDescription;
    }

    public void setRoomTypeDescription(String roomTypeDescription) {
        this.roomTypeDescription = roomTypeDescription;
    }

    public String getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(String mealPlan) {
        this.mealPlan = mealPlan;
    }

    public String getMealPlanCode() {
        return mealPlanCode;
    }

    public void setMealPlanCode(String mealPlanCode) {
        this.mealPlanCode = mealPlanCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }

    public BigDecimal getRateBeforeTax() {
        return rateBeforeTax;
    }

    public void setRateBeforeTax(BigDecimal rateBeforeTax) {
        this.rateBeforeTax = rateBeforeTax;
    }

    public BigDecimal getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }

    public void setRecommendedRetailPrice(BigDecimal recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public boolean isNonRefundable() {
        return nonRefundable;
    }

    public void setNonRefundable(boolean nonRefundable) {
        this.nonRefundable = nonRefundable;
    }

    public String getContractLabel() {
        return contractLabel;
    }

    public void setContractLabel(String contractLabel) {
        this.contractLabel = contractLabel;
    }

    public List<CancellationPolicy> getCancellationPolicies() {
        return cancellationPolicies;
    }

    public void setCancellationPolicies(List<CancellationPolicy> cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    @Override
    public String toString() {
        return "RateOptionResponse{" +
                "mealPlan='" + mealPlan + '\'' +
                ", mealPlanCode='" + mealPlanCode + '\'' +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                ", totalRate=" + totalRate +
                ", rateBeforeTax=" + rateBeforeTax +
                ", recommendedRetailPrice=" + recommendedRetailPrice +
                ", roomStatus='" + roomStatus + '\'' +
                ", nonRefundable=" + nonRefundable +
                ", contractLabel='" + contractLabel + '\'' +
                ", cancellationPolicies=" + cancellationPolicies +
                '}';
    }
}
