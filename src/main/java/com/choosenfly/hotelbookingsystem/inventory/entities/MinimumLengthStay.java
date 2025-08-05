package com.choosenfly.hotelbookingsystem.inventory.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "minimum_length_stay")
public class MinimumLengthStay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="minimum_days")
	private Integer minimumDays;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private HotelRoom room;
	
	@ManyToOne
	@JoinColumn(name = "minimum_length_id")
	private MinimumLength minimumLength;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMinimumDays() {
		return minimumDays;
	}

	public void setMinimumDays(Integer minimumDays) {
		this.minimumDays = minimumDays;
	}

	public HotelRoom getRoom() {
		return room;
	}

	public void setRoom(HotelRoom room) {
		this.room = room;
	}

	public MinimumLength getMinimumLength() {
		return minimumLength;
	}

	public void setMinimumLength(MinimumLength minimumLength) {
		this.minimumLength = minimumLength;
	}

	@Override
	public String toString() {
		return "MinimumLengthStay [id=" + id + ", minimumDays=" + minimumDays + ", room=" + room + ", minimumLength="
				+ minimumLength + "]";
	}


	
	
	
}
