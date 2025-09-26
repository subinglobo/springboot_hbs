package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

public class HotelOccupancyDTO {
    private Long hotel_id;
    private Long hotel_room_category_id;
    private Integer adults;
    private Integer children;
    private Integer max_adults;
    private Integer max_children;
    private String validity_from;
    private String validity_to;

    // Constructors
    public HotelOccupancyDTO() {}

    public HotelOccupancyDTO(Long hotel_id, Long hotel_room_category_id, Integer adults, Integer children, 
                            Integer max_adults, Integer max_children, String validity_from, String validity_to) {
        this.hotel_id = hotel_id;
        this.hotel_room_category_id = hotel_room_category_id;
        this.adults = adults;
        this.children = children;
        this.max_adults = max_adults;
        this.max_children = max_children;
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

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Integer getMax_adults() {
        return max_adults;
    }

    public void setMax_adults(Integer max_adults) {
        this.max_adults = max_adults;
    }

    public Integer getMax_children() {
        return max_children;
    }

    public void setMax_children(Integer max_children) {
        this.max_children = max_children;
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
