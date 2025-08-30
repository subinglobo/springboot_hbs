package com.choosenfly.hotelbookingsystem.inventory.entities.discount;

import java.util.Date;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.entities.specialrate.SpecialRateCombined;

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
@Table(name="discountrate")
public class DiscountRate extends BaseEntity { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id", length = 11)
	private Long discountId;

    @ManyToOne
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

	@Column(name = "season_id", length = 11)
	private Integer seasonId;

	@Column(name = "rate_code", length = 200)
	private String rateCode;

	@Column(name = "is_week_day")
	private Boolean isWeekDay;

	@Column(name = "is_week_end")
	private Boolean isWeekEnd;

	@Column(name = "is_all_days")
	private Boolean isAllDays;

	@Column(name = "is_refund")
	private Boolean isRefund;

	@Column(name = "book_date")
	private Date bookDate;

	@Column(name = "book_day", length = 11)
	private Integer bookDay;

	@Column(name = "remark", length = 500)
	private String remark;

	@Column(name = "is_live")
	private Boolean isLive = false;


	@Column(name = "promotion_room")
	private Boolean promotionRoom;

	@Column(name = "extra_bed")
	private Boolean extraBed;

	@Column(name = "promotion_meals")
	private Boolean promotionMeals;
	
    @OneToMany(mappedBy = "discountRate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DiscountMarketType>discountMarketTypes;
    
    @OneToMany(mappedBy = "discountRate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DiscountValidity>discountValidities;
    
    @OneToMany(mappedBy = "discountRate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DiscountRoom>discountRooms;
    
    @OneToMany(mappedBy = "discountRate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DiscountExcludedCountry>discountExcludedCountries;
    

	public Long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	

	public Boolean getPromotionRoom() {
		return promotionRoom;
	}

	public void setPromotionRoom(Boolean promotionRoom) {
		this.promotionRoom = promotionRoom;
	}

	

	public Boolean getExtraBed() {
		return extraBed;
	}

	public void setExtraBed(Boolean extraBed) {
		this.extraBed = extraBed;
	}

	public Boolean getPromotionMeals() {
		return promotionMeals;
	}

	public void setPromotionMeals(Boolean promotionMeals) {
		this.promotionMeals = promotionMeals;
	}

	public List<DiscountMarketType> getDiscountMarketTypes() {
		return discountMarketTypes;
	}

	public void setDiscountMarketTypes(List<DiscountMarketType> discountMarketTypes) {
		this.discountMarketTypes = discountMarketTypes;
	}

	public List<DiscountValidity> getDiscountValidities() {
		return discountValidities;
	}

	public void setDiscountValidities(List<DiscountValidity> discountValidities) {
		this.discountValidities = discountValidities;
	}

	public List<DiscountRoom> getDiscountRooms() {
		return discountRooms;
	}

	public void setDiscountRooms(List<DiscountRoom> discountRooms) {
		this.discountRooms = discountRooms;
	}

	public List<DiscountExcludedCountry> getDiscountExcludedCountries() {
		return discountExcludedCountries;
	}

	public void setDiscountExcludedCountries(List<DiscountExcludedCountry> discountExcludedCountries) {
		this.discountExcludedCountries = discountExcludedCountries;
	}

	@Override
	public String toString() {
		return "DiscountRate [discountId=" + discountId + ", hotel=" + hotel + ", seasonId=" + seasonId + ", rateCode="
				+ rateCode + ", isWeekDay=" + isWeekDay + ", isWeekEnd=" + isWeekEnd + ", isAllDays=" + isAllDays
				+ ", isRefund=" + isRefund + ", bookDate=" + bookDate + ", bookDay=" + bookDay + ", remark=" + remark
				+ ", isLive=" + isLive + ", promotionRoom=" + promotionRoom + ", extraBed=" + extraBed
				+ ", promotionMeals=" + promotionMeals + ", discountMarketTypes=" + discountMarketTypes
				+ ", discountValidities=" + discountValidities + ", discountRooms=" + discountRooms
				+ ", discountExcludedCountries=" + discountExcludedCountries + "]";
	}

	
    
    

}