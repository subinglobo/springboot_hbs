package com.choosenfly.hotelbookingsystem.registration.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.registration.activity.entities.ActivityInclusionAndTerms;
@Repository
public interface ActivityInclusionAndTermsRepository extends JpaRepository<ActivityInclusionAndTerms, Long>{

}
