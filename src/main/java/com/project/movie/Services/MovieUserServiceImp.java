package com.project.movie.Services;

import com.project.movie.Entity.MovieUser;
import com.project.movie.Repository.MovieUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieUserServiceImp implements MovieUserService {
    private final MovieUserRepository userRepository;

    public MovieUserServiceImp(MovieUserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public List<MovieUser> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public void saveUser(MovieUser user){
        userRepository.save(user);
    }

    public Optional<MovieUser> getUserByUsername(String username){
        return userRepository.findMovieUserByUsername(username);
    }
}