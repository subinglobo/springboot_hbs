package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request;

import java.util.List;

/**
 * DTO for room search criteria
 */
public class SearchRoomDTO {
    private int adults;
    private int children;
    private List<Integer> adultAges;
    private List<Integer> childAges;

    // Constructors
    public SearchRoomDTO() {}

    public SearchRoomDTO(int adults, int children, List<Integer> adultAges, List<Integer> childAges) {
        this.adults = adults;
        this.children = children;
        this.adultAges = adultAges;
        this.childAges = childAges;
    }

    // Getters and Setters
    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
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
}
