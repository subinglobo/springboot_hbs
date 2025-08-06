package com.choosenfly.hotelbookingsystem.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.agent.entity.AgentGSTDetails;

public interface GSTDetailsRepository extends JpaRepository<AgentGSTDetails, Long> {
}