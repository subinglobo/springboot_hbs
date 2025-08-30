package com.choosenfly.hotelbookingsystem.inventory.dto.discount;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DiscountDTO {

		private Long discountId;
		
	    private Long hotelId;
	    
	    private Integer seasonId;
		
	 	private List<Long> marketype;

	    private String rateCode;
	    
	    private List<Long> excludeCountry;

	    private Boolean weekDay;
	    
	    private Boolean weekEnd;
	    
	    private Boolean allDays=false;
	    
	    private Boolean refund=false;

	    private Boolean ExtraBed=false;
	    
	    private Integer promotionMeals;

	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private Date bookDate;
	    
	    private Integer bookDay;
	    
	    private String remark;

	    private List<DicountValidityDTO> validityDTO;
	    
	    private List<DiscountRoomDTO> roomDTO;

		public Long getDiscountId() {
			return discountId;
		}

		public void setDiscountId(Long discountId) {
			this.discountId = discountId;
		}

		

		public Long getHotelId() {
			return hotelId;
		}

		public void setHotelId(Long hotelId) {
			this.hotelId = hotelId;
		}

		

		public Integer getSeasonId() {
			return seasonId;
		}

		public void setSeasonId(Integer seasonId) {
			this.seasonId = seasonId;
		}

		public List<Long> getMarketype() {
			return marketype;
		}

		public void setMarketype(List<Long> marketype) {
			this.marketype = marketype;
		}

		public String getRateCode() {
			return rateCode;
		}

		public void setRateCode(String rateCode) {
			this.rateCode = rateCode;
		}

		
		public List<Long> getExcludeCountry() {
			return excludeCountry;
		}

		public void setExcludeCountry(List<Long> excludeCountry) {
			this.excludeCountry = excludeCountry;
		}

		public Boolean getWeekDay() {
			return weekDay;
		}

		public void setWeekDay(Boolean weekDay) {
			this.weekDay = weekDay;
		}

		public Boolean getWeekEnd() {
			return weekEnd;
		}

		public void setWeekEnd(Boolean weekEnd) {
			this.weekEnd = weekEnd;
		}

		public Boolean getAllDays() {
			return allDays;
		}

		public void setAllDays(Boolean allDays) {
			this.allDays = allDays;
		}

		public Boolean getRefund() {
			return refund;
		}

		public void setRefund(Boolean refund) {
			this.refund = refund;
		}

		public Boolean getExtraBed() {
			return ExtraBed;
		}

		public void setExtraBed(Boolean extraBed) {
			ExtraBed = extraBed;
		}

		public Integer getPromotionMeals() {
			return promotionMeals;
		}

		public void setPromotionMeals(Integer promotionMeals) {
			this.promotionMeals = promotionMeals;
		}

		public Date getBookDate() {
			return bookDate;
		}

		public void setBookDate(Date bookDate) {
			this.bookDate = bookDate;
		}

		public Integer getBookDay() {
			return bookDay;
		}

		public void setBookDay(Integer bookDay) {
			this.bookDay = bookDay;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public List<DicountValidityDTO> getValidityDTO() {
			return validityDTO;
		}

		public void setValidityDTO(List<DicountValidityDTO> validityDTO) {
			this.validityDTO = validityDTO;
		}

		public List<DiscountRoomDTO> getRoomDTO() {
			return roomDTO;
		}

		public void setRoomDTO(List<DiscountRoomDTO> roomDTO) {
			this.roomDTO = roomDTO;
		}

		
		
	    
	    
	    
	    
}
