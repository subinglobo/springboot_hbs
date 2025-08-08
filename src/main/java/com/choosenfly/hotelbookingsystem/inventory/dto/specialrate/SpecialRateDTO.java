package com.choosenfly.hotelbookingsystem.inventory.dto.specialrate;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SpecialRateDTO {
	
	private Long specialRateId;

    private List<Long> marketype;
    
    private String excludeCountry;
    
    private List<String> combinedPromo;
    
    private List<String> promotypeArray;
    
    private Long hotelId;;
    
    private Long seasonId;
    
    private String rateCode;
    
    private Boolean weekDay;
    
    private Boolean weekEnd;
    
    private Boolean allDays;
    
    private Boolean IsRefund;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date bookDate;
    
    private String bookDay;
    
    private Long lengthStay;
    
    private String remark;
    
    private List<SpecialRateValidityDTO> specialRateValidityDTO;
    
    private List<SpecialRateRoomDTO> specialRateRoomDTO;

	public Long getSpecialRateId() {
		return specialRateId;
	}

	public void setSpecialRateId(Long specialRateId) {
		this.specialRateId = specialRateId;
	}

	public List<Long> getMarketype() {
		return marketype;
	}

	public void setMarketype(List<Long> marketype) {
		this.marketype = marketype;
	}

	public String getExcludeCountry() {
		return excludeCountry;
	}

	public void setExcludeCountry(String excludeCountry) {
		this.excludeCountry = excludeCountry;
	}

	public List<String> getCombinedPromo() {
		return combinedPromo;
	}

	public void setCombinedPromo(List<String> combinedPromo) {
		this.combinedPromo = combinedPromo;
	}

	public List<String> getPromotypeArray() {
		return promotypeArray;
	}

	public void setPromotypeArray(List<String> promotypeArray) {
		this.promotypeArray = promotypeArray;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
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

	public Boolean getIsRefund() {
		return IsRefund;
	}

	public void setIsRefund(Boolean isRefund) {
		IsRefund = isRefund;
	}

	

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookDay() {
		return bookDay;
	}

	public void setBookDay(String bookDay) {
		this.bookDay = bookDay;
	}

	

	public Long getLengthStay() {
		return lengthStay;
	}

	public void setLengthStay(Long lengthStay) {
		this.lengthStay = lengthStay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SpecialRateValidityDTO> getSpecialRateValidityDTO() {
		return specialRateValidityDTO;
	}

	public void setSpecialRateValidityDTO(List<SpecialRateValidityDTO> specialRateValidityDTO) {
		this.specialRateValidityDTO = specialRateValidityDTO;
	}

	public List<SpecialRateRoomDTO> getSpecialRateRoomDTO() {
		return specialRateRoomDTO;
	}

	public void setSpecialRateRoomDTO(List<SpecialRateRoomDTO> specialRateRoomDTO) {
		this.specialRateRoomDTO = specialRateRoomDTO;
	}

	
	@Override
	public String toString() {
		return "SpecialRateDTO [specialRateId=" + specialRateId + ", marketype=" + marketype + ", excludeCountry="
				+ excludeCountry + ", combinedPromo=" + combinedPromo + ", promotypeArray=" + promotypeArray
				+ ", hotelId=" + hotelId + ", seasonId=" + seasonId + ", rateCode=" + rateCode + ", weekDay=" + weekDay
				+ ", weekEnd=" + weekEnd + ", allDays=" + allDays + ", IsRefund=" + IsRefund + ", bookDate=" + bookDate
				+ ", bookDay=" + bookDay + ", lengthStay=" + lengthStay + ", remark=" + remark
				+ ", specialRateValidityDTO=" + specialRateValidityDTO + ", specialRateRoomDTO=" + specialRateRoomDTO
				+ "]";
	}
    
    
    
    
}
