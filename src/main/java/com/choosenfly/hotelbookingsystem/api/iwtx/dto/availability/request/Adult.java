package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request;

import jakarta.xml.bind.annotation.XmlElement;

public class Adult {
    
    private String age;
    
    @XmlElement(name = "Age")
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
}
