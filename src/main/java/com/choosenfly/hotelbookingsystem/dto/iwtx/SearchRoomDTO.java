package com.choosenfly.hotelbookingsystem.dto.iwtx;

public class SearchRoomDTO {

	private long roomcount;

	private long adult;

	private long child;
	
	private long ocuupancyid;
	
	private boolean isRefund;

	private int[] childAge;
	
	
	private int[] adultAge;
	
	private String room_category;
	
	private String mealType;
	
	private String room_type;
	
	private Long room_category_id = 0L;
	
	private Long room_type_id = 0L;

    private String share_type_id_ws; //  added on 8 th april commited on 25/6
	
	private String price; // added on 11 th april
	
	private String priceWithoutMarkup; 
	
	private String deadLineDate;

	public long getRoomcount() {
		return roomcount;
	}

	public void setRoomcount(long roomcount) {
		this.roomcount = roomcount;
	}

	public long getAdult() {
		return adult;
	}

	public void setAdult(long adult) {
		this.adult = adult;
	}

	public long getChild() {
		return child;
	}

	public void setChild(long child) {
		this.child = child;
	}

	public long getOcuupancyid() {
		return ocuupancyid;
	}

	public void setOcuupancyid(long ocuupancyid) {
		this.ocuupancyid = ocuupancyid;
	}

	public boolean isRefund() {
		return isRefund;
	}

	public void setRefund(boolean isRefund) {
		this.isRefund = isRefund;
	}

	public int[] getChildAge() {
		return childAge;
	}

	public void setChildAge(int[] childAge) {
		this.childAge = childAge;
	}

	public int[] getAdultAge() {
		return adultAge;
	}

	public void setAdultAge(int[] adultAge) {
		this.adultAge = adultAge;
	}

	public String getRoom_category() {
		return room_category;
	}

	public void setRoom_category(String room_category) {
		this.room_category = room_category;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public Long getRoom_category_id() {
		return room_category_id;
	}

	public void setRoom_category_id(Long room_category_id) {
		this.room_category_id = room_category_id;
	}

	public Long getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(Long room_type_id) {
		this.room_type_id = room_type_id;
	}

	public String getShare_type_id_ws() {
		return share_type_id_ws;
	}

	public void setShare_type_id_ws(String share_type_id_ws) {
		this.share_type_id_ws = share_type_id_ws;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceWithoutMarkup() {
		return priceWithoutMarkup;
	}

	public void setPriceWithoutMarkup(String priceWithoutMarkup) {
		this.priceWithoutMarkup = priceWithoutMarkup;
	}

	public String getDeadLineDate() {
		return deadLineDate;
	}

	public void setDeadLineDate(String deadLineDate) {
		this.deadLineDate = deadLineDate;
	}
	
	
}
