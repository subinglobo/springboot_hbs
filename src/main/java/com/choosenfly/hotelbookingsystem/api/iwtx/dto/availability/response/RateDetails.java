package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class RateDetails {
    
    private String rate;
    
    @XmlElement(name = "Rate")
    public String getRate() {
        return rate;
    }
    
    public void setRate(String rate) {
        this.rate = rate;
    }
}
