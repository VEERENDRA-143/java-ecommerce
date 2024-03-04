package com.eCommerce.ecom.services.jwt;

import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eCommerce.ecom.entity.User;
import com.eCommerce.ecom.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> optionaluser = userRepository.findFirstByEmail(username);
        if (optionaluser.isEmpty()) throw new UsernameNotFoundException("User Not Found With Name "+username);
        return new org.springframework.security.core.userdetails.User(optionaluser.get().getEmail(), optionaluser.get().getPassword(), new ArrayList<>());
    }
    
}
