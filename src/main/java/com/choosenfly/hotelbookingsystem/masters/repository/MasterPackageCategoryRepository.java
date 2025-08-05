package com.choosenfly.hotelbookingsystem.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.masters.entities.MasterBank;
import com.choosenfly.hotelbookingsystem.masters.entities.MasterPackageCategory;

@Repository
public interface MasterPackageCategoryRepository extends JpaRepository<MasterPackageCategory, Long>{

}
