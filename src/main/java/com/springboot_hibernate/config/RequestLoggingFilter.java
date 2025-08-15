package com.springboot_hibernate.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.EOFException;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String header = null;
        try {
            header = req.getMethod() + " " + req.getRequestURI();
        } catch (Exception e) {
            log.warn("Incomplete request from: {}", req.getRemoteAddr());
        }

        try {
            chain.doFilter(request, response);
        } catch (EOFException e) {
            log.debug("Client disconnected early: {} from {}", header, req.getRemoteAddr());
        }
    }
}