package com.project.movie.Services;

import com.project.movie.Entity.Movie;
import com.project.movie.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImp implements MovieService{

    private final MovieRepository allmovies;

    public MovieServiceImp(MovieRepository allmovies){
        this.allmovies=allmovies;
    }

    @Override
    public void addMovie(Movie movie) {
        allmovies.save(movie);
    }

    @Override
    public boolean removeMovie(Movie movie) {
        allmovies.delete(movie);
        return true;
    }

    @Override
    public List<Movie> getAllMovies() {
        return allmovies.findAll();
    }

    public Optional<Movie> getMovieByTitle(String title){
        return allmovies.findById(title);
    }

    @Override
    public Optional<Movie> updateMovie(String title, Movie movie) {
        Optional<Movie> m = this.getMovieByTitle(title);
        if(m.isEmpty()){
            return Optional.empty();
        }
        if(movie.getGenre() != null && !movie.getGenre().isEmpty() )
            m.get().setGenre(movie.getGenre());
        if(movie.getDirector() != null)
            m.get().setDirector(movie.getDirector());
        if(movie.getRating() != null)
            m.get().setRating(movie.getRating());
        if(movie.getReleaseDate() != null)
            m.get().setReleaseDate(movie.getReleaseDate());
        allmovies.save(m.get());
        return m;
    }
}
