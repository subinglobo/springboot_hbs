package com.choosenfly.hotelbookingsystem.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.master.MasterContactType;

@Repository
public interface MasterContactTypeRepository extends JpaRepository<MasterContactType, Long>{

}
