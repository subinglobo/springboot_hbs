package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

public class HotelDiscountBlockDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private String block_date;
    private String validity_from;
    private String validity_to;

    // Constructors
    public HotelDiscountBlockDTO() {}

    public HotelDiscountBlockDTO(Long hotel_id, Long hotel_room_category_id, String block_date, 
                                String validity_from, String validity_to) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.block_date = block_date;
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

    public String getBlock_date() {
        return block_date;
    }

    public void setBlock_date(String block_date) {
        this.block_date = block_date;
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
