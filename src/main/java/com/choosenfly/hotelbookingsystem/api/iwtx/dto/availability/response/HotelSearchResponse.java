package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotelSearchResponse")
public class HotelSearchResponse {
    
    private ProfileResponse profile;
    private Hotels hotels;
    
    @XmlElement(name = "Profile")
    public ProfileResponse getProfile() {
        return profile;
    }
    
    public void setProfile(ProfileResponse profile) {
        this.profile = profile;
    }
    
    @XmlElement(name = "Hotels")
    public Hotels getHotels() {
        return hotels;
    }
    
    public void setHotels(Hotels hotels) {
        this.hotels = hotels;
    }
}
