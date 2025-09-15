package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO representing a room request with occupancy details
 */
public class RoomRequest {

    @NotNull(message = "Number of adults is required")
    @Min(value = 1, message = "At least 1 adult is required per room")
    private Integer adults;

    @Min(value = 0, message = "Number of children cannot be negative")
    private Integer children;

    private List<Integer> adultAges;
    
    private List<Integer> childAges;

    // Default constructor
    public RoomRequest() {}

    // Constructor with parameters
    public RoomRequest(Integer adults, Integer children, List<Integer> adultAges, List<Integer> childAges) {
        this.adults = adults;
        this.children = children;
        this.adultAges = adultAges;
        this.childAges = childAges;
    }

    // Getters and setters
    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public List<Integer> getAdultAges() {
        return adultAges;
    }

    public void setAdultAges(List<Integer> adultAges) {
        this.adultAges = adultAges;
    }

    public List<Integer> getChildAges() {
        return childAges;
    }

    public void setChildAges(List<Integer> childAges) {
        this.childAges = childAges;
    }

    @Override
    public String toString() {
        return "RoomRequest{" +
                "adults=" + adults +
                ", children=" + children +
                ", adultAges=" + adultAges +
                ", childAges=" + childAges +
                '}';
    }
}
