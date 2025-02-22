package com.project.movie.Services;

import com.project.movie.Entity.MovieUser;
import com.project.movie.Repository.MovieUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MovieUserRepository userRepository;
    public CustomUserDetailsService(MovieUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MovieUser> user = userRepository.findMovieUserByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        MovieUser movieUser = user.get();
        return User.builder()
                .username(movieUser.getUsername())
                .password(movieUser.getPassword())
                .roles(movieUser.getRole())
                .build();
    }
}