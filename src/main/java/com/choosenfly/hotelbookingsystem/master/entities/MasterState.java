package com.choosenfly.hotelbookingsystem.master.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_state", schema = "public")
public class MasterState extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private MasterCountry country;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "wht_code", length = 100)
    private String whtCode;

    @Column(name = "smy_code", length = 100)
    private String smyCode;

    @Column(name = "atharva_code", length = 100)
    private String atharvaCode;

    @Column(name = "state_code", length = 100)
    private String stateCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MasterCountry getCountry() {
		return country;
	}

	public void setCountry(MasterCountry country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getWhtCode() {
		return whtCode;
	}

	public void setWhtCode(String whtCode) {
		this.whtCode = whtCode;
	}

	public String getSmyCode() {
		return smyCode;
	}

	public void setSmyCode(String smyCode) {
		this.smyCode = smyCode;
	}

	public String getAtharvaCode() {
		return atharvaCode;
	}

	public void setAtharvaCode(String atharvaCode) {
		this.atharvaCode = atharvaCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public String toString() {
		return "MasterState [id=" + id + ", country=" + country + ", name=" + name + ", isDeleted=" + isDeleted
				+ ", whtCode=" + whtCode + ", smyCode=" + smyCode + ", atharvaCode=" + atharvaCode + ", stateCode="
				+ stateCode + "]";
	}

   
}