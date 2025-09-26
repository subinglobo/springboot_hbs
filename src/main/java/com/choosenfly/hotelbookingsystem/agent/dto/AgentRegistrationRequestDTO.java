package com.choosenfly.hotelbookingsystem.agent.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AgentRegistrationRequestDTO {
	
	private Long id;

    @NotBlank(message = "Business type is required")
    private String businessType;

    @NotNull(message = "Agent category ID is required")
    @Positive(message = "Agent category ID must be greater than 0")
    private Long agentCategoryId;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Country ID is required")
    @Positive(message = "Country ID must be greater than 0")
    private Long countryId;

    @NotNull(message = "Province ID is required")
    @Positive(message = "Province ID must be greater than 0")
    private Long provinceId;

    @NotNull(message = "Place ID is required")
    @Positive(message = "Place ID must be greater than 0")
    private Long placeId;


    @NotBlank(message = "Personal email is required")
    @Email(message = "Invalid email format")
    private String personalEmail;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;

    private AgentGSTDetailsDTO agentGSTDetailsDTO;
    
    private String shortName;
    
    private String companyCode;
    
    private String agentUrl;
    
    private String zipCode;
    
    private String contactPerson;
    
    private String markup;
    
    private Integer currency;  //currencyId
    
    private String status;
    
    // Getters and Setters

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Long getAgentCategoryId() {
        return agentCategoryId;
    }

    public void setAgentCategoryId(Long agentCategoryId) {
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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

    public AgentGSTDetailsDTO getAgentGSTDetailsDTO() {
        return agentGSTDetailsDTO;
    }

    public void setAgentGSTDetailsDTO(AgentGSTDetailsDTO agentGSTDetailsDTO) {
        this.agentGSTDetailsDTO = agentGSTDetailsDTO;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AgentRegistrationRequestDTO [id=" + id + ", businessType=" + businessType + ", agentCategoryId="
				+ agentCategoryId + ", companyName=" + companyName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", countryId=" + countryId + ", provinceId=" + provinceId + ", placeId=" + placeId
				+ ", personalEmail=" + personalEmail + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", agentGSTDetailsDTO=" + agentGSTDetailsDTO + ", shortName=" + shortName + ", companyCode="
				+ companyCode + ", agentUrl=" + agentUrl + ", zipCode=" + zipCode + ", contactPerson=" + contactPerson
				+ ", markup=" + markup + ", currency=" + currency + ", status=" + status + "]";
	}

	

	

	

	
    
}
