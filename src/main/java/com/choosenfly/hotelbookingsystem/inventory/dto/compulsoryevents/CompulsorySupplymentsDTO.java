package com.choosenfly.hotelbookingsystem.inventory.dto.compulsoryevents;

import java.util.List;

public class CompulsorySupplymentsDTO {

    private Long supplymentId;
    
    private String supplymentCode;
    
    private String supplyments;
    
    private Long hotelId;

    private List<Long> marketypeIds;
    
    private List<CompulsorySupplymentsRateDTO> compulsorySupplymentsRateDTO;
    
    private List<CompulsorySupplyValidityDTO> compulsorySupplyValidityDTO;

    

	public Long getSupplymentId() {
		return supplymentId;
	}

	public void setSupplymentId(Long supplymentId) {
		this.supplymentId = supplymentId;
	}

	

	public String getSupplymentCode() {
		return supplymentCode;
	}

	public void setSupplymentCode(String supplymentCode) {
		this.supplymentCode = supplymentCode;
	}

	public String getSupplyments() {
		return supplyments;
	}

	public void setSupplyments(String supplyments) {
		this.supplyments = supplyments;
	}

	

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	
	public List<Long> getMarketypeIds() {
		return marketypeIds;
	}

	public void setMarketypeIds(List<Long> marketypeIds) {
		this.marketypeIds = marketypeIds;
	}

	public List<CompulsorySupplymentsRateDTO> getCompulsorySupplymentsRateDTO() {
		return compulsorySupplymentsRateDTO;
	}

	public void setCompulsorySupplymentsRateDTO(List<CompulsorySupplymentsRateDTO> compulsorySupplymentsRateDTO) {
		this.compulsorySupplymentsRateDTO = compulsorySupplymentsRateDTO;
	}

	public List<CompulsorySupplyValidityDTO> getCompulsorySupplyValidityDTO() {
		return compulsorySupplyValidityDTO;
	}

	public void setCompulsorySupplyValidityDTO(List<CompulsorySupplyValidityDTO> compulsorySupplyValidityDTO) {
		this.compulsorySupplyValidityDTO = compulsorySupplyValidityDTO;
	}

	
	
	@Override
	public String toString() {
		return "CompulsorySupplymentsDTO [supplymentId=" + supplymentId + ", supplymentCode=" + supplymentCode
				+ ", supplyments=" + supplyments + ", hotelId=" + hotelId + ", marketypeIds=" + marketypeIds
				+ ", compulsorySupplymentsRateDTO=" + compulsorySupplymentsRateDTO + ", compulsorySupplyValidityDTO="
				+ compulsorySupplyValidityDTO + "]";
	}
    
    
    
}
