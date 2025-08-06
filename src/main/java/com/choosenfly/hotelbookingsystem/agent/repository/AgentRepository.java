package com.choosenfly.hotelbookingsystem.agent.repository;

import com.choosenfly.hotelbookingsystem.agent.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    boolean existsByPersonalEmail(String personalEmail);
}