package com.eCommerce.ecom.filters;

import com.eCommerce.ecom.services.jwt.UserDetailsServiceImpl;
import com.eCommerce.ecom.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter {

   private  final UserDetailsServiceImpl userDetailsService;

   private final JwtUtil jwtUtil;

    protected  void  doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException{
       
        String authHeader = request.getHeader("Authorization");
        String tocken = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            tocken = authHeader.substring(7);
            username = jwtUtil.extractUsername(tocken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(tocken, userDetails)) {
                UsernamePasswordAuthenticationToken authTocken = new UsernamePasswordAuthenticationToken(username, null);
                authTocken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authTocken);
            }
        }

        filterChain.doFilter(request, response);

    }

}
