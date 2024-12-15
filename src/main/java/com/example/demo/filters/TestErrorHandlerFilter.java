package com.example.demo.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TestErrorHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("request.getAttribute(\"jwtException\")"+request.getAttribute("jwtException"));
        System.out.println("request.getAttribute(\"jwtException\")"+request.getAttribute("Exception"));
        System.out.println(request.getAttribute("JwtException"));
        filterChain.doFilter(request,response);
    }
}