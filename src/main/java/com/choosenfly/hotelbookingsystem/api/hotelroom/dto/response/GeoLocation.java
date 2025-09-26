package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response;

import java.math.BigDecimal;

/**
 * DTO representing geographical location coordinates
 */
public class GeoLocation {

    private BigDecimal longitude;
    private BigDecimal latitude;

    // Constructors
    public GeoLocation() {}

    public GeoLocation(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Getters and Setters
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "GeoLocation{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
