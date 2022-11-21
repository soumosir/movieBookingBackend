package com.soumosir.bookmydream.repo;

import com.soumosir.bookmydream.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    Movie findByTitle(String name);
}

