package com.choosenfly.hotelbookingsystem.masters.entities;

import com.choosenfly.hotelbookingsystem.common.base.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "master_markup_type", schema = "public")
public class MasterMarkupType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_type", length = 100)
    private String isType;

    @NotBlank(message="This feild cannot be null")
    @NotNull(message="This feild cannot be null")
    @Column(name = "markup", length = 100)
    private String markup;

    @Enumerated(EnumType.STRING)
    @Column(name = "markup_type", length = 100)
    private Markup markupType;

    @Column(name = "name", length = 100)
    @NotBlank(message="This feild cannot be null")
    @NotNull(message="This feild cannot be null")
    private String name;
    
    //enum
    public enum Markup{
    	Percent,
    	Amount
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsType() {
        return isType;
    }

    public void setIsType(String isType) {
        this.isType = isType;
    }

    public String getMarkup() {
		return markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	public Markup getMarkupType() {
		return markupType;
	}

	public void setMarkupType(Markup markupType) {
		this.markupType = markupType;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}