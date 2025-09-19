package com.choosenfly.hotelbookingsystem.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.choosenfly.hotelbookingsystem.common.error.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class ExceptionHandlingFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            if (!handleException(response, ex)) {
                // If we didn't handle the exception, re-throw it
                if (ex instanceof ServletException) {
                    throw (ServletException) ex;
                } else if (ex instanceof IOException) {
                    throw (IOException) ex;
                } else {
                    throw new ServletException(ex);
                }
            }
        }
    }

    private boolean handleException(HttpServletResponse response, Exception ex) throws IOException {
        // Check if it's any of our custom business exceptions that could be intercepted by Spring Security
        Throwable cause = ex;
        while (cause != null) {
            if (isBusinessException(cause)) {
                ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    cause.getMessage()
                );
                
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
                response.getWriter().flush();
                return true; // Exception was handled
            }
            cause = cause.getCause();
        }
        
        // Exception was not handled
        return false;
    }
    
    private boolean isBusinessException(Throwable cause) {
        // Check for EntityNotFoundException (both packages)
        if (cause instanceof com.choosenfly.hotelbookingsystem.exceptions.EntityNotFoundException ||
            cause instanceof com.choosenfly.hotelbookingsystem.inventory.exceptions.EntityNotFoundException) {
            return true;
        }
        
        // Check for other common business exceptions that might be thrown in protected endpoints
        if (cause instanceof com.choosenfly.hotelbookingsystem.exceptions.DataNotFoundException ||
            cause instanceof com.choosenfly.hotelbookingsystem.exceptions.HotelNotFoundException ||
            cause instanceof com.choosenfly.hotelbookingsystem.exceptions.EntityCreationException) {
            return true;
        }
        
        // Add more custom exceptions as needed
        return false;
    }
}
