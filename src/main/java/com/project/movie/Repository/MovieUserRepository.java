package com.project.movie.Repository;

import com.project.movie.Entity.MovieUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieUserRepository extends JpaRepository<MovieUser,String> {
    Optional<MovieUser> findMovieUserByUsername(String username);
}
