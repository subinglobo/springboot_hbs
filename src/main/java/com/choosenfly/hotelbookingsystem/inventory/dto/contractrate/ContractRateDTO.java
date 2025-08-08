package com.choosenfly.hotelbookingsystem.inventory.dto.contractrate;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContractRateDTO {

	@NotNull(message = "Market types cannot be null")
	@Size(min = 1, message = "At least one market type must be specified")
    private List<Long> markeType;
    
    private List<Long> excludeCountry;
    
    @NotNull(message = "Hotel ID is required")
    private Long hotelId;
    
    @NotNull(message = "season is required")
    private Long seasonId;
    
    private Long contractrateId;
    
    @NotBlank(message = "rateCode is required")
    private String rateCode;
    
    private Boolean weekDay= false;
    
    private Boolean weekEndDay= false;
    
    private Boolean allDays= false;
    
    private Boolean isLive=false;
    
    private List<ContractRateValidityDTO> contractRateValidityDTO;
    
 //   private List<ContractRateCompulsoryDTO> contractRateCompulsoryDTO;
    
    private List<ContractRateRoomDetailsDTO> contractRateRoomDTO;
    

	

	public List<Long> getMarkeType() {
		return markeType;
	}

	public void setMarkeType(List<Long> markeType) {
		this.markeType = markeType;
	}

	public List<Long> getExcludeCountry() {
		return excludeCountry;
	}

	public void setExcludeCountry(List<Long> excludeCountry) {
		this.excludeCountry = excludeCountry;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public Long getContractrateId() {
		return contractrateId;
	}

	public void setContractrateId(Long contractrateId) {
		this.contractrateId = contractrateId;
	}

	public List<ContractRateRoomDetailsDTO> getContractRateRoomDTO() {
		return contractRateRoomDTO;
	}

	public void setContractRateRoomDTO(List<ContractRateRoomDetailsDTO> contractRateRoomDTO) {
		this.contractRateRoomDTO = contractRateRoomDTO;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	

	public Boolean getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Boolean weekDay) {
		this.weekDay = weekDay;
	}

	public Boolean getWeekEndDay() {
		return weekEndDay;
	}

	public void setWeekEndDay(Boolean weekEndDay) {
		this.weekEndDay = weekEndDay;
	}

	public Boolean getAllDays() {
		return allDays;
	}

	public void setAllDays(Boolean allDays) {
		this.allDays = allDays;
	}

	public List<ContractRateValidityDTO> getContractRateValidityDTO() {
		return contractRateValidityDTO;
	}

	public void setContractRateValidityDTO(List<ContractRateValidityDTO> contractRateValidityDTO) {
		this.contractRateValidityDTO = contractRateValidityDTO;
	}
	
	

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	
	@Override
	public String toString() {
		return "ContractRateDTO [markeType=" + markeType + ", excludeCountry=" + excludeCountry + ", hotelId=" + hotelId
				+ ", seasonId=" + seasonId + ", contractrateId=" + contractrateId + ", rateCode=" + rateCode
				+ ", weekDay=" + weekDay + ", weekEndDay=" + weekEndDay + ", allDays=" + allDays + ", isLive=" + isLive
				+ ", contractRateValidityDTO=" + contractRateValidityDTO + ", contractRateRoomDTO="
				+ contractRateRoomDTO + "]";
	}

	
    
    
}
