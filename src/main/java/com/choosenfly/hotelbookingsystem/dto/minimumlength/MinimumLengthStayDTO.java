package com.choosenfly.hotelbookingsystem.dto.minimumlength;

import jakarta.validation.constraints.NotNull;

public class MinimumLengthStayDTO {

	private Long id;
	
	private Long roomId;
	
	@NotNull
	private Integer minimumLength;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Integer getMinimumLength() {
		return minimumLength;
	}

	public void setMinimumLength(Integer minimumLength) {
		this.minimumLength = minimumLength;
	}

	@Override
	public String toString() {
		return "MinimumLengthStayDTO [id=" + id + ", roomId=" + roomId + ", minimumLength=" + minimumLength + "]";
	}

	
	
	
	
}
