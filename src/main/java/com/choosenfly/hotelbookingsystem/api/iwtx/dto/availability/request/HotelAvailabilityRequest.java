package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Simplified request DTO for hotel availability that doesn't require profile credentials
 * Profile will be automatically populated from application.properties
 */
public class HotelAvailabilityRequest {
    
    @JsonProperty("searchCriteria")
    private SearchCriteria searchCriteria;
    
    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }
    
    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
