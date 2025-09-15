package com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response;

import jakarta.xml.bind.annotation.XmlElement;

public class Message {
    
    private String id;
    private String messageShort;
    private String messageFull;
    private String type;
    private String taxInclusive;
    private String messageChargeBase;
    private String value;
    private String valueType;
    private String ageFrom;
    private String ageTo;
    private String occupancy;
    
    @XmlElement(name = "Id")
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @XmlElement(name = "MessageShort")
    public String getMessageShort() {
        return messageShort;
    }
    
    public void setMessageShort(String messageShort) {
        this.messageShort = messageShort;
    }
    
    @XmlElement(name = "MessageFull")
    public String getMessageFull() {
        return messageFull;
    }
    
    public void setMessageFull(String messageFull) {
        this.messageFull = messageFull;
    }
    
    @XmlElement(name = "Type")
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @XmlElement(name = "TaxInclusive")
    public String getTaxInclusive() {
        return taxInclusive;
    }
    
    public void setTaxInclusive(String taxInclusive) {
        this.taxInclusive = taxInclusive;
    }
    
    @XmlElement(name = "MessageChargeBase")
    public String getMessageChargeBase() {
        return messageChargeBase;
    }
    
    public void setMessageChargeBase(String messageChargeBase) {
        this.messageChargeBase = messageChargeBase;
    }
    
    @XmlElement(name = "Value")
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @XmlElement(name = "ValueType")
    public String getValueType() {
        return valueType;
    }
    
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
    
    @XmlElement(name = "AgeFrom")
    public String getAgeFrom() {
        return ageFrom;
    }
    
    public void setAgeFrom(String ageFrom) {
        this.ageFrom = ageFrom;
    }
    
    @XmlElement(name = "AgeTo")
    public String getAgeTo() {
        return ageTo;
    }
    
    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }
    
    @XmlElement(name = "Occupancy")
    public String getOccupancy() {
        return occupancy;
    }
    
    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }
}
