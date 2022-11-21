package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.repo.MovieRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class MovieServiceImplementation implements MovieService {


    private final MovieRepo movieRepo;


    @Override
    public Movie saveMovie(Movie movie) {

        movie.validate();
        log.info("Saving movie {} to database",movie.getTitle());

        return movieRepo.save(movie);
    }

    @Override
    public Movie updateMovie(Long id,Movie movie) {

        movie.validate();
        Movie movieDb = movieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie does not exist with id: " + id));

        movieDb.setTitle(movie.getTitle());
        movieDb.setDirector(movie.getDirector());
        movieDb.setCast(movie.getCast());
        movieDb.setDescription(movie.getDescription());
        log.info("Updating movie with id {} and name {} to database",movieDb.getId(),movieDb.getTitle());
        return movieDb;
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movieDb = movieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie does not exist with id: " + id));
        log.info("Deleting movie with id {} and name {} from database ",movieDb.getId(),movieDb.getTitle());
        movieRepo.deleteById(id);
    }

    @Override
    public Movie getMovie(String name) {
        log.info("get  movie {} from database",name);
        return movieRepo.findByTitle(name);
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }


}
