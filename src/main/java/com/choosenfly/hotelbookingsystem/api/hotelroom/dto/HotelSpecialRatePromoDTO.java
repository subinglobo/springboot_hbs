package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import java.math.BigDecimal;

public class HotelSpecialRatePromoDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private String promo_name;
    private String promo_type;
    private BigDecimal promo_value;
    private String validity_from;
    private String validity_to;

    // Constructors
    public HotelSpecialRatePromoDTO() {}

    public HotelSpecialRatePromoDTO(Long hotel_id, Long hotel_room_category_id, String promo_name, 
                                   String promo_type, BigDecimal promo_value, String validity_from, 
                                   String validity_to) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.promo_name = promo_name;
        this.promo_type = promo_type;
        this.promo_value = promo_value;
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

    public String getPromo_name() {
        return promo_name;
    }

    public void setPromo_name(String promo_name) {
        this.promo_name = promo_name;
    }

    public String getPromo_type() {
        return promo_type;
    }

    public void setPromo_type(String promo_type) {
        this.promo_type = promo_type;
    }

    public BigDecimal getPromo_value() {
        return promo_value;
    }

    public void setPromo_value(BigDecimal promo_value) {
        this.promo_value = promo_value;
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
