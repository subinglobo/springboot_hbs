package com.choosenfly.hotelbookingsystem.agent.entity;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPlace;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agent")
public class Agent extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "business_type")
	private String businessType;
	
	@ManyToOne
	@JoinColumn(name = "agent_category_id")
	private AgentCategory agentCategoryId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private MasterCountry country;

	@ManyToOne
	@JoinColumn(name = "province_id")
	private MasterState province;

	@ManyToOne
	@JoinColumn(name = "place_id")
	private MasterPlace place;

	@Column(name = "personal_email")
	private String personalEmail;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "address")
	private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gst_id", referencedColumnName = "id")
    private AgentGSTDetails gstDetails;
    
    @OneToOne(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AgentCreditLimit creditLimit;
    
    @Column(name = "short_name")
    private String shortName;
    
    @Column(name = "company_code")
    private String companyCode;
    
    @Column(name = "agent_url")
    private String agentUrl;
    
    @Column(name = "zip_code")
    private String zipCode;
    
    @Column(name = "contact_person")
    private String contactPerson;
    
    @Column(name = "markup")
    private String markup;
    
    @Column(name = "currency_id")
    private Integer currencyId;
    
    @Column(name = "status")
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	
	public AgentCategory getAgentCategoryId() {
		return agentCategoryId;
	}

	public void setAgentCategoryId(AgentCategory agentCategoryId) {
		this.agentCategoryId = agentCategoryId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	public MasterState getProvince() {
		return province;
	}

	public void setProvince(MasterState province) {
		this.province = province;
	}

	public MasterPlace getPlace() {
		return place;
	}

	public void setPlace(MasterPlace place) {
		this.place = place;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AgentGSTDetails getGstDetails() {
		return gstDetails;
	}

	public void setGstDetails(AgentGSTDetails gstDetails) {
		this.gstDetails = gstDetails;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getAgentUrl() {
		return agentUrl;
	}

	public void setAgentUrl(String agentUrl) {
		this.agentUrl = agentUrl;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) { 
		this.contactPerson = contactPerson;
	}

	public String getMarkup() {
		return markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AgentCreditLimit getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(AgentCreditLimit creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", businessType=" + businessType + ", agentCategoryId=" + agentCategoryId
				+ ", companyName=" + companyName + ", firstName=" + firstName + ", lastName=" + lastName + ", country="
				+ country + ", province=" + province + ", place=" + place + ", personalEmail=" + personalEmail
				+ ", mobileNumber=" + mobileNumber + ", address=" + address + ", gstDetails=" + gstDetails
				+ ", shortName=" + shortName + ", companyCode=" + companyCode + ", agentUrl=" + agentUrl + ", zipCode="
				+ zipCode + ", contactPerson=" + contactPerson + ", markup=" + markup + ", currencyId=" + currencyId
				+ ", status=" + status + "]";
	}

	

    

}