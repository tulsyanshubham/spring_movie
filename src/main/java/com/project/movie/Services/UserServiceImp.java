package com.project.movie.Services;

import com.project.movie.Entity.UserPrincipal;
import com.project.movie.Entity.Users;
import com.project.movie.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(Users users){
        userRepository.save(users);
    }

    public Optional<Users> getUserByUsername(String username){
        return userRepository.findUsersByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findUsersByUsername(username);
        System.out.println(userRepository.findAll());
        if(!user.isPresent()){
            System.out.println("user no found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user.get());
    }
}