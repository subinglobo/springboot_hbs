package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;

public class Rooms {
    
    private List<RoomResponse> room;
    
    @XmlElement(name = "Room")
    public List<RoomResponse> getRoom() {
        return room;
    }
    
    public void setRoom(List<RoomResponse> room) {
        this.room = room;
    }
}
