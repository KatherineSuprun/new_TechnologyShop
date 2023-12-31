package com.example.mainapp.service;

import com.example.mainapp.dto.UserDetailsImpl;
import com.example.mainapp.entity.User;
import com.example.mainapp.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> retrievedUser = Optional.ofNullable(userRepo.findByEmail(username));
        if (retrievedUser.isPresent()) {
            return new UserDetailsImpl(retrievedUser.get());
       }
        throw new UsernameNotFoundException("User with name " + username + " was not found");
    }

}
