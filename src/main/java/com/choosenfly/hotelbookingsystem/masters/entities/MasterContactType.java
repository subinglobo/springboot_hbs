package com.choosenfly.hotelbookingsystem.masters.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_contact_type", schema = "public")
public class MasterContactType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacttype_id", nullable = false)
    private Long contacttypeId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // Getters and Setters
    public Long getContacttypeId() {
        return contacttypeId;
    }

    public void setContacttypeId(Long contacttypeId) {
        this.contacttypeId = contacttypeId;
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

	@Override
	public String toString() {
		return "MasterContactType [contacttypeId=" + contacttypeId + ", name=" + name + ", isDeleted=" + isDeleted
				+ "]";
	}
    
    
}