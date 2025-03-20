package com.choosenfly.hotelbookingsystem.dto.hotel;

public class RoomOccupancyDTO {
	private Long id;
	private Long hotelRoomId;
	private String hotelRoomCategoryName;
	private String occupancyTypeName;
	private Long occupancyTypeId;
	private Integer totalAdult;
	private Integer totalChild;
	private Integer extraAdult;
	private Integer extraChild;

	
	public String getHotelRoomCategoryName() {
		return hotelRoomCategoryName;
	}

	public void setHotelRoomCategoryName(String hotelRoomCategoryName) {
		this.hotelRoomCategoryName = hotelRoomCategoryName;
	}

	public String getOccupancyTypeName() {
		return occupancyTypeName;
	}

	public void setOccupancyTypeName(String occupancyTypeName) {
		this.occupancyTypeName = occupancyTypeName;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelRoomId() {
		return hotelRoomId;
	}

	public void setHotelRoomId(Long hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}

	public Long getOccupancyTypeId() {
		return occupancyTypeId;
	}

	public void setOccupancyTypeId(Long occupancyTypeId) {
		this.occupancyTypeId = occupancyTypeId;
	}

	public Integer getTotalAdult() {
		return totalAdult;
	}

	public void setTotalAdult(Integer totalAdult) {
		this.totalAdult = totalAdult;
	}

	public Integer getTotalChild() {
		return totalChild;
	}

	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}

	public Integer getExtraAdult() {
		return extraAdult;
	}

	public void setExtraAdult(Integer extraAdult) {
		this.extraAdult = extraAdult;
	}

	public Integer getExtraChild() {
		return extraChild;
	}

	public void setExtraChild(Integer extraChild) {
		this.extraChild = extraChild;
	}

	@Override
	public String toString() {
		return "RoomOccupancyDTO [id=" + id + ", hotelRoomId=" + hotelRoomId + ", hotelRoomCategoryName="
				+ hotelRoomCategoryName + ", occupancyTypeName=" + occupancyTypeName + ", occupancyTypeId="
				+ occupancyTypeId + ", totalAdult=" + totalAdult + ", totalChild=" + totalChild + ", extraAdult="
				+ extraAdult + ", extraChild=" + extraChild + "]";
	}
	
	
}
