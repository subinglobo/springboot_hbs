package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import java.math.BigDecimal;

public class HotelMinimumLengthDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private Integer minimum_days;
    private String validity_from;
    private String validity_to;

    // Constructors
    public HotelMinimumLengthDTO() {}

    public HotelMinimumLengthDTO(Long hotel_id, Long hotel_room_category_id, Integer minimum_days, String validity_from, String validity_to) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.minimum_days = minimum_days;
        this.validity_from = validity_from;
        this.validity_to = validity_to;
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

    public Integer getMinimum_days() {
        return minimum_days;
    }

    public void setMinimum_days(Integer minimum_days) {
        this.minimum_days = minimum_days;
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
}
