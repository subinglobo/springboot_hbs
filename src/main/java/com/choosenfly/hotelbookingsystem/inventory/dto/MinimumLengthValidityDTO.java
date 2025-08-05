package com.choosenfly.hotelbookingsystem.inventory.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MinimumLengthValidityDTO {

	private Long id;
	private Long minimumLengthId;
	private LocalDateTime validityFrom;
	private LocalDateTime validityTo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMinimumLengthId() {
		return minimumLengthId;
	}
	public void setMinimumLengthId(Long minimumLengthId) {
		this.minimumLengthId = minimumLengthId;
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
		return "MinimumLengthValidityDTO [id=" + id + ", minimumLengthId=" + minimumLengthId + ", validityFrom="
				+ validityFrom + ", validityTo=" + validityTo + "]";
	}
	
	
	
}
