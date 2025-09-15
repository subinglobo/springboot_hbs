package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HotelSearchRequest")
public class HotelSearchRequest {
    
    private Profile profile;
    private SearchCriteria searchCriteria;
    
    @XmlElement(name = "Profile")
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    @XmlElement(name = "SearchCriteria")
    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }
    
    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
