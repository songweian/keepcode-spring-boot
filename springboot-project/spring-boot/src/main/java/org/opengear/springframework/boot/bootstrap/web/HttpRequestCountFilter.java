package org.opengear.springframework.boot.bootstrap.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class HttpRequestCountFilter extends OncePerRequestFilter {

    private final AtomicLong requestCount = new AtomicLong(0);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            requestCount.incrementAndGet();
            filterChain.doFilter(request, response);
            requestCount.decrementAndGet();
        } finally {
            // Do something
        }
    }
}
