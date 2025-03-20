package com.choosenfly.hotelbookingsystem.entities.occupancy;

import java.time.LocalDateTime;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_occupancy_validity")
public class OccupancyValidity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "validity_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "occupancy_id", nullable = false)
	private HotelOccupancy hotelOccupancy;

	@Column(name = "validity_from", nullable = false)
	private LocalDateTime validityFrom;

	@Column(name = "validity_to", nullable = false)
	private LocalDateTime validityTo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HotelOccupancy getHotelOccupancy() {
		return hotelOccupancy;
	}

	public void setHotelOccupancy(HotelOccupancy hotelOccupancy) {
		this.hotelOccupancy = hotelOccupancy;
	}

	public LocalDateTime getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(LocalDateTime validityFrom) {
		this.validityFrom = validityFrom;
	}

	public LocalDateTime getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(LocalDateTime validityTo) {
		this.validityTo = validityTo;
	}

	@Override
	public String toString() {
		return "OccupancyValidity [id=" + id + ", hotelOccupancy=" + hotelOccupancy + ", validityFrom=" + validityFrom
				+ ", validityTo=" + validityTo + "]";
	}
	
	

}
