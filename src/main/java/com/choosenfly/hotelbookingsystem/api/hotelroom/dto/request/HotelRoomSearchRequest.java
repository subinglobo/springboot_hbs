package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.util.List;

/**
 * Request DTO for hotel room search API
 */
public class HotelRoomSearchRequest {

    @NotBlank(message = "Check-in date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Check-in date must be in YYYY-MM-DD format")
    private String checkInDate;

    @NotBlank(message = "Check-out date is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Check-out date must be in YYYY-MM-DD format")
    private String checkOutDate;

    @NotBlank(message = "Hotel code is required")
    private String hotelCode;

    @NotEmpty(message = "At least one room is required")
    @Valid
    private List<RoomRequest> rooms;

    @NotBlank(message = "Nationality is required")
    @Pattern(regexp = "[A-Z]{2}", message = "Nationality must be a 2-letter ISO country code")
    private String nationality;

    private String agentId;

    @NotNull(message = "API ID is required")
    @Positive(message = "API ID must be positive")
    private Integer apiId;

    // Constructors
    public HotelRoomSearchRequest() {}

    public HotelRoomSearchRequest(String checkInDate, String checkOutDate, String hotelCode,
                                  List<RoomRequest> rooms, String nationality, String agentId, Integer apiId) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotelCode = hotelCode;
        this.rooms = rooms;
        this.nationality = nationality;
        this.agentId = agentId;
        this.apiId = apiId;
    }

    // Getters and Setters
    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public List<RoomRequest> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomRequest> rooms) {
        this.rooms = rooms;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    @Override
    public String toString() {
        return "HotelRoomSearchRequest{" +
                "checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", hotelCode='" + hotelCode + '\'' +
                ", rooms=" + rooms +
                ", nationality='" + nationality + '\'' +
                ", agentId='" + agentId + '\'' +
                ", apiId=" + apiId +
                '}';
    }
}
