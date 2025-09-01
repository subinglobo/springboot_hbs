package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class GeoLocation {
    
    private String longitude;
    private String latitude;
    
    @XmlElement(name = "Longitude")
    public String getLongitude() {
        return longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    @XmlElement(name = "Latitude")
    public String getLatitude() {
        return latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
