package com.choosenfly.hotelbookingsystem.inventory.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="minimumlength_validity")
public class MinimumLengthValidity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "validity_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "minimumlength_id", nullable = false)
	private MinimumLength minimumLength;

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

	public MinimumLength getMinimumLength() {
		return minimumLength;
	}

	public void setMinimumLength(MinimumLength minimumLength) {
		this.minimumLength = minimumLength;
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
		return "MinimumLengthValidity [id=" + id + ", minimumLength=" + minimumLength + ", validityFrom=" + validityFrom
				+ ", validityTo=" + validityTo + "]";
	}
	

	
	
}
