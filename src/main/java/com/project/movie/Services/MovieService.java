package com.project.movie.Services;

import com.project.movie.Entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    void addMovie (Movie movie);
    boolean removeMovie(Movie movie);
    List<Movie> getAllMovies();
    Optional<Movie> updateMovie(String title, Movie movie);
}
