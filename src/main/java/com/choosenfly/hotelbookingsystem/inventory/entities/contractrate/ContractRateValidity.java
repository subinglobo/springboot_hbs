package com.choosenfly.hotelbookingsystem.inventory.entities.contractrate;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_contractrate_validity")
public class ContractRateValidity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_validity_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "contractrate_id")
    private ContractRate contractRate;
    
    @Column(name="is_live")
    private Boolean isLive;
    
    @Column(name="priority")
    private Integer priority;
    
    @Column(name="validity_from")
    private String validityFrom;
    
    @Column(name="validaity_to")
    private String validityTo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContractRate getContractRate() {
		return contractRate;
	}

	public void setContractRate(ContractRate contractRate) {
		this.contractRate = contractRate;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	

	public String getValidityFrom() {
		return validityFrom;
	}

	public void setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
	}

	public String getValidityTo() {
		return validityTo;
	}

	public void setValidityTo(String validityTo) {
		this.validityTo = validityTo;
	}

	@Override
	public String toString() {
		return "ContractRateValidity [id=" + id + ", contractRate=" + contractRate + ", isLive=" + isLive
				+ ", priority=" + priority + ", validityFrom=" + validityFrom + ", validityTo=" + validityTo + "]";
	}
    
    
    
    // Getters and setters
}
