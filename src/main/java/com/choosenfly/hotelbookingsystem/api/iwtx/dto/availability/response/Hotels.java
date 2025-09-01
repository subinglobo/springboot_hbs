package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;

public class Hotels {
    
    private List<Hotel> hotel;
    
    @XmlElement(name = "Hotel")
    public List<Hotel> getHotel() {
        return hotel;
    }
    
    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }
}
