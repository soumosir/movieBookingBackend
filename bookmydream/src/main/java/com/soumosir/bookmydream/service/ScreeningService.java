package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Screening;

import java.util.List;

public interface ScreeningService {
    Screening saveScreening(Screening screening) throws Exception;
    List<Screening> getScreeningsByHall(Hall hall);
    List<Screening> getScreenings();
    Screening updateScreening(Long id, Screening screening);
    void deleteScreening(Long id);
}
