package com.project.movie.Services;


import com.project.movie.Entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();

    void saveUser(Users user);

}
