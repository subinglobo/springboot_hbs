package com.choosenfly.hotelbookingsystem.inventory.hotel.dto;

public class HotelBankDetailsDTO {
    
	private Long id;
    private Long hotelId; // Reference to Hotel
    private Long bankId; // Reference to MasterBank
    private String accountNo;
    private String iban;
    private String swiftCode;
    private String bankAddress;
    private String telephone;
    private String faxNumber;
    private String contactPerson;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public String toString() {
        return "HotelBankDetailsDTO [id=" + id + ", hotelId=" + hotelId + ", bankId=" + bankId + ", accountNo=" + accountNo
                + ", iban=" + iban + ", swiftCode=" + swiftCode + ", bankAddress=" + bankAddress + ", telephone=" + telephone
                + ", faxNumber=" + faxNumber + ", contactPerson=" + contactPerson + "]";
    }
}