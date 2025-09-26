package com.choosenfly.hotelbookingsystem.registration.cab.entities;

import java.util.Date;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cab_location")
public class CabLocation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cablocationid")
    private Long cabLocationId;

    @Column(name = "pickup")
    private String pickup;

    @Column(name = "dropoff")
    private String dropoff;

    // Many locations belong to one cab
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabid")
    private Cab cab;

	public Long getCabLocationId() {
		return cabLocationId;
	}

	public void setCabLocationId(Long cabLocationId) {
		this.cabLocationId = cabLocationId;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getDropoff() {
		return dropoff;
	}

	public void setDropoff(String dropoff) {
		this.dropoff = dropoff;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	@Override
	public String toString() {
		return "CabLocation [cabLocationId=" + cabLocationId + ", pickup=" + pickup + ", dropoff=" + dropoff + ", cab="
				+ cab + "]";
	}
    
    
	
}

