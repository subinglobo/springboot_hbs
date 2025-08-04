package com.choosenfly.hotelbookingsystem.inventory.occupancy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelOccupancyPatchDTO {

	/**
     * Indicates whether the HotelOccupancy is live or not.
     * True means active, false means inactive.
     */
    @NotNull(message = "isLive cannot be null") // Optional: enforce non-null if required
    private Boolean isLive;

    // Default constructor
    public HotelOccupancyPatchDTO() {
    }

    // Constructor with isLive parameter
    public HotelOccupancyPatchDTO(Boolean isLive) {
        this.isLive = isLive;
    }

    // Getter
    public Boolean getIsLive() {
        return isLive;
    }

    // Setter
    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }
}
