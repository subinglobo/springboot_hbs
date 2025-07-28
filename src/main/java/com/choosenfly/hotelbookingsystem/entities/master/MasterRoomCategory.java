package com.choosenfly.hotelbookingsystem.entities.master;

import com.choosenfly.hotelbookingsystem.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "master_room_category", schema = "public")
public class MasterRoomCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_category_id", nullable = false)
    private Long roomCategoryId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "category_code", length = 100)
    private String categoryCode;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // Getters and Setters
    public Long getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(Long roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}