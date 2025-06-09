package com.choosenfly.hotelbookingsystem.dto.hotel.search;

public class HotelSearchResult {

	 private String hotelName;
	    private String hotelImage;
	    private Double baseRate;
	    private String hotelCode;
	    private Integer starRating;
	    private String hotelAddress;
	    private String apiType;
		public String getHotelName() {
			return hotelName;
		}
		public void setHotelName(String hotelName) {
			this.hotelName = hotelName;
		}
		public String getHotelImage() {
			return hotelImage;
		}
		public void setHotelImage(String hotelImage) {
			this.hotelImage = hotelImage;
		}
		public Double getBaseRate() {
			return baseRate;
		}
		public void setBaseRate(Double baseRate) {
			this.baseRate = baseRate;
		}
		public String getHotelCode() {
			return hotelCode;
		}
		public void setHotelCode(String hotelCode) {
			this.hotelCode = hotelCode;
		}
		public Integer getStarRating() {
			return starRating;
		}
		public void setStarRating(Integer starRating) {
			this.starRating = starRating;
		}
		public String getHotelAddress() {
			return hotelAddress;
		}
		public void setHotelAddress(String hotelAddress) {
			this.hotelAddress = hotelAddress;
		}
		public String getApiType() {
			return apiType;
		}
		public void setApiType(String apiType) {
			this.apiType = apiType;
		}
		@Override
		public String toString() {
			return "HotelSearchResponse [hotelName=" + hotelName + ", hotelImage=" + hotelImage + ", baseRate=" + baseRate
					+ ", hotelCode=" + hotelCode + ", starRating=" + starRating + ", hotelAddress=" + hotelAddress
					+ ", apiType=" + apiType + "]";
		}
	    
}
