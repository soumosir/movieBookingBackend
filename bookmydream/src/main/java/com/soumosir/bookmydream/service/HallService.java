package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.model.Hall;

import java.util.List;

public interface HallService {
    Hall saveHall(Hall Hall);
    Hall getHall(String name);
    Hall updateHall(Long id, Hall hall);
    void deleteHall(Long id);
    List<Hall> getHalls();
}
