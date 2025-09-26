package com.choosenfly.hotelbookingsystem.api.x3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "iwtx_new_hotelslist_feed")
public class X3Hotels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iwtxnewhotelfeedid")
    private Long iwtxNewHotelFeedId;

    @Column(name = "iwtx_code")
    private String iwtxCode;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "zone")
    private String zone;

    @Column(name = "id")
    private String id;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "name")
    private String name;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "address")
    private String address;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "isdeleted")
    private Boolean isDeleted;

    @Column(name = "hotelimage", length = 1000)
    private String hotelImage;

    @Column(name = "starcategory", length = 100)
    private String starCategory;

    // Constructors
    public X3Hotels() {}

    public X3Hotels(String iwtxCode, String cityCode, String countryCode, String zone, String id,
                   String supplierName, String supplierCode, String sourceId, String name,
                   String brandName, String hotelName, String longitude, String latitude,
                   String address, String postCode, String stateName, String stateCode,
                   String propertyType, String phone, String website, ZonedDateTime createdDate,
                   Boolean isDeleted, String hotelImage, String starCategory) {
        this.iwtxCode = iwtxCode;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.zone = zone;
        this.id = id;
        this.supplierName = supplierName;
        this.supplierCode = supplierCode;
        this.sourceId = sourceId;
        this.name = name;
        this.brandName = brandName;
        this.hotelName = hotelName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.postCode = postCode;
        this.stateName = stateName;
        this.stateCode = stateCode;
        this.propertyType = propertyType;
        this.phone = phone;
        this.website = website;
        this.createdDate = createdDate;
        this.isDeleted = isDeleted;
        this.hotelImage = hotelImage;
        this.starCategory = starCategory;
    }

    // Getters and Setters
    public Long getIwtxNewHotelFeedId() {
        return iwtxNewHotelFeedId;
    }

    public void setIwtxNewHotelFeedId(Long iwtxNewHotelFeedId) {
        this.iwtxNewHotelFeedId = iwtxNewHotelFeedId;
    }

    public String getIwtxCode() {
        return iwtxCode;
    }

    public void setIwtxCode(String iwtxCode) {
        this.iwtxCode = iwtxCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getStarCategory() {
        return starCategory;
    }

    public void setStarCategory(String starCategory) {
        this.starCategory = starCategory;
    }

    @Override
    public String toString() {
        return "X3Hotels [iwtxNewHotelFeedId=" + iwtxNewHotelFeedId + ", iwtxCode=" + iwtxCode + 
               ", cityCode=" + cityCode + ", countryCode=" + countryCode + ", zone=" + zone + 
               ", id=" + id + ", supplierName=" + supplierName + ", supplierCode=" + supplierCode + 
               ", sourceId=" + sourceId + ", name=" + name + ", brandName=" + brandName + 
               ", hotelName=" + hotelName + ", longitude=" + longitude + ", latitude=" + latitude + 
               ", address=" + address + ", postCode=" + postCode + ", stateName=" + stateName + 
               ", stateCode=" + stateCode + ", propertyType=" + propertyType + ", phone=" + phone + 
               ", website=" + website + ", createdDate=" + createdDate + ", isDeleted=" + isDeleted + 
               ", hotelImage=" + hotelImage + ", starCategory=" + starCategory + "]";
    }
}
