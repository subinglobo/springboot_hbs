package com.choosenfly.hotelbookingsystem.agent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.agent.entity.AgentCreditLimit;

@Repository
public interface AgentCreditLimitRepository extends JpaRepository<AgentCreditLimit, Long> {

    /**
     * Find credit limit by agent ID
     * @param agentId The agent ID
     * @return Optional AgentCreditLimit
     */
    @Query("SELECT acl FROM AgentCreditLimit acl WHERE acl.agent.id = :agentId")
    Optional<AgentCreditLimit> findByAgentId(@Param("agentId") Long agentId);

    /**
     * Check if credit limit exists for an agent
     * @param agentId The agent ID
     * @return true if exists, false otherwise
     */
    @Query("SELECT COUNT(acl) > 0 FROM AgentCreditLimit acl WHERE acl.agent.id = :agentId")
    boolean existsByAgentId(@Param("agentId") Long agentId);

    /**
     * Delete credit limit by agent ID
     * @param agentId The agent ID
     */
    @Query("DELETE FROM AgentCreditLimit acl WHERE acl.agent.id = :agentId")
    void deleteByAgentId(@Param("agentId") Long agentId);
}
