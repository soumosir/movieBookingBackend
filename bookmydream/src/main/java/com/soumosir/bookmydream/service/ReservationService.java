package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.model.Reservation;
import com.soumosir.bookmydream.model.Screening;

import java.util.List;

public interface ReservationService {
    Reservation saveReservation(Reservation reservation) throws Exception;
    List<Reservation> getReservations();
    List<Reservation> getReservationsByUsername(String username);
    Reservation updateReservation(Long id, Reservation reservation) throws Exception;
    void deleteReservation(Long id,String username);
}
