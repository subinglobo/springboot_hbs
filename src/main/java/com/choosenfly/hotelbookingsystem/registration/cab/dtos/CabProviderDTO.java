package com.choosenfly.hotelbookingsystem.registration.cab.dtos;

import java.util.List;

public class CabProviderDTO {

    private Long cabprovider;
    private String providername;
    private String phonenumber;
    private String emailid;
    private String contactperson;
    private List<CabDTO> cabList;
    private boolean active;
	public Long getCabprovider() {
		return cabprovider;
	}
	public void setCabprovider(Long cabprovider) {
		this.cabprovider = cabprovider;
	}
	public String getProvidername() {
		return providername;
	}
	public void setProvidername(String providername) {
		this.providername = providername;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public List<CabDTO> getCabList() {
		return cabList;
	}
	public void setCabList(List<CabDTO> cabList) {
		this.cabList = cabList;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "CabProviderDTO [cabprovider=" + cabprovider + ", providername=" + providername + ", phonenumber="
				+ phonenumber + ", emailid=" + emailid + ", contactperson=" + contactperson + ", cabList=" + cabList
				+ ", active=" + active + "]";
	}
    
    
    
}
