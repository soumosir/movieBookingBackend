package com.soumosir.bookmydream.repo;

import com.soumosir.bookmydream.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByScreeningAndRowIdAndColumnId(Screening screening, Long rowId, Long ColumnId);
    List<Reservation> findByAppUser(AppUser appUser);
}
