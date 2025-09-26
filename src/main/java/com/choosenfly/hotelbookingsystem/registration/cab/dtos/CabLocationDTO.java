package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

public class CabLocationDTO {

    private Long cablocationId;
    private Long cabid;
    private String pickup;
    private String dropoff;
	public Long getCablocationId() {
		return cablocationId;
	}
	public void setCablocationId(Long cablocationId) {
		this.cablocationId = cablocationId;
	}
	public Long getCabid() {
		return cabid;
	}
	public void setCabid(Long cabid) {
		this.cabid = cabid;
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
	@Override
	public String toString() {
		return "CabLocationDTO [cablocationId=" + cablocationId + ", cabid=" + cabid + ", pickup=" + pickup
				+ ", dropoff=" + dropoff + "]";
	}
    
}
