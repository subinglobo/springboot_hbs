package com.choosenfly.hotelbookingsystem.api.hotelroom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkupTypeDTO {
    
    private Long markupTypeId;
    private BigDecimal markup;
    private String markupType; // "Percent" or "Fixed"
    private String description;
    private Boolean isActive;
}
