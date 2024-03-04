package com.eCommerce.ecom.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.ecom.dto.AuthenticationRequest;
import com.eCommerce.ecom.entity.User;
import com.eCommerce.ecom.repository.UserRepository;
import com.eCommerce.ecom.util.JwtUtil;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public static final String HEADER_STRING = "Authorization";
    public static final String TOCKEN_PREFIX = "Bearer ";

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws IOException, JSONException{

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Inccorect Password or UserName");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionalUser =  userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.genarateTocken(userDetails.getUsername());
        if (optionalUser.isPresent()) {
            response.getWriter().write(new JSONObject()
            .put("userId",optionalUser.get().getId())
            .put("role", optionalUser.get().getRole())
            .toString()
            );

            
            response.addHeader(HEADER_STRING,TOCKEN_PREFIX+jwt);
        }

    }

}
