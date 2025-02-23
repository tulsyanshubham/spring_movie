package com.project.movie.Controller;

import com.project.movie.Entity.Users;
import com.project.movie.Services.UserServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImp users;

    public UserController(UserServiceImp users){
        this.users=users;
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return users.getAllUsers();
    }

    @PostMapping
    public boolean saveUser(@RequestBody Users users){
        Optional<Users> u = this.users.getUserByUsername(users.getUsername());
        if(u.isPresent()) return false;
        this.users.saveUser(users);
        return true;
    }

    @GetMapping("/{username}")
    public Optional<Users> getUserByUsername(@PathVariable("username") String username) {
        return users.getUserByUsername(username);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println(user);
        return users.verify(user);
    }
}
