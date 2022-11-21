package com.soumosir.bookmydream.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.repo.HallRepo;
import com.soumosir.bookmydream.repo.MovieRepo;
import com.soumosir.bookmydream.repo.ScreeningRepo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScreeningServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class ScreeningServiceImplementationTest {
    @MockBean
    private HallRepo hallRepo;

    @MockBean
    private MovieRepo movieRepo;

    @MockBean
    private ScreeningRepo screeningRepo;

    @Autowired
    private ScreeningServiceImplementation screeningServiceImplementation;

    /**
     * Method under test: {@link ScreeningServiceImplementation#getScreeningsByHall(Hall)}
     */
    @Test
    void testGetScreeningsByHall() {
        ArrayList<Screening> screeningList = new ArrayList<>();
        when(this.screeningRepo.findByHall((Hall) any())).thenReturn(screeningList);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        List<Screening> actualScreeningsByHall = this.screeningServiceImplementation.getScreeningsByHall(hall);
        assertSame(screeningList, actualScreeningsByHall);
        assertTrue(actualScreeningsByHall.isEmpty());
        verify(this.screeningRepo).findByHall((Hall) any());
    }

    /**
     * Method under test: {@link ScreeningServiceImplementation#getScreeningsByHall(Hall)}
     */
    @Test
    void testGetScreeningsByHall2() {
        when(this.screeningRepo.findByHall((Hall) any())).thenThrow(new ValidationException("An error occurred"));

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        assertThrows(ValidationException.class, () -> this.screeningServiceImplementation.getScreeningsByHall(hall));
        verify(this.screeningRepo).findByHall((Hall) any());
    }

    /**
     * Method under test: {@link ScreeningServiceImplementation#getScreenings()}
     */
    @Test
    void testGetScreenings() {
        ArrayList<Screening> screeningList = new ArrayList<>();
        when(this.screeningRepo.findAll()).thenReturn(screeningList);
        List<Screening> actualScreenings = this.screeningServiceImplementation.getScreenings();
        assertSame(screeningList, actualScreenings);
        assertTrue(actualScreenings.isEmpty());
        verify(this.screeningRepo).findAll();
    }

    /**
     * Method under test: {@link ScreeningServiceImplementation#getScreenings()}
     */
    @Test
    void testGetScreenings2() {
        when(this.screeningRepo.findAll()).thenThrow(new ValidationException("An error occurred"));
        assertThrows(ValidationException.class, () -> this.screeningServiceImplementation.getScreenings());
        verify(this.screeningRepo).findAll();
    }

    /**
     * Method under test: {@link ScreeningServiceImplementation#deleteScreening(Long)}
     */
    @Test
    void testDeleteScreening() {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);

        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");

        Screening screening = new Screening();
        screening.setEndTime(mock(Timestamp.class));
        screening.setHall(hall);
        screening.setId(123L);
        screening.setMovie(movie);
        screening.setStartTime(mock(Timestamp.class));
        Optional<Screening> ofResult = Optional.of(screening);
        doNothing().when(this.screeningRepo).deleteById((Long) any());
        when(this.screeningRepo.findById((Long) any())).thenReturn(ofResult);
        this.screeningServiceImplementation.deleteScreening(123L);
        verify(this.screeningRepo).findById((Long) any());
        verify(this.screeningRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ScreeningServiceImplementation#deleteScreening(Long)}
     */
    @Test
    void testDeleteScreening2() {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);

        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");

        Screening screening = new Screening();
        screening.setEndTime(mock(Timestamp.class));
        screening.setHall(hall);
        screening.setId(123L);
        screening.setMovie(movie);
        screening.setStartTime(mock(Timestamp.class));
        Optional<Screening> ofResult = Optional.of(screening);
        doThrow(new ValidationException("An error occurred")).when(this.screeningRepo).deleteById((Long) any());
        when(this.screeningRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ValidationException.class, () -> this.screeningServiceImplementation.deleteScreening(123L));
        verify(this.screeningRepo).findById((Long) any());
        verify(this.screeningRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ScreeningServiceImplementation#deleteScreening(Long)}
     */
    @Test
    void testDeleteScreening3() {
        doNothing().when(this.screeningRepo).deleteById((Long) any());
        when(this.screeningRepo.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> this.screeningServiceImplementation.deleteScreening(123L));
        verify(this.screeningRepo).findById((Long) any());
    }
}

