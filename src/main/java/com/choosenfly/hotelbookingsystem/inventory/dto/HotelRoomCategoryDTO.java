package com.choosenfly.hotelbookingsystem.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelRoomCategoryDTO {

    private Long hotelRoomCategoryId;
    private Long hotelId;
    private Long roomCategoryId; // Reference to MasterRoomCategory
    private String name;
    private String noOfRooms;

    public Long getHotelRoomCategoryId() {
        return hotelRoomCategoryId;
    }

    public void setHotelRoomCategoryId(Long hotelRoomCategoryId) {
        this.hotelRoomCategoryId = hotelRoomCategoryId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(Long roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    @Override
    public String toString() {
        return "HotelRoomCategoryDTO [hotelRoomCategoryId=" + hotelRoomCategoryId + ", hotelId=" + hotelId
                + ", roomCategoryId=" + roomCategoryId + ", name=" + name + ", noOfRooms=" + noOfRooms + "]";
    }
}
