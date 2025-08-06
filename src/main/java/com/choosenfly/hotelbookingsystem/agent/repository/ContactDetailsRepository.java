package com.choosenfly.hotelbookingsystem.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.agent.entity.AgentContactDetails;

public interface ContactDetailsRepository extends JpaRepository<AgentContactDetails, Long> {
}