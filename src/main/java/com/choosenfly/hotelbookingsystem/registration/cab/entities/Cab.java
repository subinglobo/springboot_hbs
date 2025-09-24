package com.choosenfly.hotelbookingsystem.registration.cab.entities;

import java.util.Date;
import java.util.List;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterCountry;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cab")
public class Cab extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cab_id")
    private Long cabId;

    @Column(name = "cab_code")
    private String cabCode;

    @Column(name = "cabpic")
    private String cabPic;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private MasterCountry country;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private MasterState Place;


    @Column(name = "isActive")
    private Boolean isActive;


    // Many cabs belong to one provider
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabprovider")
    private CabProvider cabProvider;

    // One cab can have many locations
    @OneToMany(mappedBy = "cab", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CabLocation> cabLocations;

    @OneToMany(mappedBy = "cab", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CabRates>cabRates;
    
    
	public Long getCabId() {
		return cabId;
	}




	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}




	public String getCabCode() {
		return cabCode;
	}




	public void setCabCode(String cabCode) {
		this.cabCode = cabCode;
	}




	public String getCabPic() {
		return cabPic;
	}




	public void setCabPic(String cabPic) {
		this.cabPic = cabPic;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}



	



	public MasterCountry getCountry() {
		return country;
	}




	public void setCountry(MasterCountry country) {
		this.country = country;
	}




	public MasterState getPlace() {
		return Place;
	}




	public void setPlace(MasterState place) {
		Place = place;
	}




	public Boolean getIsActive() {
		return isActive;
	}




	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}




	public CabProvider getCabProvider() {
		return cabProvider;
	}




	public void setCabProvider(CabProvider cabProvider) {
		this.cabProvider = cabProvider;
	}




	public List<CabLocation> getCabLocations() {
		return cabLocations;
	}




	public void setCabLocations(List<CabLocation> cabLocations) {
		this.cabLocations = cabLocations;
	}

	


	public List<CabRates> getCabRates() {
		return cabRates;
	}




	public void setCabRates(List<CabRates> cabRates) {
		this.cabRates = cabRates;
	}


	

	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", cabCode=" + cabCode + ", cabPic=" + cabPic + ", name=" + name + ", country="
				+ country + ", Place=" + Place + ", isActive=" + isActive + ", cabProvider=" + cabProvider
				+ ", cabLocations=" + cabLocations + ", cabRates=" + cabRates + "]";
	}
    
    
}