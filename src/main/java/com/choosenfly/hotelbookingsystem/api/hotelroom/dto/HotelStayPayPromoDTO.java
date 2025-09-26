package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import java.math.BigDecimal;

public class HotelStayPayPromoDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private String promo_name;
    private Integer stay_nights;
    private Integer pay_nights;
    private String validity_from;
    private String validity_to;
    private String exclude_country;
    private Integer minimum_days;

    // Constructors
    public HotelStayPayPromoDTO() {}

    public HotelStayPayPromoDTO(Long hotel_id, Long hotel_room_category_id, String promo_name, 
                               Integer stay_nights, Integer pay_nights, String validity_from, 
                               String validity_to, String exclude_country, Integer minimum_days) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.promo_name = promo_name;
        this.stay_nights = stay_nights;
        this.pay_nights = pay_nights;
        this.validity_from = validity_from;
        this.validity_to = validity_to;
        this.exclude_country = exclude_country;
        this.minimum_days = minimum_days;
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

    public Integer getStay_nights() {
        return stay_nights;
    }

    public void setStay_nights(Integer stay_nights) {
        this.stay_nights = stay_nights;
    }

    public Integer getPay_nights() {
        return pay_nights;
    }

    public void setPay_nights(Integer pay_nights) {
        this.pay_nights = pay_nights;
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

    public String getExclude_country() {
        return exclude_country;
    }

    public void setExclude_country(String exclude_country) {
        this.exclude_country = exclude_country;
    }

    public Integer getMinimum_days() {
        return minimum_days;
    }

    public void setMinimum_days(Integer minimum_days) {
        this.minimum_days = minimum_days;
    }
}
