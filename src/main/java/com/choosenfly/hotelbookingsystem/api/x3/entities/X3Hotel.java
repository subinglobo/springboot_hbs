package com.choosenfly.hotelbookingsystem.api.x3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "x3_hotels")
public class X3Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hotel_code")
    private String hotelCode;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "images_url")
    private String imagesUrl;

    @Column(name = "star_rating")
    private String starRating;

    @Column(name = "hotel_address")
    private String hotelAddress;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "city_name")
    private String cityName;

    // Constructors
    public X3Hotel() {}

    public X3Hotel(String hotelCode, String hotelName, String imagesUrl, String starRating, 
                   String hotelAddress, Integer cityId, Integer countryId, String cityName) {
        this.hotelCode = hotelCode;
        this.hotelName = hotelName;
        this.imagesUrl = imagesUrl;
        this.starRating = starRating;
        this.hotelAddress = hotelAddress;
        this.cityId = cityId;
        this.countryId = countryId;
        this.cityName = cityName;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "X3Hotel [id=" + id + ", hotelCode=" + hotelCode + ", hotelName=" + hotelName + 
               ", imagesUrl=" + imagesUrl + ", starRating=" + starRating + ", hotelAddress=" + hotelAddress + 
               ", cityId=" + cityId + ", countryId=" + countryId + ", cityName=" + cityName + "]";
    }
}
