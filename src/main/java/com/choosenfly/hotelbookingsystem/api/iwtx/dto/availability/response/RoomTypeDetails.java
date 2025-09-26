package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class RoomTypeDetails {
    
    private Rooms rooms;
    
    @XmlElement(name = "Rooms")
    public Rooms getRooms() {
        return rooms;
    }
    
    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }
}
