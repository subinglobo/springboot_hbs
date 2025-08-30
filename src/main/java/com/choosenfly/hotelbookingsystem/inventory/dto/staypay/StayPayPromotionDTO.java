package com.choosenfly.hotelbookingsystem.inventory.dto.staypay;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StayPayPromotionDTO {

    private List<Long> marketype;
    
    private List<Integer> combinedPromo;
    
    private List<Integer> promotypeArray;

    private Long hotelId;
    
    private Integer seasonId;
    
    private Long staypayId;
    
    private String rateCode;
    
    private String excludeCountry;

    private boolean weekDay = false;
    
    private boolean weekEnd = false;
    
    private boolean allDays = false;
    
    private boolean refund = false;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date bookDate;
    
    private Long bookDay;
    
    private Integer promotionfor;
    
    private String remark;
    
    private List<StaypayValidityDTO> promotionValidityDTO;
    
    private List<StaypayRoomDTO> promotionRoomDTO;

    

	public List<Long> getMarketype() {
		return marketype;
	}

	public void setMarketype(List<Long> marketype) {
		this.marketype = marketype;
	}

	public List<Integer> getCombinedPromo() {
		return combinedPromo;
	}

	public void setCombinedPromo(List<Integer> combinedPromo) {
		this.combinedPromo = combinedPromo;
	}

	public List<Integer> getPromotypeArray() {
		return promotypeArray;
	}

	public void setPromotypeArray(List<Integer> promotypeArray) {
		this.promotypeArray = promotypeArray;
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

	public Long getStaypayId() {
		return staypayId;
	}

	public void setStaypayId(Long staypayId) {
		this.staypayId = staypayId;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getExcludeCountry() {
		return excludeCountry;
	}

	public void setExcludeCountry(String excludeCountry) {
		this.excludeCountry = excludeCountry;
	}

	public boolean isWeekDay() {
		return weekDay;
	}

	public void setWeekDay(boolean weekDay) {
		this.weekDay = weekDay;
	}

	public boolean isWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(boolean weekEnd) {
		this.weekEnd = weekEnd;
	}

	public boolean isAllDays() {
		return allDays;
	}

	public void setAllDays(boolean allDays) {
		this.allDays = allDays;
	}

	public boolean isRefund() {
		return refund;
	}

	public void setRefund(boolean refund) {
		this.refund = refund;
	}

	

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	
	

	public void setBookDay(Long bookDay) {
		this.bookDay = bookDay;
	}

	public List<StaypayValidityDTO> getPromotionValidityDTO() {
		return promotionValidityDTO;
	}

	public void setPromotionValidityDTO(List<StaypayValidityDTO> promotionValidityDTO) {
		this.promotionValidityDTO = promotionValidityDTO;
	}

	public List<StaypayRoomDTO> getPromotionRoomDTO() {
		return promotionRoomDTO;
	}

	public void setPromotionRoomDTO(List<StaypayRoomDTO> promotionRoomDTO) {
		this.promotionRoomDTO = promotionRoomDTO;
	}

	

	public Integer getPromotionfor() {
		return promotionfor;
	}

	public void setPromotionfor(Integer promotionfor) {
		this.promotionfor = promotionfor;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public Long getBookDay() {
		return bookDay;
	}

	
	@Override
	public String toString() {
		return "StayPayPromotionDTO [marketype=" + marketype + ", combinedPromo=" + combinedPromo + ", promotypeArray="
				+ promotypeArray + ", hotelId=" + hotelId + ", seasonId=" + seasonId + ", staypayId=" + staypayId
				+ ", rateCode=" + rateCode + ", excludeCountry=" + excludeCountry + ", weekDay=" + weekDay
				+ ", weekEnd=" + weekEnd + ", allDays=" + allDays + ", refund=" + refund + ", bookDate=" + bookDate
				+ ", bookDay=" + bookDay + ", promotionfor=" + promotionfor + ", remark=" + remark
				+ ", promotionValidityDTO=" + promotionValidityDTO + ", promotionRoomDTO=" + promotionRoomDTO + "]";
	}
    
    
}
