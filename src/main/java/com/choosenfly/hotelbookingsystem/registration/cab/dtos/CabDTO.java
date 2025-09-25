package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

import java.util.List;

public class CabDTO {

    private Long cabId;
    private String name;
    private Long cabprovider;
    private String cabCode;
    private String cabpic;
    private Long countryid;
    private Long placeid;
    private String providername;
    private List<CabLocationDTO> cabLocationDTOList;
  
	public Long getCabId() {
		return cabId;
	}
	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCabprovider() {
		return cabprovider;
	}
	public void setCabprovider(Long cabprovider) {
		this.cabprovider = cabprovider;
	}
	public String getCabCode() {
		return cabCode;
	}
	public void setCabCode(String cabCode) {
		this.cabCode = cabCode;
	}
	public String getCabpic() {
		return cabpic;
	}
	public void setCabpic(String cabpic) {
		this.cabpic = cabpic;
	}
	public Long getCountryid() {
		return countryid;
	}
	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}
	public Long getPlaceid() {
		return placeid;
	}
	public void setPlaceid(Long placeid) {
		this.placeid = placeid;
	}
	public String getProvidername() {
		return providername;
	}
	public void setProvidername(String providername) {
		this.providername = providername;
	}
	public List<CabLocationDTO> getCabLocationDTOList() {
		return cabLocationDTOList;
	}
	public void setCabLocationDTOList(List<CabLocationDTO> cabLocationDTOList) {
		this.cabLocationDTOList = cabLocationDTOList;
	}
	@Override
	public String toString() {
		return "CabDTO [cabId=" + cabId + ", name=" + name + ", cabprovider=" + cabprovider + ", cabCode=" + cabCode
				+ ", cabpic=" + cabpic + ", countryid=" + countryid + ", placeid=" + placeid + ", providername="
				+ providername + ", cabLocationDTOList=" + cabLocationDTOList + "]";
	}
	
	
	
    
}
