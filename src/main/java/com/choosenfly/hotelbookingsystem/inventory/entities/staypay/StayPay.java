package com.choosenfly.hotelbookingsystem.inventory.entities.staypay;

import java.util.Date;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.contractrate.ContractRateMarketType;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateCombined;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="hotel_staypay")
public class StayPay extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staypay_id")
	private Long staypayId;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

	@Column(name = "season_id")
	private Integer seasonId;

	@Column(name = "rate_code")
	private String rateCode;

	@Column(name = "is_weekday")
	private Boolean isWeekDay;

	@Column(name = "is_weekend")
	private Boolean isWeekEnd;

	@Column(name = "is_alldays")
	private Boolean isAllDays;

	@Column(name = "is_refund")
	private Boolean isRefund;

	@Column(name = "book_date")
	private Date bookDate;

	@Column(name = "book_day")
	private Long bookDay;

	@Column(name = "remark")
	private String remark;

	@Column(name = "promotionfor")
	private Integer promotionfor; //if 1 for room and 2 for extra bed 
	
	@Column(name = "is_live")
	private Boolean isLive = false;

	@Column(name = "is_validity")
	private Boolean isValidity;
	
    @OneToMany(mappedBy = "stayPay",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaypayMarketType> marketTypes ;
    
    @OneToMany(mappedBy = "stayPay",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaypayRoom> staypayRooms ;
    
    @OneToMany(mappedBy = "stayPay",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaypayValidity> validities ;
    

	public Long getStaypayId() {
		return staypayId;
	}

	public void setStaypayId(Long staypayId) {
		this.staypayId = staypayId;
	}

	

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
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

	public Long getBookDay() {
		return bookDay;
	}

	public void setBookDay(Long bookDay) {
		this.bookDay = bookDay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public Integer getPromotionfor() {
		return promotionfor;
	}

	public void setPromotionfor(Integer promotionfor) {
		this.promotionfor = promotionfor;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public boolean isValidity() {
		return isValidity;
	}

	public void setValidity(boolean isValidity) {
		this.isValidity = isValidity;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	
	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	public Boolean getIsValidity() {
		return isValidity;
	}

	public void setIsValidity(Boolean isValidity) {
		this.isValidity = isValidity;
	}

	public List<StaypayMarketType> getMarketTypes() {
		return marketTypes;
	}

	public void setMarketTypes(List<StaypayMarketType> marketTypes) {
		this.marketTypes = marketTypes;
	}

	
	public List<StaypayRoom> getStaypayRooms() {
		return staypayRooms;
	}

	public void setStaypayRooms(List<StaypayRoom> staypayRooms) {
		this.staypayRooms = staypayRooms;
	}

	
	public List<StaypayValidity> getValidities() {
		return validities;
	}

	public void setValidities(List<StaypayValidity> validities) {
		this.validities = validities;
	}

	
	

	
	@Override
	public String toString() {
		return "StayPay [staypayId=" + staypayId + ", hotel=" + hotel + ", seasonId=" + seasonId + ", rateCode="
				+ rateCode + ", isWeekDay=" + isWeekDay + ", isWeekEnd=" + isWeekEnd + ", isAllDays=" + isAllDays
				+ ", isRefund=" + isRefund + ", bookDate=" + bookDate + ", bookDay=" + bookDay + ", remark=" + remark
				+ ", promotionfor=" + promotionfor + ", isLive=" + isLive + ", isValidity=" + isValidity
				+ ", marketTypes=" + marketTypes + ", staypayRooms=" + staypayRooms + ", validities=" + validities
				+ "]";
	}
	
	
	
	
}
