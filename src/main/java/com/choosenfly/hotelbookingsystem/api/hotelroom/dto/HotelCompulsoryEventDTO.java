package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import java.math.BigDecimal;

public class HotelCompulsoryEventDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private String event_name;
    private BigDecimal event_rate;
    private String event_date;
    private String validity_from;
    private String validity_to;
    private String currency;

    // Constructors
    public HotelCompulsoryEventDTO() {}

    public HotelCompulsoryEventDTO(Long hotel_id, Long hotel_room_category_id, String event_name, 
                                  BigDecimal event_rate, String event_date, String validity_from, 
                                  String validity_to, String currency) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.event_name = event_name;
        this.event_rate = event_rate;
        this.event_date = event_date;
        this.validity_from = validity_from;
        this.validity_to = validity_to;
        this.currency = currency;
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

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public BigDecimal getEvent_rate() {
        return event_rate;
    }

    public void setEvent_rate(BigDecimal event_rate) {
        this.event_rate = event_rate;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
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
}
