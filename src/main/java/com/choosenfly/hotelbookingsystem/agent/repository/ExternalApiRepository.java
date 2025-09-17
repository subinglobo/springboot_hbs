package com.choosenfly.hotelbookingsystem.agent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.agent.entity.ExternalApi;

@Repository
public interface ExternalApiRepository extends JpaRepository<ExternalApi, Long> {

    /**
     * Find external API by API code
     * @param apiCode The API code
     * @return Optional ExternalApi
     */
    Optional<ExternalApi> findByApiCode(String apiCode);

    /**
     * Find all active external APIs
     * @return List of active ExternalApi
     */
    @Query("SELECT ea FROM ExternalApi ea WHERE ea.isActive = true")
    List<ExternalApi> findAllActiveApis();

    /**
     * Find all active external APIs (excluding inhouse)
     * @return List of active external ExternalApi
     */
    @Query("SELECT ea FROM ExternalApi ea WHERE ea.isActive = true AND ea.isExternal = true")
    List<ExternalApi> findAllActiveExternalApis();

    /**
     * Check if API code exists
     * @param apiCode The API code
     * @return true if exists, false otherwise
     */
    boolean existsByApiCode(String apiCode);

    /**
     * Find APIs by active status and external flag
     * @param isActive Active status
     * @param isExternal External flag
     * @return List of ExternalApi
     */
    List<ExternalApi> findByIsActiveAndIsExternal(Boolean isActive, Boolean isExternal);
}
