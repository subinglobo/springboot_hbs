package com.choosenfly.hotelbookingsystem.masters.entities;

import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.inventory.entities.LinkedHotelRoomAmenity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_room_amenities", schema = "public")
public class MasterRoomAmenities extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "amenities_seq")
    @SequenceGenerator(name = "amenities_seq", sequenceName = "amenities_amenities_id_seq", allocationSize = 1)
    @Column(name = "amenities_id", nullable = false)
    private Long amenitiesId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "amenity", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<LinkedHotelRoomAmenity> hotelRoomAmenities;

	public Long getAmenitiesId() {
		return amenitiesId;
	}

	public void setAmenitiesId(Long amenitiesId) {
		this.amenitiesId = amenitiesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<LinkedHotelRoomAmenity> getHotelRoomAmenities() {
		return hotelRoomAmenities;
	}

	public void setHotelRoomAmenities(List<LinkedHotelRoomAmenity> hotelRoomAmenities) {
		this.hotelRoomAmenities = hotelRoomAmenities;
	}

	@Override
	public String toString() {
		return "MasterRoomAmenities [amenitiesId=" + amenitiesId + ", name=" + name + ", isDeleted=" + isDeleted
				+ ", hotelRoomAmenities=" + hotelRoomAmenities + "]";
	}


    
}