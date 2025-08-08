package com.choosenfly.hotelbookingsystem.inventory.entities.specialrate;

import java.util.Date;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_specialrate")
public class SpecialRate extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="special_rate_id")
	private Long specialRateId;

	@ManyToOne
	@JoinColumn(name ="hotel_id",nullable = false )
	private Hotel hotel;

	@Column(name="season_id")
	private Long seasonId;

	@Column(name="rate_code")
	private String rateCode;

	@Column(name="is_weekend_day")
	private Boolean isWeekDay=false;

	@Column(name="is_weekend")
	private Boolean isWeekEnd=false;

	@Column(name="is_alldays")
	private Boolean isAllDays=false;

	@Column(name="is_refund")
	private Boolean isRefund;

	@Column(name="book_date")
	private Date bookDate;

	@Column(name="book_day")
	private String bookDay;

	@Column(name="length_stay")
	private Long lengthStay;

	@Column(name="remark")
	private String remark;
	
	@Column(name="type")
	private String type;
	
	@Column(name="exclude_county")
	private String excludeCountry;

	@Column(name="is_validity")
	private Boolean isValidity;
	
	@Column(name="is_live")
	private Boolean isLive = false;
	
	@OneToMany(mappedBy = "specialRate",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<SpecialRateValidity>specialRateValidities;
	
	@OneToMany(mappedBy = "specialRate",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<SpecialRateMarketType>specialRateMarketTypes;
	
	@OneToMany(mappedBy = "specialRate",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<SpecialRateRoom>specialRateRooms;

	public Long getSpecialRateId() {
		return specialRateId;
	}

	public void setSpecialRateId(Long specialRateId) {
		this.specialRateId = specialRateId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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

	public Boolean getIsWeekDay() {
		return isWeekDay;
	}

	public void setIsWeekDay(Boolean isWeekDay) {
		this.isWeekDay = isWeekDay;
	}

	public Boolean getIsWeekEnd() {
		return isWeekEnd;
	}

	public void setIsWeekEnd(Boolean isWeekEnd) {
		this.isWeekEnd = isWeekEnd;
	}

	public Boolean getIsAllDays() {
		return isAllDays;
	}

	public void setIsAllDays(Boolean isAllDays) {
		this.isAllDays = isAllDays;
	}

	public Boolean getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(Boolean isRefund) {
		this.isRefund = isRefund;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getExcludeCountry() {
		return excludeCountry;
	}

	public void setExcludeCountry(String excludeCountry) {
		this.excludeCountry = excludeCountry;
	}

	public Boolean getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(Boolean isValidity) {
		this.isValidity = isValidity;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	
	
	public List<SpecialRateValidity> getSpecialRateValidities() {
		return specialRateValidities;
	}

	public void setSpecialRateValidities(List<SpecialRateValidity> specialRateValidities) {
		this.specialRateValidities = specialRateValidities;
	}

	public List<SpecialRateMarketType> getSpecialRateMarketTypes() {
		return specialRateMarketTypes;
	}

	public void setSpecialRateMarketTypes(List<SpecialRateMarketType> specialRateMarketTypes) {
		this.specialRateMarketTypes = specialRateMarketTypes;
	}

	public List<SpecialRateRoom> getSpecialRateRooms() {
		return specialRateRooms;
	}

	public void setSpecialRateRooms(List<SpecialRateRoom> specialRateRooms) {
		this.specialRateRooms = specialRateRooms;
	}

	
	@Override
	public String toString() {
		return "SpecialRate [specialRateId=" + specialRateId + ", hotel=" + hotel + ", seasonId=" + seasonId
				+ ", rateCode=" + rateCode + ", isWeekDay=" + isWeekDay + ", isWeekEnd=" + isWeekEnd + ", isAllDays="
				+ isAllDays + ", isRefund=" + isRefund + ", bookDate=" + bookDate + ", bookDay=" + bookDay
				+ ", lengthStay=" + lengthStay + ", remark=" + remark + ", type=" + type + ", excludeCountry="
				+ excludeCountry + ", isValidity=" + isValidity + ", isLive=" + isLive + ", specialRateValidities="
				+ specialRateValidities + ", specialRateMarketTypes=" + specialRateMarketTypes + ", specialRateRooms="
				+ specialRateRooms + "]";
	}
	
	
}
