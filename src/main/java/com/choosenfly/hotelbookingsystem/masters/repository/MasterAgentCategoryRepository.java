package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterAgentCategory;

@Repository
public interface MasterAgentCategoryRepository extends JpaRepository<MasterAgentCategory, Long>{

}
