package com.choosenfly.hotelbookingsystem.api.iwtx.dto.search.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hotel")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class HotelIwtx  {
	
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
	private GeoLocationIwtx GeoLocation;
	@XmlElement
	private String Chain;
	@XmlElement
	private String HotelCode;
	@XmlElement
	private String City;
	@XmlElement
	private RoomTypeDetailsIwtx RoomTypeDetails;
	@XmlElement
	private int StartDate;
	@XmlElement
	private int EndDate;
	@XmlElement
	private String Country;
	@XmlElement
	private Object Restriction;
	
//	@JsonProperty("SourceId")
//	@XmlAttribute(name = "SourceId")
	public int getSourceId() {
		return SourceId;
	}
	
	public void setSourceId(int sourceId) {
		SourceId = sourceId;
	}
	
//	@JsonProperty("HotelId")
//	@XmlElement(name ="HotelId")
	public int getHotelId() {
		return HotelId;
	}
	

	public void setHotelId(int hotelId) {
		HotelId = hotelId;
	}
	
//	@JsonProperty("HotelName")
///	@XmlAttribute(name = "HotelName")
	public String getHotelName() {
		return HotelName;
	}
	
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	
//	@JsonProperty("PreferredStatus")
//	@XmlAttribute(name = "PreferredStatus")
	public String getPreferredStatus() {
		return PreferredStatus;
	}
	
	public void setPreferredStatus(String preferredStatus) {
		PreferredStatus = preferredStatus;
	}
	
//	@JsonProperty("PropertyType")
//	@XmlAttribute(name = "PropertyType")
	public String getPropertyType() {
		return PropertyType;
	}
	
	public void setPropertyType(String propertyType) {
		PropertyType = propertyType;
	}

//	@JsonProperty("StarRating")
//	@XmlAttribute(name = "StarRating")
	public int getStarRating() {
		return StarRating;
	}

	public void setStarRating(int starRating) {
		StarRating = starRating;
	}
	
//	@JsonProperty("GeoLocation")
//	@XmlAttribute(name = "GeoLocation")
	public GeoLocationIwtx getGeoLocation() {
		return GeoLocation;
	}
	
	public void setGeoLocation(GeoLocationIwtx geoLocation) {
		GeoLocation = geoLocation;
	}
	
//	@JsonProperty("Chain")
	//@XmlAttribute(name = "Chain")
	public String getChain() {
		return Chain;
	}
	
	public void setChain(String chain) {
		Chain = chain;
	}
	
//	@JsonProperty("HotelCode")
//	@XmlAttribute(name = "HotelCode")
	public String getHotelCode() {
		return HotelCode;
	}
	
	public void setHotelCode(String hotelCode) {
		HotelCode = hotelCode;
	}
	
//	@JsonProperty("City")
//	@XmlAttribute(name = "City")
	public String getCity() {
		return City;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
	}
	/**
	 * @return the roomTypeDetails
	 */
//	@JsonProperty("RoomTypeDetails")
//	@XmlAttribute(name = "RoomTypeDetails")
	public RoomTypeDetailsIwtx getRoomTypeDetails() {
		return RoomTypeDetails;
	}
	/**
	 * @param roomTypeDetails the roomTypeDetails to set
	 */
	public void setRoomTypeDetails(RoomTypeDetailsIwtx roomTypeDetails) {
		RoomTypeDetails = roomTypeDetails;
	}
	/**
	 * @return the startDate
	 */
//	@JsonProperty("StartDate")
//	@XmlAttribute(name = "StartDate")
	public int getStartDate() {
		return StartDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(int startDate) {
		StartDate = startDate;
	}
	/**
	 * @return the endDate
	 */
//	@JsonProperty("EndDate")
//	@XmlAttribute(name = "EndDate")
	public int getEndDate() {
		return EndDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(int endDate) {
		EndDate = endDate;
	}
	/**
	 * @return the country
	 */
//	@JsonProperty("Country")
//	@XmlAttribute(name = "Country")
	public String getCountry() {
		return Country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		Country = country;
	}
	/**
	 * @return the restriction
	 */
//	@JsonProperty("Restriction")
//	@XmlAttribute(name = "Restriction")
	public Object getRestriction() {
		return Restriction;
	}
	/**
	 * @param restriction the restriction to set
	 */
	public void setRestriction(Object restriction) {
		Restriction = restriction;
	}

	@Override
	public String toString() {
		return "Hotel [SourceId=" + SourceId + ", HotelId=" + HotelId + ", HotelName=" + HotelName
				+ ", PreferredStatus=" + PreferredStatus + ", PropertyType=" + PropertyType + ", StarRating="
				+ StarRating + ", GeoLocation=" + GeoLocation + ", Chain=" + Chain + ", HotelCode=" + HotelCode
				+ ", City=" + City + ", RoomTypeDetails=" + RoomTypeDetails + ", StartDate=" + StartDate + ", EndDate="
				+ EndDate + ", Country=" + Country + ", Restriction=" + Restriction + "]";
	}
	
	

}
