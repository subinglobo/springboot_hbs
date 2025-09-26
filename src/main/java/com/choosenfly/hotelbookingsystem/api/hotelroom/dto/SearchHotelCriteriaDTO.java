package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.SearchRoomDTO;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHotelCriteriaDTO {
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    
    private String hotelCode; // Format like "IN108" where 108 is hotelId
    private String nationality; // e.g., "IN"
    private String agentId;
    private int apiId; // If 1, trigger in-house search
    private List<SearchRoomDTO> rooms; // Each with adults, children, adultAges, childAges
    
    // Additional fields
    private Long nativeCountryId;
    private Long marketId;
    private String currencyCode;
    private String sessionId;
    
    // Helper method to extract hotelId from hotelCode
    public Long getHotelId() {
        if (hotelCode != null && hotelCode.startsWith("IN")) {
            try {
                return Long.parseLong(hotelCode.substring(2));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
