package com.choosenfly.hotelbookingsystem.entities.hotel.linked;

import com.choosenfly.hotelbookingsystem.entities.hotel.Hotel;
import com.choosenfly.hotelbookingsystem.entities.master.MasterHotelAmenities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "linked_hotel_amenities", schema = "public") // Adjust schema if needed
public class LinkedHotelAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "amenity_id", nullable = false)
    private MasterHotelAmenities amenity;

    // Optional: Add additional fields (e.g., status, created_date) if needed
    // @Column(name = "status")
    // private String status;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public MasterHotelAmenities getAmenity() {
        return amenity;
    }

    public void setAmenity(MasterHotelAmenities amenity) {
        this.amenity = amenity;
    }
}