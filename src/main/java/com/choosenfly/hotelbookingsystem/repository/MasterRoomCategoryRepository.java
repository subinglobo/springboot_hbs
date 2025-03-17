package com.choosenfly.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterRoomCategory;

@Repository
public interface MasterRoomCategoryRepository extends JpaRepository<MasterRoomCategory, Long> {

}
