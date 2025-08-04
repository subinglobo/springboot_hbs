package com.choosenfly.hotelbookingsystem.dto.hotel.stopsale;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class StopSaleDTO {

    private Long stopSaleId;
    
    @NotNull(message = "Hotel ID must not be null")
    @JsonProperty("hotelId")
    private Long hotelId;

    @NotNull(message = "Market Type ID must not be null")
    private Long marketTypeId;

    @NotNull(message = "Room Category ID must not be null")
    private Long roomCategoryId;
    
    private List<StopSaleValidityDTO> stopSaleValidityDTO;

    private Boolean roomAllocation;
    
    private Boolean block;
    
    private Boolean freeSale;

    private Boolean isLive;

	public Long getStopSaleId() {
		return stopSaleId;
	}

	public void setStopSaleId(Long stopSaleId) {
		this.stopSaleId = stopSaleId;
	}

	public Long getMarketTypeId() {
		return marketTypeId;
	}

	public void setMarketTypeId(Long marketTypeId) {
		this.marketTypeId = marketTypeId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public List<StopSaleValidityDTO> getStopSaleValidityDTO() {
		return stopSaleValidityDTO;
	}

	public void setStopSaleValidityDTO(List<StopSaleValidityDTO> stopSaleValidityDTO) {
		this.stopSaleValidityDTO = stopSaleValidityDTO;
	}

	public Boolean getRoomAllocation() {
		return roomAllocation;
	}

	public void setRoomAllocation(Boolean roomAllocation) {
		this.roomAllocation = roomAllocation;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	public Boolean getFreeSale() {
		return freeSale;
	}

	public void setFreeSale(Boolean freeSale) {
		this.freeSale = freeSale;
	}

	

	public Long getRoomCategoryId() {
		return roomCategoryId;
	}

	public void setRoomCategoryId(Long roomCategoryId) {
		this.roomCategoryId = roomCategoryId;
	}

	
	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	
	@Override
	public String toString() {
		return "StopSaleDTO [stopSaleId=" + stopSaleId + ", hotelId=" + hotelId + ", marketTypeId=" + marketTypeId
				+ ", roomCategoryId=" + roomCategoryId + ", stopSaleValidityDTO=" + stopSaleValidityDTO
				+ ", roomAllocation=" + roomAllocation + ", block=" + block + ", freeSale=" + freeSale + ", isLive="
				+ isLive + "]";
	}
    
    
    
    
}
