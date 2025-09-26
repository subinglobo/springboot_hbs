package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import java.math.BigDecimal;
import java.util.List;

public class FilterHotelDTO {
    
    private Long hotel_id;
    private String currency_value;
    private Long state_id;
    private Long country_id;
    private String hotel_code;
    private String hotel_name;
    private String hotel_category;
    private String hotelType;
    private Long hotel_type_id;
    private Integer childChargeableAgeMax;
    private Integer childComAgeMax;
    private String hotel_details;
    private String imageName;
    private BigDecimal markup;
    private String markupType;
    
    // Additional fields for room details and rates
    private List<FilterHotelRoomsDTO> rooms;
    private List<RoomAvailableDTO> availability;
    private List<RoomStopSaleDTO> stopSales;
    private FilterHotelWeekDaysDTO weekDays;
    private List<Object> policies;
    private List<Object> events;
    private List<Object> rates;
    private List<Object> promotions;
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getCurrency_value() {
		return currency_value;
	}
	public void setCurrency_value(String currency_value) {
		this.currency_value = currency_value;
	}
	public Long getState_id() {
		return state_id;
	}
	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}
	public Long getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Long country_id) {
		this.country_id = country_id;
	}
	public String getHotel_code() {
		return hotel_code;
	}
	public void setHotel_code(String hotel_code) {
		this.hotel_code = hotel_code;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getHotel_category() {
		return hotel_category;
	}
	public void setHotel_category(String hotel_category) {
		this.hotel_category = hotel_category;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public Long getHotel_type_id() {
		return hotel_type_id;
	}
	public void setHotel_type_id(Long hotel_type_id) {
		this.hotel_type_id = hotel_type_id;
	}
	public Integer getChildChargeableAgeMax() {
		return childChargeableAgeMax;
	}
	public void setChildChargeableAgeMax(Integer childChargeableAgeMax) {
		this.childChargeableAgeMax = childChargeableAgeMax;
	}
	public Integer getChildComAgeMax() {
		return childComAgeMax;
	}
	public void setChildComAgeMax(Integer childComAgeMax) {
		this.childComAgeMax = childComAgeMax;
	}
	public String getHotel_details() {
		return hotel_details;
	}
	public void setHotel_details(String hotel_details) {
		this.hotel_details = hotel_details;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public BigDecimal getMarkup() {
		return markup;
	}
	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}
	public String getMarkupType() {
		return markupType;
	}
	public void setMarkupType(String markupType) {
		this.markupType = markupType;
	}
	public List<FilterHotelRoomsDTO> getRooms() {
		return rooms;
	}
	public void setRooms(List<FilterHotelRoomsDTO> rooms) {
		this.rooms = rooms;
	}
	public List<RoomAvailableDTO> getAvailability() {
		return availability;
	}
	public void setAvailability(List<RoomAvailableDTO> availability) {
		this.availability = availability;
	}
	public List<RoomStopSaleDTO> getStopSales() {
		return stopSales;
	}
	public void setStopSales(List<RoomStopSaleDTO> stopSales) {
		this.stopSales = stopSales;
	}
	public FilterHotelWeekDaysDTO getWeekDays() {
		return weekDays;
	}
	public void setWeekDays(FilterHotelWeekDaysDTO weekDays) {
		this.weekDays = weekDays;
	}
	public List<Object> getPolicies() {
		return policies;
	}
	public void setPolicies(List<Object> policies) {
		this.policies = policies;
	}
	public List<Object> getEvents() {
		return events;
	}
	public void setEvents(List<Object> events) {
		this.events = events;
	}
	public List<Object> getRates() {
		return rates;
	}
	public void setRates(List<Object> rates) {
		this.rates = rates;
	}
	public List<Object> getPromotions() {
		return promotions;
	}
	public void setPromotions(List<Object> promotions) {
		this.promotions = promotions;
	}
	@Override
	public String toString() {
		return "FilterHotelDTO [hotel_id=" + hotel_id + ", currency_value=" + currency_value + ", state_id=" + state_id
				+ ", country_id=" + country_id + ", hotel_code=" + hotel_code + ", hotel_name=" + hotel_name
				+ ", hotel_category=" + hotel_category + ", hotelType=" + hotelType + ", hotel_type_id=" + hotel_type_id
				+ ", childChargeableAgeMax=" + childChargeableAgeMax + ", childComAgeMax=" + childComAgeMax
				+ ", hotel_details=" + hotel_details + ", imageName=" + imageName + ", markup=" + markup
				+ ", markupType=" + markupType + ", rooms=" + rooms + ", availability=" + availability + ", stopSales="
				+ stopSales + ", weekDays=" + weekDays + ", policies=" + policies + ", events=" + events + ", rates="
				+ rates + ", promotions=" + promotions + "]";
	}
    
    
}
