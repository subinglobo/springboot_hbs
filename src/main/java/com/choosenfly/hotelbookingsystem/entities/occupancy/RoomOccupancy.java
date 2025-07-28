package com.choosenfly.hotelbookingsystem.entities.occupancy;

import com.choosenfly.hotelbookingsystem.entities.hotel.HotelRoom;
import com.choosenfly.hotelbookingsystem.entities.master.MasterOccupancyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_occupancy")
public class RoomOccupancy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_occupancy_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hotel_room_id", nullable = false)
	private HotelRoom hotelRoom;

	@ManyToOne
	@JoinColumn(name = "occupancy_type_id", nullable = false)
	private MasterOccupancyType occupancyType; // Now referencing the master table

	@ManyToOne
	@JoinColumn(name = "hotel_occupancy_id", nullable = false)
	private HotelOccupancy hotelOccupancy;
	
	@Column(name = "total_adult")
	private Integer totalAdult;

	@Column(name = "total_child")
	private Integer totalChild;

	@Column(name = "extra_adult")
	private Integer extraAdult;

	@Column(name = "extra_child")
	private Integer extraChild;

	
	
	public HotelOccupancy getHotelOccupancy() {
		return hotelOccupancy;
	}

	public void setHotelOccupancy(HotelOccupancy hotelOccupancy) {
		this.hotelOccupancy = hotelOccupancy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public MasterOccupancyType getOccupancyType() {
		return occupancyType;
	}

	public void setOccupancyType(MasterOccupancyType occupancyType) {
		this.occupancyType = occupancyType;
	}

	public Integer getTotalAdult() {
		return totalAdult;
	}

	public void setTotalAdult(Integer totalAdult) {
		this.totalAdult = totalAdult;
	}

	public Integer getTotalChild() {
		return totalChild;
	}

	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}

	public Integer getExtraAdult() {
		return extraAdult;
	}

	public void setExtraAdult(Integer extraAdult) {
		this.extraAdult = extraAdult;
	}

	public Integer getExtraChild() {
		return extraChild;
	}

	public void setExtraChild(Integer extraChild) {
		this.extraChild = extraChild;
	}

	@Override
	public String toString() {
		return "RoomOccupancy [id=" + id + ", hotelRoom=" + hotelRoom + ", occupancyType=" + occupancyType
				+ ", totalAdult=" + totalAdult + ", totalChild=" + totalChild + ", extraAdult=" + extraAdult
				+ ", extraChild=" + extraChild + "]";
	}
	
	
}
