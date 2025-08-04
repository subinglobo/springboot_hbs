package com.choosenfly.hotelbookingsystem.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.master.entities.MasterBank;

@Repository
public interface MasterBankRepository extends JpaRepository<MasterBank, Long>{

}
