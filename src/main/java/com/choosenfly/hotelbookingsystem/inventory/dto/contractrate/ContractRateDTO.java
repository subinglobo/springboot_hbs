package com.choosenfly.hotelbookingsystem.inventory.dto.contractrate;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContractRateDTO {

	@NotNull(message = "Market types cannot be null")
	@Size(min = 1, message = "At least one market type must be specified")
    private List<Long> marketype;
    
    private List<Long> exclude_country;
    
    @NotNull(message = "Hotel ID is required")
    private Long hotel_id;
    
    @NotNull(message = "season is required")
    private Long season_id;
    
    private Long contractrate_id;
    
    @NotBlank(message = "rateCode is required")
    private String rateCode;
    
    private Boolean weekDay= false;
    
    private Boolean weekEndDay= false;
    
    private Boolean allDays= false;
    
    private Boolean isLive=false;
    
    private List<ContractRateValidityDTO> contractRateValidityDTO;
    
 //   private List<ContractRateCompulsoryDTO> contractRateCompulsoryDTO;
    
    private List<ContractRateRoomDetailsDTO> contractRateRoomDTO;
    

	public List<Long> getMarketype() {
		return marketype;
	}

	public void setMarketype(List<Long> marketype) {
		this.marketype = marketype;
	}

	public List<Long> getExclude_country() {
		return exclude_country;
	}

	public void setExclude_country(List<Long> exclude_country) {
		this.exclude_country = exclude_country;
	}

	

	public Long getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}

	public List<ContractRateRoomDetailsDTO> getContractRateRoomDTO() {
		return contractRateRoomDTO;
	}

	public void setContractRateRoomDTO(List<ContractRateRoomDetailsDTO> contractRateRoomDTO) {
		this.contractRateRoomDTO = contractRateRoomDTO;
	}

	

	

	public Long getSeason_id() {
		return season_id;
	}

	public void setSeason_id(Long season_id) {
		this.season_id = season_id;
	}

	public Long getContractrate_id() {
		return contractrate_id;
	}

	public void setContractrate_id(Long contractrate_id) {
		this.contractrate_id = contractrate_id;
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
		return "ContractRateDTO [marketype=" + marketype + ", exclude_country=" + exclude_country + ", hotel_id="
				+ hotel_id + ", season_id=" + season_id + ", contractrate_id=" + contractrate_id + ", rateCode="
				+ rateCode + ", weekDay=" + weekDay + ", weekEndDay=" + weekEndDay + ", allDays=" + allDays
				+ ", isLive=" + isLive + ", contractRateValidityDTO=" + contractRateValidityDTO
				+ ", contractRateRoomDTO=" + contractRateRoomDTO + "]";
	}

	
    
    
}
