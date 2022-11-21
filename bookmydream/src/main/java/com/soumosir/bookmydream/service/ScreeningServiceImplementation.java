package com.soumosir.bookmydream.service;


import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.repo.HallRepo;
import com.soumosir.bookmydream.repo.MovieRepo;
import com.soumosir.bookmydream.repo.ScreeningRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ScreeningServiceImplementation implements ScreeningService {
    private final ScreeningRepo screeningRepo;
    private final HallRepo hallRepo;
    private final MovieRepo movieRepo;

    @Override
    public Screening saveScreening(Screening screening) throws Exception {

        screening.validate();

        Hall hall = hallRepo.findById(screening.getHall().getId()).orElseThrow(()->{
            log.error("Hall not found with id {} ",screening.getHall().getId());
            throw new ResourceNotFoundException(String.format("Hall not found with id %d", screening.getHall().getId()));
        });

        Movie movie = movieRepo.findById(screening.getMovie().getId()).orElseThrow(()->{
            log.error("Movie not found with id {} ",screening.getMovie().getId());
            throw new ResourceNotFoundException(String.format("Movie not found with id %d", screening.getMovie().getId()));
        });

        screening.setHall(hall);
        screening.setMovie(movie);

        Long duration = screening.getMovie().getDuration();
        screening.setEndTime(
                Timestamp.valueOf((screening.getStartTime().toLocalDateTime().plus(duration, ChronoUnit.MINUTES)))
        );
        if(!screeningRepo.findByHallAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                screening.getHall(),screening.getEndTime(),screening.getStartTime()).isEmpty()){
            log.error("Hall {} has other shows running for the start time {}",screening.getHall().getName(),screening.getStartTime());
            throw new ValidationException("Hall  has other shows running for the start time");
        }
        log.info("Saving new screening with time {} to database ",screening.getStartTime());
        return screeningRepo.save(screening);
    }

    @Override
    public List<Screening> getScreeningsByHall(Hall hall) {
        log.info("get screening by hall {} from database",hall.getName());
        return screeningRepo.findByHall(hall);
    }

    @Override
    public List<Screening> getScreenings() {
        log.info("get screenings from database");
        return screeningRepo.findAll();
    }

    @Override
    public Screening updateScreening(Long id, Screening screening){

        screening.validate();

        Screening screening1 = screeningRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screening does not exist with id: " + id));


        Hall hall = hallRepo.findById(screening.getHall().getId()).orElseThrow(()->{
            log.error("Hall not found with id {} ",screening.getHall().getId());
            throw new ResourceNotFoundException(String.format("Hall not found with id %d", screening.getHall().getId()));
        });
        Movie movie = movieRepo.findById(screening.getMovie().getId()).orElseThrow(()->{
            log.error("Movie not found with id {} ",screening.getMovie().getId());
            throw new ResourceNotFoundException(String.format("Movie not found with id %d", screening.getMovie().getId()));
        });

        screening.setHall(hall);
        screening.setMovie(movie);

        Long duration = screening.getMovie().getDuration();
        screening.setEndTime(
                Timestamp.valueOf((screening.getStartTime().toLocalDateTime().plus(duration, ChronoUnit.MINUTES)))
        );
        if(!screeningRepo.findByHallAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                screening.getHall(),screening.getEndTime(),screening.getStartTime()).isEmpty()){

            List<Screening> screenings = screeningRepo.findByHallAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                    screening.getHall(),screening.getEndTime(),screening.getStartTime());
            if(!(screenings.size()==1 && screenings.get(0).getId()==screening1.getId())) {
                log.error("Hall {} has other shows running for the start time {}",screening.getHall().getName(),screening.getStartTime());
                throw new ValidationException("Hall has other shows running for the start time");

            }

        }


        log.info("Updating screening with id {} and time {} to database",id,screening.getStartTime());

        screening1.setHall(hall);
        screening1.setMovie(movie);
        screening1.setStartTime(screening.getStartTime());
        screening1.setEndTime(screening.getEndTime());

        return screening1;
    }

    @Override
    public void deleteScreening(Long id){
        Screening screening = screeningRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screening does not exist with id: " + id));

        log.info("Deleting screening with id {} from database",id);
        screeningRepo.deleteById(id);
    }



}
