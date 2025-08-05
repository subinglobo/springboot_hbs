package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterMailType;

@Repository
public interface HotelMailCentreRepository extends JpaRepository<MasterMailType,Long> {

}
