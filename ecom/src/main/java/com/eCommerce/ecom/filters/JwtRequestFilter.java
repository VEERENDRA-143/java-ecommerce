package com.eCommerce.ecom.filters;

import com.eCommerce.ecom.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter {

   private  final UserDetailsServiceImpl userDetailsService;
   private final JwtUtil jwtUtil;
    protected  void  doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException{
        String authHeader = request.getHeader("Authorization");
        String tocken = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            tocken = authHeader.substring(7);
            username = jwtUtil.extractUsername(tocken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

        }
    }

}
