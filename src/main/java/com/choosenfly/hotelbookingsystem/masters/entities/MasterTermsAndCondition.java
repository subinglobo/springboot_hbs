package com.choosenfly.hotelbookingsystem.masters.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Master_terms_And_condition", schema = "public")
public class MasterTermsAndCondition extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_and_conditions_id")
    private Long termsAndConditionsId;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private MasterCountry country;
    
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private MasterState state;

    @Column(name = "description")
    @NotBlank(message = "cannot be null or empty")
    private String description;

    @Column(name = "description_type")
    @NotBlank(message = "cannot be null or empty")
    private Integer descriptionType;

    @Column(name = "tag_line")
    private String tagline;

    @Column(name = "terms_code")
    @NotBlank(message = "cannot be null or empty")
    private String termsCode;

	public Long getTermsAndConditionsId() {
		return termsAndConditionsId;
	}

	public void setTermsAndConditionsId(Long termsAndConditionsId) {
		this.termsAndConditionsId = termsAndConditionsId;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	public MasterState getState() {
		return state;
	}

	public void setState(MasterState state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(Integer descriptionType) {
		this.descriptionType = descriptionType;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTermsCode() {
		return termsCode;
	}

	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	}

	@Override
	public String toString() {
		return "MasterTermsAndCondition [termsAndConditionsId=" + termsAndConditionsId + ", country=" + country
				+ ", state=" + state + ", description=" + description + ", descriptionType=" + descriptionType
				+ ", tagline=" + tagline + ", termsCode=" + termsCode + "]";
	}
    
    
}
