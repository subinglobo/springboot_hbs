package com.choosenfly.hotelbookingsystem.inventory.dto;

public class RoomOccupancyDTO {

	private Long id;
    private Long roomId; // Reference to HotelRoom by ID
    private String roomName;
    private Long occupancyTypeId; // Reference to MasterOccupancyType by ID
    private String occupancyTypeName;
    private Integer totalAdult;
    private Integer totalChild;
    private Integer extraAdult;
    private Integer extraChild;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Long getOccupancyTypeId() {
		return occupancyTypeId;
	}
	public void setOccupancyTypeId(Long occupancyTypeId) {
		this.occupancyTypeId = occupancyTypeId;
	}
	public String getOccupancyTypeName() {
		return occupancyTypeName;
	}
	public void setOccupancyTypeName(String occupancyTypeName) {
		this.occupancyTypeName = occupancyTypeName;
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
		return "RoomOccupancyDTO [id=" + id + ", roomId=" + roomId + ", roomName=" + roomName + ", occupancyTypeId="
				+ occupancyTypeId + ", occupancyTypeName=" + occupancyTypeName + ", totalAdult=" + totalAdult
				+ ", totalChild=" + totalChild + ", extraAdult=" + extraAdult + ", extraChild=" + extraChild + "]";
	}
    
    
}
