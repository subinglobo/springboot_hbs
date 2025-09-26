package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class ProfileResponse {
    
    private String tokenNumber;
    private String iata;
    private String companyClientCode;
    private String subClientId;
    
    @XmlElement(name = "TokenNumber")
    public String getTokenNumber() {
        return tokenNumber;
    }
    
    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }
    
    @XmlElement(name = "IATA")
    public String getIata() {
        return iata;
    }
    
    public void setIata(String iata) {
        this.iata = iata;
    }
    
    @XmlElement(name = "CompanyClientCode")
    public String getCompanyClientCode() {
        return companyClientCode;
    }
    
    public void setCompanyClientCode(String companyClientCode) {
        this.companyClientCode = companyClientCode;
    }
    
    @XmlElement(name = "Sub_x0020_Client_x0020_Id")
    public String getSubClientId() {
        return subClientId;
    }
    
    public void setSubClientId(String subClientId) {
        this.subClientId = subClientId;
    }
}
