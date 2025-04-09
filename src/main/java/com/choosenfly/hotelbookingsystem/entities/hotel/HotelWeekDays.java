package com.choosenfly.hotelbookingsystem.entities.hotel;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel_week_days")
public class HotelWeekDays extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wd_sunday", nullable = false)
    private Boolean wdSunday = false;

    @Column(name = "wd_monday", nullable = false)
    private Boolean wdMonday = false;

    @Column(name = "wd_tuesday", nullable = false)
    private Boolean wdTuesday = false;

    @Column(name = "wd_wednesday", nullable = false)
    private Boolean wdWednesday = false;

    @Column(name = "wd_thursday", nullable = false)
    private Boolean wdThursday = false;

    @Column(name = "wd_friday", nullable = false)
    private Boolean wdFriday = false;

    @Column(name = "wd_saturday", nullable = false)
    private Boolean wdSaturday = false;

    @Column(name = "wed_sunday", nullable = false)
    private Boolean wedSunday = false;

    @Column(name = "wed_monday", nullable = false)
    private Boolean wedMonday = false;

    @Column(name = "wed_tuesday", nullable = false)
    private Boolean wedTuesday = false;

    @Column(name = "wed_wednesday", nullable = false)
    private Boolean wedWednesday = false;

    @Column(name = "wed_thursday", nullable = false)
    private Boolean wedThursday = false;

    @Column(name = "wed_friday", nullable = false)
    private Boolean wedFriday = false;

    @Column(name = "wed_saturday", nullable = false)
    private Boolean wedSaturday = false;

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getWdSunday() {
		return wdSunday;
	}

	public void setWdSunday(Boolean wdSunday) {
		this.wdSunday = wdSunday;
	}

	public Boolean getWdMonday() {
		return wdMonday;
	}

	public void setWdMonday(Boolean wdMonday) {
		this.wdMonday = wdMonday;
	}

	public Boolean getWdTuesday() {
		return wdTuesday;
	}

	public void setWdTuesday(Boolean wdTuesday) {
		this.wdTuesday = wdTuesday;
	}

	public Boolean getWdWednesday() {
		return wdWednesday;
	}

	public void setWdWednesday(Boolean wdWednesday) {
		this.wdWednesday = wdWednesday;
	}

	public Boolean getWdThursday() {
		return wdThursday;
	}

	public void setWdThursday(Boolean wdThursday) {
		this.wdThursday = wdThursday;
	}

	public Boolean getWdFriday() {
		return wdFriday;
	}

	public void setWdFriday(Boolean wdFriday) {
		this.wdFriday = wdFriday;
	}

	public Boolean getWdSaturday() {
		return wdSaturday;
	}

	public void setWdSaturday(Boolean wdSaturday) {
		this.wdSaturday = wdSaturday;
	}

	public Boolean getWedSunday() {
		return wedSunday;
	}

	public void setWedSunday(Boolean wedSunday) {
		this.wedSunday = wedSunday;
	}

	public Boolean getWedMonday() {
		return wedMonday;
	}

	public void setWedMonday(Boolean wedMonday) {
		this.wedMonday = wedMonday;
	}

	public Boolean getWedTuesday() {
		return wedTuesday;
	}

	public void setWedTuesday(Boolean wedTuesday) {
		this.wedTuesday = wedTuesday;
	}

	public Boolean getWedWednesday() {
		return wedWednesday;
	}

	public void setWedWednesday(Boolean wedWednesday) {
		this.wedWednesday = wedWednesday;
	}

	public Boolean getWedThursday() {
		return wedThursday;
	}

	public void setWedThursday(Boolean wedThursday) {
		this.wedThursday = wedThursday;
	}

	public Boolean getWedFriday() {
		return wedFriday;
	}

	public void setWedFriday(Boolean wedFriday) {
		this.wedFriday = wedFriday;
	}

	public Boolean getWedSaturday() {
		return wedSaturday;
	}

	public void setWedSaturday(Boolean wedSaturday) {
		this.wedSaturday = wedSaturday;
	}

	@Override
	public String toString() {
		return "HotelWeekDays [id=" + id + ", wdSunday=" + wdSunday + ", wdMonday=" + wdMonday + ", wdTuesday="
				+ wdTuesday + ", wdWednesday=" + wdWednesday + ", wdThursday=" + wdThursday + ", wdFriday=" + wdFriday
				+ ", wdSaturday=" + wdSaturday + ", wedSunday=" + wedSunday + ", wedMonday=" + wedMonday
				+ ", wedTuesday=" + wedTuesday + ", wedWednesday=" + wedWednesday + ", wedThursday=" + wedThursday
				+ ", wedFriday=" + wedFriday + ", wedSaturday=" + wedSaturday + "]";
	}

	

 
	
	
}