package com.choosenfly.hotelbookingsystem.dto.masters;

import com.choosenfly.hotelbookingsystem.entities.master.MasterMarkupType.Markup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class MasterMarkupTypeDTO {

    private Long id;

    private Boolean isDeleted;

    private  String  isType;

    @NotNull(message = "Markup cannot be null")
    @NotBlank(message = "Markup cannot be empty")
    private String markup;

    @NotNull(message = "MarkupType cannot be null")
    private Markup markupType;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

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

	
	public String getMarkup() {
		return markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
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

	@Override
	public String toString() {
		return "MasterMarkupTypeDTO [id=" + id + ", isDeleted=" + isDeleted + ", isType=" + isType + ", markup="
				+ markup + ", markupType=" + markupType + ", name=" + name + "]";
	}

    
}