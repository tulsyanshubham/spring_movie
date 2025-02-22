package com.project.movie.Controller;

import com.project.movie.Entity.MovieUser;
import com.project.movie.Services.MovieUserServiceImp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class MovieUserController {

    private final MovieUserServiceImp users;

    public MovieUserController(MovieUserServiceImp users){
        this.users=users;
    }

    @GetMapping
    public List<MovieUser> getAllUsers(){
        return users.getAllUsers();
    }

    @PostMapping
    public boolean saveUser(@RequestBody MovieUser movieUser){
        Optional<MovieUser> u = users.getUserByUsername(movieUser.getUsername());
        if(u.isPresent()) return false;
        movieUser.setPassword(new BCryptPasswordEncoder().encode(movieUser.getPassword()));
        users.saveUser(movieUser);
        return true;
    }

    @GetMapping("{username}")
    public Optional<MovieUser> getUserByUsername(@RequestParam("username") String username){
        return users.getUserByUsername(username);
    }
}
