package com.choosenfly.hotelbookingsystem.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // If you have a user system, fetch the logged-in user instead of "System"
        return Optional.of("System");
    }
}