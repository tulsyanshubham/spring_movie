package com.project.movie.Services;

import com.project.movie.Entity.UserPrincipal;
import com.project.movie.Entity.Users;
import com.project.movie.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findUsersByUsername(username);

        if (user.isEmpty()) {
            System.out.printf("User not found");
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new UserPrincipal(user.get());
    }
}
