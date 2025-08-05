package com.choosenfly.hotelbookingsystem.masters.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_country", schema = "public")
public class MasterCountry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    @SequenceGenerator(name = "country_seq", sequenceName = "con_country_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "market_type_id")
    private MasterMarketType marketType;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private MasterRegion region;

    @Column(name = "country_code", length = 100)
    private String countryCode;

    @Column(name = "wht_code", length = 100)
    private String whtCode;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
    public MasterMarketType getMarketType() { return marketType; }
    public void setMarketType(MasterMarketType marketType) { this.marketType = marketType; }
    public MasterRegion getRegion() { return region; }
    public void setRegion(MasterRegion region) { this.region = region; }
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    public String getWhtCode() { return whtCode; }
    public void setWhtCode(String whtCode) { this.whtCode = whtCode; }
}