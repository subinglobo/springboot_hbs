package com.choosenfly.hotelbookingsystem.api.x3.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hotel")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class HotelX3  {
	
	@XmlElement
	private int SourceId;
	@XmlElement
	private int HotelId;
	@XmlElement
	private String HotelName;
	@XmlElement
	private String PreferredStatus;
	@XmlElement
	private String PropertyType;
	@XmlElement
	private int StarRating;
	@XmlElement
	private GeoLocationX3 GeoLocation;
	@XmlElement
	private String Chain;
	@XmlElement
	private String HotelCode;
	@XmlElement
	private String City;
	@XmlElement
	private RoomTypeDetailsX3 RoomTypeDetails;
	@XmlElement
	private int StartDate;
	@XmlElement
	private int EndDate;
	@XmlElement
	private String Country;
	@XmlElement
	private Object Restriction;
	
	public int getSourceId() {
		return SourceId;
	}
	
	public void setSourceId(int sourceId) {
		SourceId = sourceId;
	}
	
	public int getHotelId() {
		return HotelId;
	}
	

	public void setHotelId(int hotelId) {
		HotelId = hotelId;
	}
	
	public String getHotelName() {
		return HotelName;
	}
	
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	
	public String getPreferredStatus() {
		return PreferredStatus;
	}
	
	public void setPreferredStatus(String preferredStatus) {
		PreferredStatus = preferredStatus;
	}
	
	public String getPropertyType() {
		return PropertyType;
	}
	
	public void setPropertyType(String propertyType) {
		PropertyType = propertyType;
	}

	public int getStarRating() {
		return StarRating;
	}

	public void setStarRating(int starRating) {
		StarRating = starRating;
	}
	
	public GeoLocationX3 getGeoLocation() {
		return GeoLocation;
	}
	
	public void setGeoLocation(GeoLocationX3 geoLocation) {
		GeoLocation = geoLocation;
	}
	
	public String getChain() {
		return Chain;
	}
	
	public void setChain(String chain) {
		Chain = chain;
	}
	
	public String getHotelCode() {
		return HotelCode;
	}
	
	public void setHotelCode(String hotelCode) {
		HotelCode = hotelCode;
	}
	
	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}
	
	public RoomTypeDetailsX3 getRoomTypeDetails() {
		return RoomTypeDetails;
	}
	
	public void setRoomTypeDetails(RoomTypeDetailsX3 roomTypeDetails) {
		RoomTypeDetails = roomTypeDetails;
	}
	
	public int getStartDate() {
		return StartDate;
	}
	
	public void setStartDate(int startDate) {
		StartDate = startDate;
	}
	
	public int getEndDate() {
		return EndDate;
	}
	
	public void setEndDate(int endDate) {
		EndDate = endDate;
	}
	
	public String getCountry() {
		return Country;
	}
	
	public void setCountry(String country) {
		Country = country;
	}
	
	public Object getRestriction() {
		return Restriction;
	}
	
	public void setRestriction(Object restriction) {
		Restriction = restriction;
	}

	@Override
	public String toString() {
		return "HotelX3 [SourceId=" + SourceId + ", HotelId=" + HotelId + ", HotelName=" + HotelName
				+ ", PreferredStatus=" + PreferredStatus + ", PropertyType=" + PropertyType + ", StarRating="
				+ StarRating + ", GeoLocation=" + GeoLocation + ", Chain=" + Chain + ", HotelCode=" + HotelCode
				+ ", City=" + City + ", RoomTypeDetails=" + RoomTypeDetails + ", StartDate=" + StartDate + ", EndDate="
				+ EndDate + ", Country=" + Country + ", Restriction=" + Restriction + "]";
	}
	
	

}
