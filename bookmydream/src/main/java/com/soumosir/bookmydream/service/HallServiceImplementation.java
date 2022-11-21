package com.soumosir.bookmydream.service;

import com.soumosir.bookmydream.dbkeys.SeatKey;
import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Seat;
import com.soumosir.bookmydream.repo.HallRepo;
import com.soumosir.bookmydream.repo.SeatRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class HallServiceImplementation implements HallService {

    private final HallRepo hallRepo;
    private final SeatRepo seatRepo;


    private List<Seat> addSeatsToHall(Hall hall) {

        List<Seat> seats= new ArrayList<>();
        for(Long row = 1L;row<=hall.getTotalRows();row++){
            for(Long column=1L;column<=hall.getTotalColumns();column++){


                Seat seat = seatRepo.findById(new SeatKey(row,column)).orElse(null);
                if(seat==null){
                    seat = seatRepo.save(new Seat(new SeatKey(row,column)));
                }
                seats.add(seat);
            }
        }
        return seats;
    }

    @Override
    public Hall saveHall(Hall hall) {
        hall.validate();
        List<Seat> seats = addSeatsToHall(hall);
        hall.setSeats(seats);
        log.info("Saving hall to database - "+hall.getName());
        return hallRepo.save(hall);
    }

    @Override
    public Hall getHall(String name) {
        log.info("get  hall from database");
        return hallRepo.findByName(name);
    }

    @Override
    public List<Hall> getHalls() {
        return hallRepo.findAll();
    }

    @Override
    public Hall updateHall(Long id, Hall hall) {
        Hall hallDb = hallRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall does not exist with id: " + id));
        hall.validate();
        hallDb.setName(hall.getName());
        hallDb.setTotalColumns(hall.getTotalColumns());
        hallDb.setTotalRows(hall.getTotalRows());
        List<Seat> seats = addSeatsToHall(hallDb);
        hallDb.setSeats(seats);
        log.info("Updating hall with id {} and name {} to database",hallDb.getId(),hallDb.getName());
        return hallDb;
    }

    @Override
    public void deleteHall(Long id) {
        Hall hallDb = hallRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall does not exist with id: " + id));

        log.info("Deleting hall with id {} and name {} to database" , hallDb.getId(),hallDb.getName());
        hallRepo.deleteById(id);
    }

}
