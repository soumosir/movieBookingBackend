package com.soumosir.bookmydream.repo;

import com.soumosir.bookmydream.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepo extends JpaRepository<Hall, Long> {
    Hall findByName(String name);
}
