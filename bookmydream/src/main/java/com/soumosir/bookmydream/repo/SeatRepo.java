package com.soumosir.bookmydream.repo;

import com.soumosir.bookmydream.dbkeys.SeatKey;
import com.soumosir.bookmydream.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat, SeatKey> {
}