package com.choosenfly.hotelbookingsystem.hotel.search.dto;

import java.util.List;

public class RoomConfiguration {

	private int roomNo;
    private String adultCount;
    private String childCount;
    private List<Integer> childAges;
    private List<Integer> adultAges;
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getAdultCount() {
		return adultCount;
	}
	public void setAdultCount(String adultCount) {
		this.adultCount = adultCount;
	}
	public String getChildCount() {
		return childCount;
	}
	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}
	public List<Integer> getChildAges() {
		return childAges;
	}
	public void setChildAges(List<Integer> childAges) {
		this.childAges = childAges;
	}
	public List<Integer> getAdultAges() {
		return adultAges;
	}
	public void setAdultAges(List<Integer> adultAges) {
		this.adultAges = adultAges;
	}
	@Override
	public String toString() {
		return "RoomConfiguration [roomNo=" + roomNo + ", adultCount=" + adultCount + ", childCount=" + childCount
				+ ", childAges=" + childAges + ", adultAges=" + adultAges + "]";
	}
    
    
}
