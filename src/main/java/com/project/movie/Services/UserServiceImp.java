package com.project.movie.Services;

import com.project.movie.Entity.Role;
import com.project.movie.Entity.UserPrincipal;
import com.project.movie.Entity.Users;
import com.project.movie.Repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTService jwtService;

    public UserServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(@NotNull Users users){
        users.setPassword(new BCryptPasswordEncoder(12).encode(users.getPassword()));
        users.setRole(Role.valueOf(users.getRole().name()));
        userRepository.save(users);
    }

    public Optional<Users> getUserByUsername(String username){
        return userRepository.findUsersByUsername(username);
    }


    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),user.getPassword()
                        )
                );

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "Failed";
    }
}