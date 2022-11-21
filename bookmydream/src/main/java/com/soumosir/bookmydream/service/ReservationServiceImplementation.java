package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.dbkeys.SeatKey;
import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Reservation;
import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.model.Seat;
import com.soumosir.bookmydream.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ReservationServiceImplementation implements ReservationService {


    private final ReservationRepo reservationRepo;
    private final HallRepo hallRepo;
    private final AppUserRepo appUserRepo;

    private final ScreeningRepo screeningRepo;
    private final SeatRepo seatRepo;


    @Override
    public Reservation saveReservation(Reservation reservation) throws Exception {

        Screening screening = screeningRepo.findById(reservation.getScreening().getId()).orElseThrow(()->
            new ResourceNotFoundException("Screening not found ")
        );

        reservation.setScreening(screening);

        Seat seat = seatRepo.findById(new SeatKey(reservation.getRowId(),reservation.getColumnId())).orElseThrow(() ->
                new ResourceNotFoundException("Seat not found "));

        if(!hallRepo.findByName(reservation.getScreening().getHall().getName()).getSeats().contains(seat)){
            log.error("Seat {} , {} is not associated with the hall {}",reservation.getRowId(),reservation.getColumnId(),reservation.getScreening().getHall().getName());
          throw new Exception("Seat is not associated with the hall");
        }
        List<Reservation> reservations = reservationRepo.findByScreeningAndRowIdAndColumnId(reservation.getScreening(),reservation.getRowId(),reservation.getColumnId());
        if(!reservations.isEmpty()){
            log.error("Seat {} , {} is booked for hall {}",reservation.getRowId(),reservation.getColumnId(),reservation.getScreening().getHall().getName());
            throw new Exception("Seat is already booked");
        }

        reservation.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        log.info("Updating reservation  for user {} to database",reservation.getAppUser().getName());

        return reservationRepo.save(reservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public List<Reservation> getReservationsByUsername(String username) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with id: " + username));
        return reservationRepo.findByAppUser(appUser);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) throws Exception {
        Reservation reservation1 = reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation does not exist with id: " + id));
        if(reservation1.getAppUser()!=null && !reservation1.getAppUser().getUsername().equals(reservation.getAppUser().getUsername())){
            log.error("Not Allowed : Cannot update other's reservation username {} ",reservation.getAppUser().getUsername());
            throw HttpClientErrorException.Unauthorized.create(
                    "Not Allowed : Cannot update other's reservation",HttpStatus.FORBIDDEN,null,null,null,null);
        }

        if(reservation.getScreening()!=null  && reservation.getScreening().getId()!=reservation1.getScreening().getId()){
            throw new Exception("you cannot change screening hall and movie");
        }


        Seat seat = seatRepo.findById(new SeatKey(reservation.getRowId(),reservation.getColumnId())).orElseThrow(() ->
                new ResourceNotFoundException("Seat not found "));
        if(!hallRepo.findByName(reservation1.getScreening().getHall().getName()).getSeats().contains(seat)){
            log.error("Seat {} , {} is not associated with the hall {}",reservation.getRowId(),reservation.getColumnId(),reservation1.getScreening().getHall().getName());
            throw new Exception("Seat is not associated with the hall");
        }
        List<Reservation> reservations = reservationRepo.findByScreeningAndRowIdAndColumnId(reservation1.getScreening(),reservation.getRowId(),reservation.getColumnId());
        if(!reservations.isEmpty()){
            log.error("Seat {} , {} is booked for hall {}",reservation.getRowId(),reservation.getColumnId(),reservation1.getScreening().getHall().getName());
            throw new Exception("Seat is already booked");
        }


        log.info("Updating reservation with id {} for user {} to database",id,reservation.getAppUser().getName());

        reservation1.setColumnId(reservation.getColumnId());
        reservation1.setRowId(reservation.getRowId());


        return reservation1;
    }

    @Override
    public void deleteReservation(Long id,String username){
        Reservation reservation1 = reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation does not exist with id: " + id));
        if(!reservation1.getAppUser().getUsername().equals(username)){
            log.error("Not Allowed : Cannot update other's reservation username {} ",username);
            throw HttpClientErrorException.Unauthorized.create(
                    "Not Allowed : Cannot delete other's reservation", HttpStatus.FORBIDDEN, null, null, null,null);
        }
        log.info("Deleting reservation with id {} from database",id);
        reservationRepo.deleteById(id);
    }

}

