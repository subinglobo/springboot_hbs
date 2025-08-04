package com.choosenfly.hotelbookingsystem.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.master.entities.MasterBank;
import com.choosenfly.hotelbookingsystem.master.entities.MasterDesignation;

@Repository
public interface MasterDesignationRepository extends JpaRepository<MasterDesignation, Long>{

}
