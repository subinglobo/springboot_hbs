package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import java.math.BigDecimal;

public class HotelRoomRateDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private BigDecimal rate;
    private String rate_date;
    private String rate_type; // CONTRACT, SPECIAL
    private Integer priority;
    private String currency;
    private Boolean is_refundable;

    // Constructors
    public HotelRoomRateDTO() {}

    public HotelRoomRateDTO(Long hotel_id, Long hotel_room_category_id, BigDecimal rate, 
                           String rate_date, String rate_type, Integer priority, 
                           String currency, Boolean is_refundable) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.rate = rate;
        this.rate_date = rate_date;
        this.rate_type = rate_type;
        this.priority = priority;
        this.currency = currency;
        this.is_refundable = is_refundable;
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

    public String getRate_type() {
        return rate_type;
    }

    public void setRate_type(String rate_type) {
        this.rate_type = rate_type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIs_refundable() {
        return is_refundable;
    }

    public void setIs_refundable(Boolean is_refundable) {
        this.is_refundable = is_refundable;
    }
}
