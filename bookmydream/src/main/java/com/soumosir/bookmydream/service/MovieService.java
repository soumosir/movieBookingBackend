package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.model.Movie;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);

    Movie updateMovie(Long id,Movie movie);
    void deleteMovie(Long id);
    Movie getMovie(String name);
    List<Movie> getMovies();
}
