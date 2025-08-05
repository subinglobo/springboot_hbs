package com.choosenfly.hotelbookingsystem.inventory.entities.hotelstopsale;

import java.time.LocalDateTime;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stop_sale_validity")
public class StopSaleValiditty extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "validity_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "stopsale_id", nullable = false)
	private HotelStopSale stopSale;

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

	

	public HotelStopSale getStopSale() {
		return stopSale;
	}

	public void setStopSale(HotelStopSale stopSale) {
		this.stopSale = stopSale;
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
		return "StopSaleValiditty [id=" + id + ", stopSale=" + stopSale + ", validityFrom=" + validityFrom
				+ ", validityTo=" + validityTo + "]";
	}

	
	

}
