package com.choosenfly.hotelbookingsystem.api.iwtx.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "iwtx_hotels", schema = "public")
public class IwtxHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hotel_code", unique = true, length = 50)
    private String hotelCode;

    @Column(name = "hotel_name", length = 255)
    private String hotelName;

    @Column(name = "city_code", length = 50)
    private String cityCode;

    @Column(name = "city_name", length = 100)
    private String cityName;

    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "star_rating", length = 10)
    private String starRating;

    @Column(name = "latitude", precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "check_in_time", length = 50)
    private String checkInTime;

    @Column(name = "check_out_time", length = 50)
    private String checkOutTime;

    @Column(name = "hotel_address", length = 255)
    private String hotelAddress;

    @Column(name = "hotel_chain", length = 100)
    private String hotelChain;

    @Column(name = "hotel_city", length = 100)
    private String hotelCity;

    @Column(name = "hotel_email", length = 100)
    private String hotelEmail;

    @Column(name = "hotel_phone", length = 50)
    private String hotelPhone;

    @Column(name = "images_url", columnDefinition = "text")
    private String imagesUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getHotelChain() {
		return hotelChain;
	}

	public void setHotelChain(String hotelChain) {
		this.hotelChain = hotelChain;
	}

	public String getHotelCity() {
		return hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelEmail() {
		return hotelEmail;
	}

	public void setHotelEmail(String hotelEmail) {
		this.hotelEmail = hotelEmail;
	}

	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "IwtxHotel [id=" + id + ", hotelCode=" + hotelCode + ", hotelName=" + hotelName + ", cityCode="
				+ cityCode + ", cityName=" + cityName + ", countryCode=" + countryCode + ", countryId=" + countryId
				+ ", cityId=" + cityId + ", starRating=" + starRating + ", latitude=" + latitude + ", longitude="
				+ longitude + ", isDeleted=" + isDeleted + ", checkInTime=" + checkInTime + ", checkOutTime="
				+ checkOutTime + ", hotelAddress=" + hotelAddress + ", hotelChain=" + hotelChain + ", hotelCity="
				+ hotelCity + ", hotelEmail=" + hotelEmail + ", hotelPhone=" + hotelPhone + ", imagesUrl=" + imagesUrl
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

    // Getters and Setters
    
    
}