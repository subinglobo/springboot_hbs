package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response;

import java.math.BigDecimal;

/**
 * DTO representing cancellation policy details
 */
public class CancellationPolicy {

    private String fromDate;
    private String toDate;
    private String percentOrAmount;
    private BigDecimal value;
    private String policyText;

    // Constructors
    public CancellationPolicy() {}

    public CancellationPolicy(String fromDate, String toDate, String percentOrAmount, BigDecimal value) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.percentOrAmount = percentOrAmount;
        this.value = value;
    }

    public CancellationPolicy(String fromDate, String toDate, String percentOrAmount, BigDecimal value, String policyText) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.percentOrAmount = percentOrAmount;
        this.value = value;
        this.policyText = policyText;
    }

    // Getters and Setters
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPercentOrAmount() {
        return percentOrAmount;
    }

    public void setPercentOrAmount(String percentOrAmount) {
        this.percentOrAmount = percentOrAmount;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getPolicyText() {
        return policyText;
    }

    public void setPolicyText(String policyText) {
        this.policyText = policyText;
    }

    @Override
    public String toString() {
        return "CancellationPolicy{" +
                "fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", percentOrAmount='" + percentOrAmount + '\'' +
                ", value=" + value +
                '}';
    }
}
