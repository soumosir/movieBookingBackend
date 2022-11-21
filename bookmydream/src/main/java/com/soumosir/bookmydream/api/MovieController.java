package com.soumosir.bookmydream.api;

import com.soumosir.bookmydream.model.Movie;

import com.soumosir.bookmydream.service.MovieService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;


import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getMovies(){
        return ResponseEntity.ok().body(movieService.getMovies());
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie").toUriString());
        return ResponseEntity.created(uri).body(movieService.saveMovie(movie));
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id,@RequestBody Movie movie){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie/"+id).toUriString());
        return ResponseEntity.created(uri).body(movieService.updateMovie(id,movie));
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable long id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/movie").toUriString());
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}

