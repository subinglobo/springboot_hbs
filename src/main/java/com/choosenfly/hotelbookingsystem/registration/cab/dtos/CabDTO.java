package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

import java.util.List;

public class CabDTO {

    private Long cabId;
    private String name;
    private Long cabprovider;
    private String cabCode;
    private String countryname;
    private String cabpic;
    private String placename;
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
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getCabpic() {
		return cabpic;
	}
	public void setCabpic(String cabpic) {
		this.cabpic = cabpic;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
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
				+ ", countryname=" + countryname + ", cabpic=" + cabpic + ", placename=" + placename + ", countryid="
				+ countryid + ", placeid=" + placeid + ", providername=" + providername + ", cabLocationDTOList="
				+ cabLocationDTOList + "]";
	}
	
    
    
}
