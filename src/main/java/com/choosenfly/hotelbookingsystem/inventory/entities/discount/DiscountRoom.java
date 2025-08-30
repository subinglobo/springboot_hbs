package com.choosenfly.hotelbookingsystem.inventory.entities.discount;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomCategory;
import com.choosenfly.hotelbookingsystem.inventory.entities.HotelRoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="discount_room")
public class DiscountRoom extends BaseEntity  { 

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better for DB-generated keys
	    @Column(name = "discount_room_id", nullable = false)
	    private Long discountRoomId;

	    @ManyToOne
	    @JoinColumn(name = "discount_id")
	    private DiscountRate discountRate;

	    @ManyToOne
	    @JoinColumn(name = "hotel_room_category_id", nullable = false)
	    private HotelRoomCategory roomCategory;

	    @ManyToOne
	    @JoinColumn(name = "hotel_roomType_id", nullable = false)
	    private HotelRoomType roomType;

	    // Use numeric type if it’s a percentage (BigDecimal recommended for precision)
	    @Column(name = "discount_percent") 
	    private String discountPercent;

	    @Column(name = "discount_value") 
	    private String discountValue;

	    @Column(name = "length_restriction")
	    private Long lengthRestriction;

		public Long getDiscountRoomId() {
			return discountRoomId;
		}

		public void setDiscountRoomId(Long discountRoomId) {
			this.discountRoomId = discountRoomId;
		}

		public DiscountRate getDiscountRate() {
			return discountRate;
		}

		public void setDiscountRate(DiscountRate discountRate) {
			this.discountRate = discountRate;
		}

		public HotelRoomCategory getRoomCategory() {
			return roomCategory;
		}

		public void setRoomCategory(HotelRoomCategory roomCategory) {
			this.roomCategory = roomCategory;
		}

		public HotelRoomType getRoomType() {
			return roomType;
		}

		public void setRoomType(HotelRoomType roomType) {
			this.roomType = roomType;
		}

		

		public String getDiscountPercent() {
			return discountPercent;
		}

		public void setDiscountPercent(String discountPercent) {
			this.discountPercent = discountPercent;
		}

		public String getDiscountValue() {
			return discountValue;
		}

		public void setDiscountValue(String discountValue) {
			this.discountValue = discountValue;
		}

		public Long getLengthRestriction() {
			return lengthRestriction;
		}

		public void setLengthRestriction(Long lengthRestriction) {
			this.lengthRestriction = lengthRestriction;
		}
	    
	    

}
