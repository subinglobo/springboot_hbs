package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class BlackOut {
    
    private String status;
    private String msg;
    
    @XmlElement(name = "Status")
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @XmlElement(name = "Msg")
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
