package com.choosenfly.hotelbookingsystem.entities.availability;

import java.util.List;
import java.util.Set;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;
import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.entities.hotel.HotelRoom;
import com.choosenfly.hotelbookingsystem.entities.master.MasterMarketType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_availability")
public class HotelAvailability extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private MasterMarketType marketType;

    @OneToMany(mappedBy = "hotelAvailability", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityValidity> validityPeriods;
	
	
	@ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private HotelRoom hotelRoom;
	
	@ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
	
	@Column(name = "no_of_rooms")
	private Integer noOfRooms;
	
	@Column(name = "release_day")
	private Integer releaseDay;
	
	
    @Enumerated(EnumType.STRING)
    @Column(name = "availability_type", nullable = false)
    private AvailabilityType availabilityType;
	
    
 // New field for allowed check-in days
    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "availability_checkin_days", joinColumns = @JoinColumn(name = "validity_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "checkin_day")
    private Set<DayOfWeek> checkinAllowedDays;
    
    @Column(name = "status")
    private Boolean status;
    
    
	public enum AvailabilityType {
	    FREE_SALE,
	    ROOM_ALLOCATION,
	    PRE_BUY
	}
	
	public enum DayOfWeek {
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}

	
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MasterMarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MasterMarketType marketType) {
		this.marketType = marketType;
	}

	public List<AvailabilityValidity> getValidityPeriods() {
		return validityPeriods;
	}

	public void setValidityPeriods(List<AvailabilityValidity> validityPeriods) {
		this.validityPeriods = validityPeriods;
	}

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public Integer getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getReleaseDay() {
		return releaseDay;
	}

	public void setReleaseDay(Integer releaseDay) {
		this.releaseDay = releaseDay;
	}

	public AvailabilityType getAvailabilityType() {
		return availabilityType;
	}

	public void setAvailabilityType(AvailabilityType availabilityType) {
		this.availabilityType = availabilityType;
	}

	public Set<DayOfWeek> getCheckinAllowedDays() {
		return checkinAllowedDays;
	}

	public void setCheckinAllowedDays(Set<DayOfWeek> checkinAllowedDays) {
		this.checkinAllowedDays = checkinAllowedDays;
	}

	@Override
	public String toString() {
		return "HotelAvailability [id=" + id + ", marketType=" + marketType + ", validityPeriods=" + validityPeriods
				+ ", hotelRoom=" + hotelRoom + ", noOfRooms=" + noOfRooms + ", releaseDay=" + releaseDay
				+ ", availabilityType=" + availabilityType + ", checkinAllowedDays=" + checkinAllowedDays + ", status="
				+ status + "]";
	}


	
	
}
