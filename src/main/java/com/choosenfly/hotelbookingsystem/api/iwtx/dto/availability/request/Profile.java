package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request;

import jakarta.xml.bind.annotation.XmlElement;

public class Profile {
    
    private String password;
    private String code;
    private String tokenNumber;
    
    @XmlElement(name = "Password")
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @XmlElement(name = "Code")
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @XmlElement(name = "TokenNumber")
    public String getTokenNumber() {
        return tokenNumber;
    }
    
    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }
}
