package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;

public class Messages {
    
    private List<Message> message;
    
    @XmlElement(name = "Message")
    public List<Message> getMessage() {
        return message;
    }
    
    public void setMessage(List<Message> message) {
        this.message = message;
    }
}
