package com.choosenfly.hotelbookingsystem.auth.enitities.token;

import java.time.Instant;
import java.util.UUID;

import com.choosenfly.hotelbookingsystem.auth.enitities.user.UserAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "token_hash", nullable = false, unique = true, length = 128)
    private String tokenHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "revoked", nullable = false)
    private boolean revoked = false;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(name = "replaced_by")
    private UUID replacedBy; // points to new token id when rotated

    @Column(name = "created_by_ip")
    private String createdByIp;

    @Column(name = "user_agent")
    private String userAgent;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTokenHash() {
		return tokenHash;
	}

	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public Instant getRevokedAt() {
		return revokedAt;
	}

	public void setRevokedAt(Instant revokedAt) {
		this.revokedAt = revokedAt;
	}

	public UUID getReplacedBy() {
		return replacedBy;
	}

	public void setReplacedBy(UUID replacedBy) {
		this.replacedBy = replacedBy;
	}

	public String getCreatedByIp() {
		return createdByIp;
	}

	public void setCreatedByIp(String createdByIp) {
		this.createdByIp = createdByIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		return "RefreshToken [id=" + id + ", tokenHash=" + tokenHash + ", user=" + user + ", createdAt=" + createdAt
				+ ", expiresAt=" + expiresAt + ", revoked=" + revoked + ", revokedAt=" + revokedAt + ", replacedBy="
				+ replacedBy + ", createdByIp=" + createdByIp + ", userAgent=" + userAgent + "]";
	}

    
}