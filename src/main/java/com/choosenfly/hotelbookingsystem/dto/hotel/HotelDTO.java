package com.choosenfly.hotelbookingsystem.dto.hotel;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HotelDTO {
   
	private Long id;

    @NotBlank(message = "Hotel name is required")
    private String hotelName;

    @NotNull(message = "Currency ID is required")
    private Long hotelCurrencyId;

    @NotNull(message = "Hotel Category is required")
    private Long hotelCategoryId;
   
    @NotNull(message = "Hotel Type is required")
    private Long hotelTypeId;
   
    @NotNull(message = "Markup is required")
    private Long markupTypeId;
  
    private String image360;

    private String hotelDescription;

    @NotNull(message = "Minimum child complimentary age is required")
    private Integer childComAgeMin;

    @NotNull(message = "Maximum child complimentary age is required")
    private Integer childComAgeMax;

    @NotNull(message = "Minimum child chargeable age is required")
    private Integer childChargeableAgeMin;

    @NotNull(message = "Maximum child chargeable age is required")
    private Integer childChargeableAgeMax;

    @NotNull(message = "Region ID is required")
    private Long regionId;

    @NotNull(message = "Country ID is required")
    private Long countryId;

    @NotNull(message = "State ID is required")
    private Long stateId;

    @NotNull(message = "Place ID is required")
    private Long placeId;

    private String address;
    private String zipcode;
    private String latitude;
    private String longitude;
    private Boolean isDeleted;
    private List<HotelContactDetailsDTO> contactDetails;
    private List<HotelBankDetailsDTO> bankDetails;
    private HotelWeekDaysDTO weekDays;
    private List<HotelRoomDTO> rooms;
    private List<HotelTermsAndConditionsDTO> termsAndConditions;
    private List<Long> amenityIds;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public Long getHotelCurrencyId() {
		return hotelCurrencyId;
	}
	public void setHotelCurrencyId(Long hotelCurrencyId) {
		this.hotelCurrencyId = hotelCurrencyId;
	}
	public Long getHotelCategoryId() {
		return hotelCategoryId;
	}
	public void setHotelCategoryId(Long hotelCategoryId) {
		this.hotelCategoryId = hotelCategoryId;
	}
	public Long getHotelTypeId() {
		return hotelTypeId;
	}
	public void setHotelTypeId(Long hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
	}
	
	public String getImage360() {
		return image360;
	}
	public void setImage360(String image360) {
		this.image360 = image360;
	}
	public String getHotelDescription() {
		return hotelDescription;
	}
	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}
	public Integer getChildComAgeMin() {
		return childComAgeMin;
	}
	public void setChildComAgeMin(Integer childComAgeMin) {
		this.childComAgeMin = childComAgeMin;
	}
	public Integer getChildComAgeMax() {
		return childComAgeMax;
	}
	public void setChildComAgeMax(Integer childComAgeMax) {
		this.childComAgeMax = childComAgeMax;
	}
	public Integer getChildChargeableAgeMin() {
		return childChargeableAgeMin;
	}
	public void setChildChargeableAgeMin(Integer childChargeableAgeMin) {
		this.childChargeableAgeMin = childChargeableAgeMin;
	}
	public Integer getChildChargeableAgeMax() {
		return childChargeableAgeMax;
	}
	public void setChildChargeableAgeMax(Integer childChargeableAgeMax) {
		this.childChargeableAgeMax = childChargeableAgeMax;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<HotelContactDetailsDTO> getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(List<HotelContactDetailsDTO> contactDetails) {
		this.contactDetails = contactDetails;
	}
	public List<HotelBankDetailsDTO> getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(List<HotelBankDetailsDTO> bankDetails) {
		this.bankDetails = bankDetails;
	}
	public HotelWeekDaysDTO getWeekDays() {
		return weekDays;
	}
	public void setWeekDays(HotelWeekDaysDTO weekDays) {
		this.weekDays = weekDays;
	}
	public List<HotelRoomDTO> getRooms() {
		return rooms;
	}
	public void setRooms(List<HotelRoomDTO> rooms) {
		this.rooms = rooms;
	}
	public List<HotelTermsAndConditionsDTO> getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(List<HotelTermsAndConditionsDTO> termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public List<Long> getAmenityIds() {
		return amenityIds;
	}
	public void setAmenityIds(List<Long> amenityIds) {
		this.amenityIds = amenityIds;
	}
	
	
	public Long getMarkupTypeId() {
		return markupTypeId;
	}
	public void setMarkupTypeId(Long markupTypeId) {
		this.markupTypeId = markupTypeId;
	}
	
	@Override
	public String toString() {
		return "HotelDTO [id=" + id + ", hotelName=" + hotelName + ", hotelCurrencyId=" + hotelCurrencyId
				+ ", hotelCategoryId=" + hotelCategoryId + ", hotelTypeId=" + hotelTypeId + ", markupTypeId="
				+ markupTypeId + ", image360=" + image360 + ", hotelDescription=" + hotelDescription
				+ ", childComAgeMin=" + childComAgeMin + ", childComAgeMax=" + childComAgeMax
				+ ", childChargeableAgeMin=" + childChargeableAgeMin + ", childChargeableAgeMax="
				+ childChargeableAgeMax + ", regionId=" + regionId + ", countryId=" + countryId + ", stateId=" + stateId
				+ ", placeId=" + placeId + ", address=" + address + ", zipcode=" + zipcode + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", isDeleted=" + isDeleted + ", contactDetails=" + contactDetails
				+ ", bankDetails=" + bankDetails + ", weekDays=" + weekDays + ", rooms=" + rooms
				+ ", termsAndConditions=" + termsAndConditions + ", amenityIds=" + amenityIds + "]";
	}
	
	

	
    
}