package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

import java.util.List;

public class CabRateDTO {

    private List<String> marketype;   // ["1", "2"]

    private Long cabId;             // "8"

    private Long cabratesId;        // "5"

    private String rateCode;          // "taxi122"

    private Long cabproviderId;            // nested cabDTO object

    private List<CabRateValidityDTO> cabRateValidityDTOList;   // list of validity DTOs

    private List<CabRateDetailsDTO> cabRateDetailsDTOList;     // list of details DTOs

	public List<String> getMarketype() {
		return marketype;
	}

	public void setMarketype(List<String> marketype) {
		this.marketype = marketype;
	}

	

	

	
	public Long getCabId() {
		return cabId;
	}

	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}

	public Long getCabratesId() {
		return cabratesId;
	}

	public void setCabratesId(Long cabratesId) {
		this.cabratesId = cabratesId;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public Long getCabproviderId() {
		return cabproviderId;
	}

	public void setCabproviderId(Long cabproviderId) {
		this.cabproviderId = cabproviderId;
	}

	public List<CabRateValidityDTO> getCabRateValidityDTOList() {
		return cabRateValidityDTOList;
	}

	public void setCabRateValidityDTOList(List<CabRateValidityDTO> cabRateValidityDTOList) {
		this.cabRateValidityDTOList = cabRateValidityDTOList;
	}

	public List<CabRateDetailsDTO> getCabRateDetailsDTOList() {
		return cabRateDetailsDTOList;
	}

	public void setCabRateDetailsDTOList(List<CabRateDetailsDTO> cabRateDetailsDTOList) {
		this.cabRateDetailsDTOList = cabRateDetailsDTOList;
	}
	
	
	@Override
	public String toString() {
		return "CabRateDTO [marketype=" + marketype + ", cabId=" + cabId + ", cabratesId=" + cabratesId + ", rateCode="
				+ rateCode + ", cabproviderId=" + cabproviderId + ", cabRateValidityDTOList=" + cabRateValidityDTOList
				+ ", cabRateDetailsDTOList=" + cabRateDetailsDTOList + "]";
	}

    // Getters and Setters
    
    
}
