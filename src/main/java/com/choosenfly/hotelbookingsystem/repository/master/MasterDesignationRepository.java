package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterBank;
import com.choosenfly.hotelbookingsystem.entities.master.MasterDesignation;

@Repository
public interface MasterDesignationRepository extends JpaRepository<MasterDesignation, Long>{

}
