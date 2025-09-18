package com.choosenfly.hotelbookingsystem.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelRoomTypeDTO {

    private Long hotelRoomTypeId;
    private Long hotelRoomCategoryId; // Reference to HotelRoomCategory
    private Long hotelId;
    private Long roomTypeId; // Reference to MasterRoomType

    public Long getHotelRoomTypeId() {
        return hotelRoomTypeId;
    }

    public void setHotelRoomTypeId(Long hotelRoomTypeId) {
        this.hotelRoomTypeId = hotelRoomTypeId;
    }

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

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public String toString() {
        return "HotelRoomTypeDTO [hotelRoomTypeId=" + hotelRoomTypeId + ", hotelRoomCategoryId=" + hotelRoomCategoryId
                + ", hotelId=" + hotelId + ", roomTypeId=" + roomTypeId + "]";
    }
}
