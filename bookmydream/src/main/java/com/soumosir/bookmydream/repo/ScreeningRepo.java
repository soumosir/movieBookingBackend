package com.soumosir.bookmydream.repo;

import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ScreeningRepo extends JpaRepository<Screening, Long> {
    List<Screening> findByHallAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Hall hall, Timestamp start,Timestamp end);
    List<Screening> findByHall(Hall hall);

}
