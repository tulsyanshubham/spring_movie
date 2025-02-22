package com.project.movie.Repository;

import com.project.movie.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {
    Optional<Movie> findMovieByTitle(String title);
}
