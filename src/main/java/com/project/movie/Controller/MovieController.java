package com.project.movie.Controller;

import com.project.movie.Entity.Movie;
import com.project.movie.Services.MovieService;
import com.project.movie.Services.MovieServiceImp;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieServiceImp movies;

    public MovieController(MovieServiceImp movies){
        this.movies=movies;
    }

    @GetMapping
    public List<Movie> getAllMovies(){
        return movies.getAllMovies();
    }

    @PostMapping
    public boolean addMovie(@org.jetbrains.annotations.NotNull @RequestBody Movie movie){
        Optional<Movie> m = movies.getMovieByTitle(movie.getTitle());
        if(m.isEmpty()){
            movies.addMovie(movie);
            return true;
        }
        return false;
    }

    @GetMapping(path="title/{title}")
    public Optional<Movie> getMovieByTitle(@PathVariable("title") String title){
        return movies.getMovieByTitle(title);
    }

    @DeleteMapping(path="{title}")
    public String deleteMovieByTitle(@PathVariable("title") String title){
        Optional<Movie> m = movies.getMovieByTitle(title);

        if(m.isPresent()){
            movies.removeMovie(m.get());
            return "Movie Deleted";
        }
        return "Movie does not exist";
    }

    @PutMapping(path = "{title}")
    public Optional<Movie> updateMovie(
            @PathVariable("title") String title,
            @RequestBody Movie movie
    ){
        return movies.updateMovie(title,movie);
    }
}
