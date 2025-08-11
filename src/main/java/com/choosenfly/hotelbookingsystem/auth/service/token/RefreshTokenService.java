package com.choosenfly.hotelbookingsystem.auth.service.token;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choosenfly.hotelbookingsystem.auth.enitities.token.RefreshToken;
import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;
import com.choosenfly.hotelbookingsystem.auth.repository.token.RefreshTokenRepository;
import com.choosenfly.hotelbookingsystem.auth.util.token.TokenUtils;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final TokenUtils tokenUtils;
    private final long refreshTokenValiditySeconds;

    public RefreshTokenService(RefreshTokenRepository repo,
                               TokenUtils tokenUtils,
                               @Value("${app.refresh.token.expiry-seconds:604800}") long refreshTokenValiditySeconds) { // default 7 days
        this.repo = repo;
        this.tokenUtils = tokenUtils;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    /** Create refresh token record and return raw token to send to client (cookie). */
    @Transactional
    public String createRefreshToken(UserAccount user, String createdByIp, String userAgent) {
        String rawToken = tokenUtils.generateSecureToken();
        String hash = tokenUtils.hash(rawToken);

        RefreshToken entity = new RefreshToken();
        entity.setTokenHash(hash);
        entity.setUser(user);
        entity.setCreatedAt(Instant.now());
        entity.setExpiresAt(Instant.now().plusSeconds(refreshTokenValiditySeconds));
        entity.setRevoked(false);
        entity.setCreatedByIp(createdByIp);
        entity.setUserAgent(userAgent);

        repo.save(entity);
        return rawToken;
    }

    /** Validate raw token, return the DB entity if valid (not expired & not revoked). */
    @Transactional(readOnly = true)
    public Optional<RefreshToken> validateRawToken(String rawToken) {
        String hash = tokenUtils.hash(rawToken);
        return repo.findByTokenHash(hash)
                   .filter(rt -> !rt.isRevoked())
                   .filter(rt -> rt.getExpiresAt().isAfter(Instant.now()));
    }

    /** Rotate refresh token: revoke old token, create new token, link replacedBy, return raw new token. */
    @Transactional
    public String rotate(String oldRawToken, String createdByIp, String userAgent) {
        String oldHash = tokenUtils.hash(oldRawToken);
        RefreshToken old = repo.findByTokenHash(oldHash)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));

        if (old.isRevoked() || old.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalStateException("Refresh token invalid");
        }

        // create new token
        String newRaw = tokenUtils.generateSecureToken();
        String newHash = tokenUtils.hash(newRaw);

        RefreshToken newEntity = new RefreshToken();
        newEntity.setTokenHash(newHash);
        newEntity.setUser(old.getUser());
        newEntity.setCreatedAt(Instant.now());
        newEntity.setExpiresAt(Instant.now().plusSeconds(refreshTokenValiditySeconds));
        newEntity.setRevoked(false);
        newEntity.setCreatedByIp(createdByIp);
        newEntity.setUserAgent(userAgent);

        repo.save(newEntity);

        // revoke old and link
        old.setRevoked(true);
        old.setRevokedAt(Instant.now());
        old.setReplacedBy(newEntity.getId());
        repo.save(old);

        return newRaw;
    }

    /** Revoke token by raw value (logout). */
    @Transactional
    public void revokeByRawToken(String rawToken) {
        String hash = tokenUtils.hash(rawToken);
        repo.findByTokenHash(hash).ifPresent(rt -> {
            rt.setRevoked(true);
            rt.setRevokedAt(Instant.now());
            repo.save(rt);
        });
    }

    /** Optional: revoke all tokens for user (logout everywhere). */
    @Transactional
    public void revokeAllForUser(UserAccount user) {
        repo.deleteByUser(user);
    }
}