package com.choosenfly.hotelbookingsystem.dto.iwtx;

public class HotelInfoIwtx {

	private String hotelCode;
	private String hotelName;
	private String hotelImage;
	private Integer starRating;
	private String hotelAddress;
	
	
	

	public HotelInfoIwtx(String hotelCode, String hotelName, String hotelImage, Integer starRating,
			String hotelAddress) {
		super();
		this.hotelCode = hotelCode;
		this.hotelName = hotelName;
		this.hotelImage = hotelImage;
		this.starRating = starRating;
		this.hotelAddress = hotelAddress;
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

	public String getHotelImage() {
		return hotelImage;
	}

	public void setHotelImage(String hotelImage) {
		this.hotelImage = hotelImage;
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

	@Override
	public String toString() {
		return "HotelInfo [hotelCode=" + hotelCode + ", hotelName=" + hotelName + ", hotelImage=" + hotelImage
				+ ", starRating=" + starRating + ", hotelAddress=" + hotelAddress + "]";
	}

}
