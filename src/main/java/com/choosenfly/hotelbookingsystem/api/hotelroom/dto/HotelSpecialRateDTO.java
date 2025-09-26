package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import java.math.BigDecimal;

public class HotelSpecialRateDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private BigDecimal rate;
    private String rate_date;
    private String validity_from;
    private String validity_to;
    private String currency;
    private Integer priority;
    private String exclude_country;

    // Constructors
    public HotelSpecialRateDTO() {}

    public HotelSpecialRateDTO(Long hotel_id, Long hotel_room_category_id, BigDecimal rate, 
                              String rate_date, String validity_from, String validity_to, 
                              String currency, Integer priority, String exclude_country) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.rate = rate;
        this.rate_date = rate_date;
        this.validity_from = validity_from;
        this.validity_to = validity_to;
        this.currency = currency;
        this.priority = priority;
        this.exclude_country = exclude_country;
    }

    // Getters and Setters
    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Long getHotel_room_category_id() {
        return hotel_room_category_id;
    }

    public void setHotel_room_category_id(Long hotel_room_category_id) {
        this.hotel_room_category_id = hotel_room_category_id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRate_date() {
        return rate_date;
    }

    public void setRate_date(String rate_date) {
        this.rate_date = rate_date;
    }

    public String getValidity_from() {
        return validity_from;
    }

    public void setValidity_from(String validity_from) {
        this.validity_from = validity_from;
    }

    public String getValidity_to() {
        return validity_to;
    }

    public void setValidity_to(String validity_to) {
        this.validity_to = validity_to;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getExclude_country() {
        return exclude_country;
    }

    public void setExclude_country(String exclude_country) {
        this.exclude_country = exclude_country;
    }
}
