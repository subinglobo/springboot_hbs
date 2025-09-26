package com.choosenfly.hotelbookingsystem.agent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.agent.entity.AgentApiExclusion;

@Repository
public interface AgentApiExclusionRepository extends JpaRepository<AgentApiExclusion, Long> {

    /**
     * Find all API exclusions for a specific agent
     * @param agentId The agent ID
     * @return List of AgentApiExclusion
     */
    @Query("SELECT aae FROM AgentApiExclusion aae WHERE aae.agent.id = :agentId")
    List<AgentApiExclusion> findByAgentId(@Param("agentId") Long agentId);

    /**
     * Find all excluded APIs for a specific agent
     * @param agentId The agent ID
     * @return List of AgentApiExclusion where isExcluded = true
     */
    @Query("SELECT aae FROM AgentApiExclusion aae WHERE aae.agent.id = :agentId AND aae.isExcluded = true")
    List<AgentApiExclusion> findExcludedApisByAgentId(@Param("agentId") Long agentId);

    /**
     * Find specific exclusion by agent ID and API code
     * @param agentId The agent ID
     * @param apiCode The API code
     * @return Optional AgentApiExclusion
     */
    @Query("SELECT aae FROM AgentApiExclusion aae WHERE aae.agent.id = :agentId AND aae.externalApi.apiCode = :apiCode")
    Optional<AgentApiExclusion> findByAgentIdAndApiCode(@Param("agentId") Long agentId, @Param("apiCode") String apiCode);

    /**
     * Check if API is excluded for an agent
     * @param agentId The agent ID
     * @param apiCode The API code
     * @return true if excluded, false otherwise
     */
    @Query("SELECT COUNT(e) > 0 FROM AgentApiExclusion e WHERE e.agent.id = :agentId AND e.externalApi.apiCode = :apiCode AND e.isExcluded = true")
    boolean isApiExcludedForAgent(@Param("agentId") Long agentId, @Param("apiCode") String apiCode);

    /**
     * Check if any exclusions exist for a specific external API
     * @param externalApiId The external API ID
     * @return true if exclusions exist, false otherwise
     */
    boolean existsByExternalApiId(Long externalApiId);

    /**
     * Get excluded API codes for an agent
     * @param agentId The agent ID
     * @return List of excluded API codes
     */
    @Query("SELECT aae.externalApi.apiCode FROM AgentApiExclusion aae WHERE aae.agent.id = :agentId AND aae.isExcluded = true")
    List<String> getExcludedApiCodesForAgent(@Param("agentId") Long agentId);

    /**
     * Find exclusions by external API ID
     * @param externalApiId The external API ID
     * @return List of AgentApiExclusion
     */
    @Query("SELECT aae FROM AgentApiExclusion aae WHERE aae.externalApi.id = :externalApiId")
    List<AgentApiExclusion> findByExternalApiId(@Param("externalApiId") Long externalApiId);

    /**
     * Delete exclusion by agent ID and API code
     * @param agentId The agent ID
     * @param apiCode The API code
     */
    @Query("DELETE FROM AgentApiExclusion aae WHERE aae.agent.id = :agentId AND aae.externalApi.apiCode = :apiCode")
    void deleteByAgentIdAndApiCode(@Param("agentId") Long agentId, @Param("apiCode") String apiCode);
}
