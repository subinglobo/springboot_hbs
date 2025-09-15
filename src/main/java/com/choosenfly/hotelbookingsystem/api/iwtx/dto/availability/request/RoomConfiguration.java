package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request;

import jakarta.xml.bind.annotation.XmlElement;

public class RoomConfiguration {
    
    private Room room;
    
    @XmlElement(name = "Room")
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
}
