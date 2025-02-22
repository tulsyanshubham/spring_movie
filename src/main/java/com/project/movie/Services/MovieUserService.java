package com.project.movie.Services;

import com.project.movie.Entity.MovieUser;

import java.util.List;
import java.util.Optional;

public interface MovieUserService {
    List<MovieUser> getAllUsers();

    void saveUser(MovieUser user);
}
