package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO representing a room option in the search response
 */
public class RoomResponse {

    private Integer roomNo;
    private String roomType;
    private String roomTypeCode;
    private String mealPlan;
    private String mealPlanCode;
    private String currency;
    private BigDecimal rate;
    private BigDecimal totalRate;
    private BigDecimal rateBeforeTax;
    private BigDecimal recommendedRetailPrice;
    private String roomStatus;
    private boolean nonRefundable;
    private String contractLabel;
    private List<CancellationPolicy> cancellationPolicies;

    // Constructors
    public RoomResponse() {}

    public RoomResponse(Integer roomNo, String roomType, String roomTypeCode, String mealPlan,
                       String mealPlanCode, String currency, BigDecimal rate, BigDecimal totalRate,
                       BigDecimal rateBeforeTax, BigDecimal recommendedRetailPrice, String roomStatus,
                       boolean nonRefundable, String contractLabel, List<CancellationPolicy> cancellationPolicies) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomTypeCode = roomTypeCode;
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
    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
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

    @Override
    public String toString() {
        return "RoomResponse{" +
                "roomNo=" + roomNo +
                ", roomType='" + roomType + '\'' +
                ", roomTypeCode='" + roomTypeCode + '\'' +
                ", mealPlan='" + mealPlan + '\'' +
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
