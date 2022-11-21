package com.soumosir.bookmydream.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.soumosir.bookmydream.dbkeys.SeatKey;
import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.Reservation;
import com.soumosir.bookmydream.model.Role;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.model.Seat;
import com.soumosir.bookmydream.repo.AppUserRepo;
import com.soumosir.bookmydream.repo.HallRepo;
import com.soumosir.bookmydream.repo.ReservationRepo;
import com.soumosir.bookmydream.repo.ScreeningRepo;
import com.soumosir.bookmydream.repo.SeatRepo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReservationServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class ReservationServiceImplementationTest {
    @MockBean
    private AppUserRepo appUserRepo;

    @MockBean
    private HallRepo hallRepo;

    @MockBean
    private ReservationRepo reservationRepo;

    @Autowired
    private ReservationServiceImplementation reservationServiceImplementation;

    @MockBean
    private ScreeningRepo screeningRepo;

    @MockBean
    private SeatRepo seatRepo;

    /**
     * Method under test: {@link ReservationServiceImplementation#saveReservation(Reservation)}
     */
    @Test
    void testSaveReservation() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

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
        Optional<Screening> ofResult1 = Optional.of(screening);
        when(this.screeningRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall1);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall2);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening1);
        reservation.setTimestamp(mock(Timestamp.class));
        assertThrows(Exception.class, () -> this.reservationServiceImplementation.saveReservation(reservation));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.screeningRepo).findById((Long) any());
        verify(this.hallRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#saveReservation(Reservation)}
     */
    @Test
    void testSaveReservation2() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

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
        Optional<Screening> ofResult1 = Optional.of(screening);
        when(this.screeningRepo.findById((Long) any())).thenReturn(ofResult1);
        when(this.hallRepo.findByName((String) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening1);
        reservation.setTimestamp(mock(Timestamp.class));
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.saveReservation(reservation));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.screeningRepo).findById((Long) any());
        verify(this.hallRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#saveReservation(Reservation)}
     */
    @Test
    void testSaveReservation3() throws Exception {
        when(this.seatRepo.findById((com.soumosir.bookmydream.dbkeys.SeatKey) any())).thenReturn(Optional.empty());

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
        when(this.screeningRepo.findById((Long) any())).thenReturn(ofResult);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall1);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall2);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening1);
        reservation.setTimestamp(mock(Timestamp.class));
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.saveReservation(reservation));
        verify(this.seatRepo).findById((com.soumosir.bookmydream.dbkeys.SeatKey) any());
        verify(this.screeningRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#saveReservation(Reservation)}
     */
    @Test
    void testSaveReservation4() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);
        when(this.screeningRepo.findById((Long) any())).thenReturn(Optional.empty());

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

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        Screening screening = mock(Screening.class);
        when(screening.getHall()).thenReturn(hall1);
        doNothing().when(screening).setEndTime((Timestamp) any());
        doNothing().when(screening).setHall((Hall) any());
        doNothing().when(screening).setId((Long) any());
        doNothing().when(screening).setMovie((Movie) any());
        doNothing().when(screening).setStartTime((Timestamp) any());
        screening.setEndTime(mock(Timestamp.class));
        screening.setHall(hall);
        screening.setId(123L);
        screening.setMovie(movie);
        screening.setStartTime(mock(Timestamp.class));

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall2);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

        Hall hall3 = new Hall();
        hall3.setId(123L);
        hall3.setName("Name");
        hall3.setSeats(new ArrayList<>());
        hall3.setTotalColumns(1L);
        hall3.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall3);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening1);
        reservation.setTimestamp(mock(Timestamp.class));
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.saveReservation(reservation));
        verify(this.screeningRepo).findById((Long) any());
        verify(screening).setEndTime((Timestamp) any());
        verify(screening).setHall((Hall) any());
        verify(screening).setId((Long) any());
        verify(screening).setMovie((Movie) any());
        verify(screening).setStartTime((Timestamp) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#saveReservation(Reservation)}
     */
    @Test
    void testSaveReservation5() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

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

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        Screening screening = mock(Screening.class);
        when(screening.getHall()).thenReturn(hall1);
        doNothing().when(screening).setEndTime((Timestamp) any());
        doNothing().when(screening).setHall((Hall) any());
        doNothing().when(screening).setId((Long) any());
        doNothing().when(screening).setMovie((Movie) any());
        doNothing().when(screening).setStartTime((Timestamp) any());
        screening.setEndTime(mock(Timestamp.class));
        screening.setHall(hall);
        screening.setId(123L);
        screening.setMovie(movie);
        screening.setStartTime(mock(Timestamp.class));
        Optional<Screening> ofResult1 = Optional.of(screening);
        when(this.screeningRepo.findById((Long) any())).thenReturn(ofResult1);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall2);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening1);
        reservation.setTimestamp(mock(Timestamp.class));
        when(this.reservationRepo.save((Reservation) any())).thenReturn(reservation);
        when(this.reservationRepo.findByScreeningAndRowIdAndColumnId((Screening) any(), (Long) any(), (Long) any()))
                .thenReturn(new ArrayList<>());

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);

        ArrayList<Seat> seatList = new ArrayList<>();
        seatList.add(seat1);

        Hall hall3 = new Hall();
        hall3.setId(123L);
        hall3.setName("Name");
        hall3.setSeats(seatList);
        hall3.setTotalColumns(1L);
        hall3.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall3);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall4 = new Hall();
        hall4.setId(123L);
        hall4.setName("Name");
        hall4.setSeats(new ArrayList<>());
        hall4.setTotalColumns(1L);
        hall4.setTotalRows(1L);

        Movie movie2 = new Movie();
        movie2.setCast("Cast");
        movie2.setDescription("The characteristics of someone or something");
        movie2.setDirector("Director");
        movie2.setDuration(1L);
        movie2.setId(123L);
        movie2.setTitle("Dr");

        Screening screening2 = new Screening();
        screening2.setEndTime(mock(Timestamp.class));
        screening2.setHall(hall4);
        screening2.setId(123L);
        screening2.setMovie(movie2);
        screening2.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser1);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening2);
        reservation1.setTimestamp(mock(Timestamp.class));
        assertSame(reservation, this.reservationServiceImplementation.saveReservation(reservation1));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.screeningRepo).findById((Long) any());
        verify(screening).getHall();
        verify(screening).setEndTime((Timestamp) any());
        verify(screening).setHall((Hall) any());
        verify(screening).setId((Long) any());
        verify(screening).setMovie((Movie) any());
        verify(screening).setStartTime((Timestamp) any());
        verify(this.reservationRepo).save((Reservation) any());
        verify(this.reservationRepo).findByScreeningAndRowIdAndColumnId((Screening) any(), (Long) any(), (Long) any());
        verify(this.hallRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#getReservations()}
     */
    @Test
    void testGetReservations() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        when(this.reservationRepo.findAll()).thenReturn(reservationList);
        List<Reservation> actualReservations = this.reservationServiceImplementation.getReservations();
        assertSame(reservationList, actualReservations);
        assertTrue(actualReservations.isEmpty());
        verify(this.reservationRepo).findAll();
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#getReservations()}
     */
    @Test
    void testGetReservations2() {
        when(this.reservationRepo.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.reservationServiceImplementation.getReservations());
        verify(this.reservationRepo).findAll();
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#getReservationsByUsername(String)}
     */
    @Test
    void testGetReservationsByUsername() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        when(this.reservationRepo.findByAppUser((AppUser) any())).thenReturn(reservationList);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        List<Reservation> actualReservationsByUsername = this.reservationServiceImplementation
                .getReservationsByUsername("janedoe");
        assertSame(reservationList, actualReservationsByUsername);
        assertTrue(actualReservationsByUsername.isEmpty());
        verify(this.reservationRepo).findByAppUser((AppUser) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#getReservationsByUsername(String)}
     */
    @Test
    void testGetReservationsByUsername2() {
        when(this.reservationRepo.findByAppUser((AppUser) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.getReservationsByUsername("janedoe"));
        verify(this.reservationRepo).findByAppUser((AppUser) any());
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#getReservationsByUsername(String)}
     */
    @Test
    void testGetReservationsByUsername3() {
        when(this.reservationRepo.findByAppUser((AppUser) any())).thenReturn(new ArrayList<>());
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.getReservationsByUsername("janedoe"));
        verify(this.appUserRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#updateReservation(Long, Reservation)}
     */
    @Test
    void testUpdateReservation() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult1 = Optional.of(reservation);
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall1);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall2);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser1);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening1);
        reservation1.setTimestamp(mock(Timestamp.class));
        assertThrows(Exception.class, () -> this.reservationServiceImplementation.updateReservation(123L, reservation1));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.reservationRepo).findById((Long) any());
        verify(this.hallRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#updateReservation(Long, Reservation)}
     */
    @Test
    void testUpdateReservation2() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult1 = Optional.of(reservation);
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult1);
        when(this.hallRepo.findByName((String) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser1);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening1);
        reservation1.setTimestamp(mock(Timestamp.class));
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.updateReservation(123L, reservation1));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.reservationRepo).findById((Long) any());
        verify(this.hallRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#updateReservation(Long, Reservation)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateReservation3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.soumosir.bookmydream.service.ReservationServiceImplementation.updateReservation(ReservationServiceImplementation.java:90)
        //   In order to prevent updateReservation(Long, Reservation)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateReservation(Long, Reservation).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.seatRepo.findById((com.soumosir.bookmydream.dbkeys.SeatKey) any())).thenReturn(null);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult = Optional.of(reservation);
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall1);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall2);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser1);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening1);
        reservation1.setTimestamp(mock(Timestamp.class));
        this.reservationServiceImplementation.updateReservation(123L, reservation1);
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#updateReservation(Long, Reservation)}
     */
    @Test
    void testUpdateReservation4() throws Exception {
        when(this.seatRepo.findById((com.soumosir.bookmydream.dbkeys.SeatKey) any())).thenReturn(Optional.empty());

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult = Optional.of(reservation);
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall1);

        AppUser appUser1 = new AppUser();
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall2);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser1);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening1);
        reservation1.setTimestamp(mock(Timestamp.class));
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.updateReservation(123L, reservation1));
        verify(this.seatRepo).findById((com.soumosir.bookmydream.dbkeys.SeatKey) any());
        verify(this.reservationRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#updateReservation(Long, Reservation)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateReservation5() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.client.HttpClientErrorException$Forbidden: Not Allowed : Cannot update other's reservation
        //       at org.springframework.web.client.HttpClientErrorException.create(HttpClientErrorException.java:109)
        //       at com.soumosir.bookmydream.service.ReservationServiceImplementation.updateReservation(ReservationServiceImplementation.java:81)
        //   In order to prevent updateReservation(Long, Reservation)
        //   from throwing HttpClientErrorException$Forbidden, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateReservation(Long, Reservation).
        //   See https://diff.blue/R013 to resolve this issue.

        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getUsername()).thenReturn("foo");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Screening screening1 = new Screening();
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));
        Reservation reservation = mock(Reservation.class);
        when(reservation.getAppUser()).thenReturn(appUser1);
        when(reservation.getScreening()).thenReturn(screening1);
        doNothing().when(reservation).setAppUser((AppUser) any());
        doNothing().when(reservation).setColumnId((Long) any());
        doNothing().when(reservation).setId((Long) any());
        doNothing().when(reservation).setRowId((Long) any());
        doNothing().when(reservation).setScreening((Screening) any());
        doNothing().when(reservation).setTimestamp((Timestamp) any());
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult1 = Optional.of(reservation);
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall2);

        AppUser appUser2 = new AppUser();
        appUser2.setEmail("jane.doe@example.org");
        appUser2.setId(123L);
        appUser2.setName("Name");
        appUser2.setPassword("iloveyou");
        appUser2.setRoles(new ArrayList<>());
        appUser2.setUsername("janedoe");

        Hall hall3 = new Hall();
        hall3.setId(123L);
        hall3.setName("Name");
        hall3.setSeats(new ArrayList<>());
        hall3.setTotalColumns(1L);
        hall3.setTotalRows(1L);

        Movie movie2 = new Movie();
        movie2.setCast("Cast");
        movie2.setDescription("The characteristics of someone or something");
        movie2.setDirector("Director");
        movie2.setDuration(1L);
        movie2.setId(123L);
        movie2.setTitle("Dr");

        Screening screening2 = new Screening();
        screening2.setEndTime(mock(Timestamp.class));
        screening2.setHall(hall3);
        screening2.setId(123L);
        screening2.setMovie(movie2);
        screening2.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser2);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening2);
        reservation1.setTimestamp(mock(Timestamp.class));
        this.reservationServiceImplementation.updateReservation(123L, reservation1);
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#updateReservation(Long, Reservation)}
     */
    @Test
    void testUpdateReservation6() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        Screening screening1 = mock(Screening.class);
        when(screening1.getHall()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(screening1.getId()).thenReturn(123L);
        doNothing().when(screening1).setEndTime((Timestamp) any());
        doNothing().when(screening1).setHall((Hall) any());
        doNothing().when(screening1).setId((Long) any());
        doNothing().when(screening1).setMovie((Movie) any());
        doNothing().when(screening1).setStartTime((Timestamp) any());
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));
        Reservation reservation = mock(Reservation.class);
        when(reservation.getAppUser()).thenReturn(appUser1);
        when(reservation.getScreening()).thenReturn(screening1);
        doNothing().when(reservation).setAppUser((AppUser) any());
        doNothing().when(reservation).setColumnId((Long) any());
        doNothing().when(reservation).setId((Long) any());
        doNothing().when(reservation).setRowId((Long) any());
        doNothing().when(reservation).setScreening((Screening) any());
        doNothing().when(reservation).setTimestamp((Timestamp) any());
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult1 = Optional.of(reservation);
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall2);

        AppUser appUser2 = new AppUser();
        appUser2.setEmail("jane.doe@example.org");
        appUser2.setId(123L);
        appUser2.setName("Name");
        appUser2.setPassword("iloveyou");
        appUser2.setRoles(new ArrayList<>());
        appUser2.setUsername("janedoe");

        Hall hall3 = new Hall();
        hall3.setId(123L);
        hall3.setName("Name");
        hall3.setSeats(new ArrayList<>());
        hall3.setTotalColumns(1L);
        hall3.setTotalRows(1L);

        Movie movie2 = new Movie();
        movie2.setCast("Cast");
        movie2.setDescription("The characteristics of someone or something");
        movie2.setDirector("Director");
        movie2.setDuration(1L);
        movie2.setId(123L);
        movie2.setTitle("Dr");

        Screening screening2 = new Screening();
        screening2.setEndTime(mock(Timestamp.class));
        screening2.setHall(hall3);
        screening2.setId(123L);
        screening2.setMovie(movie2);
        screening2.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser2);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening2);
        reservation1.setTimestamp(mock(Timestamp.class));
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.updateReservation(123L, reservation1));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.reservationRepo).findById((Long) any());
        verify(reservation, atLeast(1)).getAppUser();
        verify(reservation, atLeast(1)).getScreening();
        verify(reservation).setAppUser((AppUser) any());
        verify(reservation).setColumnId((Long) any());
        verify(reservation).setId((Long) any());
        verify(reservation).setRowId((Long) any());
        verify(reservation).setScreening((Screening) any());
        verify(reservation).setTimestamp((Timestamp) any());
        verify(appUser1).getUsername();
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setName((String) any());
        verify(appUser1).setPassword((String) any());
        verify(appUser1).setRoles((java.util.Collection<Role>) any());
        verify(appUser1).setUsername((String) any());
        verify(screening1).getHall();
        verify(screening1).getId();
        verify(screening1).setEndTime((Timestamp) any());
        verify(screening1).setHall((Hall) any());
        verify(screening1).setId((Long) any());
        verify(screening1).setMovie((Movie) any());
        verify(screening1).setStartTime((Timestamp) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#updateReservation(Long, Reservation)}
     */
    @Test
    void testUpdateReservation7() throws Exception {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        Optional<Seat> ofResult = Optional.of(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");

        Hall hall2 = new Hall();
        hall2.setId(123L);
        hall2.setName("Name");
        hall2.setSeats(new ArrayList<>());
        hall2.setTotalColumns(1L);
        hall2.setTotalRows(1L);
        Screening screening1 = mock(Screening.class);
        when(screening1.getHall()).thenReturn(hall2);
        when(screening1.getId()).thenReturn(1L);
        doNothing().when(screening1).setEndTime((Timestamp) any());
        doNothing().when(screening1).setHall((Hall) any());
        doNothing().when(screening1).setId((Long) any());
        doNothing().when(screening1).setMovie((Movie) any());
        doNothing().when(screening1).setStartTime((Timestamp) any());
        screening1.setEndTime(mock(Timestamp.class));
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(mock(Timestamp.class));
        Reservation reservation = mock(Reservation.class);
        when(reservation.getAppUser()).thenReturn(appUser1);
        when(reservation.getScreening()).thenReturn(screening1);
        doNothing().when(reservation).setAppUser((AppUser) any());
        doNothing().when(reservation).setColumnId((Long) any());
        doNothing().when(reservation).setId((Long) any());
        doNothing().when(reservation).setRowId((Long) any());
        doNothing().when(reservation).setScreening((Screening) any());
        doNothing().when(reservation).setTimestamp((Timestamp) any());
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult1 = Optional.of(reservation);
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall3 = new Hall();
        hall3.setId(123L);
        hall3.setName("Name");
        hall3.setSeats(new ArrayList<>());
        hall3.setTotalColumns(1L);
        hall3.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall3);

        AppUser appUser2 = new AppUser();
        appUser2.setEmail("jane.doe@example.org");
        appUser2.setId(123L);
        appUser2.setName("Name");
        appUser2.setPassword("iloveyou");
        appUser2.setRoles(new ArrayList<>());
        appUser2.setUsername("janedoe");

        Hall hall4 = new Hall();
        hall4.setId(123L);
        hall4.setName("Name");
        hall4.setSeats(new ArrayList<>());
        hall4.setTotalColumns(1L);
        hall4.setTotalRows(1L);

        Movie movie2 = new Movie();
        movie2.setCast("Cast");
        movie2.setDescription("The characteristics of someone or something");
        movie2.setDirector("Director");
        movie2.setDuration(1L);
        movie2.setId(123L);
        movie2.setTitle("Dr");

        Screening screening2 = new Screening();
        screening2.setEndTime(mock(Timestamp.class));
        screening2.setHall(hall4);
        screening2.setId(123L);
        screening2.setMovie(movie2);
        screening2.setStartTime(mock(Timestamp.class));

        Reservation reservation1 = new Reservation();
        reservation1.setAppUser(appUser2);
        reservation1.setColumnId(123L);
        reservation1.setId(123L);
        reservation1.setRowId(123L);
        reservation1.setScreening(screening2);
        reservation1.setTimestamp(mock(Timestamp.class));
        assertThrows(Exception.class, () -> this.reservationServiceImplementation.updateReservation(123L, reservation1));
        verify(this.reservationRepo).findById((Long) any());
        verify(reservation, atLeast(1)).getAppUser();
        verify(reservation).getScreening();
        verify(reservation).setAppUser((AppUser) any());
        verify(reservation).setColumnId((Long) any());
        verify(reservation).setId((Long) any());
        verify(reservation).setRowId((Long) any());
        verify(reservation).setScreening((Screening) any());
        verify(reservation).setTimestamp((Timestamp) any());
        verify(appUser1).getUsername();
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setName((String) any());
        verify(appUser1).setPassword((String) any());
        verify(appUser1).setRoles((java.util.Collection<Role>) any());
        verify(appUser1).setUsername((String) any());
        verify(screening1).getId();
        verify(screening1).setEndTime((Timestamp) any());
        verify(screening1).setHall((Hall) any());
        verify(screening1).setId((Long) any());
        verify(screening1).setMovie((Movie) any());
        verify(screening1).setStartTime((Timestamp) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#deleteReservation(Long, String)}
     */
    @Test
    void testDeleteReservation() {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult = Optional.of(reservation);
        doNothing().when(this.reservationRepo).deleteById((Long) any());
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult);
        this.reservationServiceImplementation.deleteReservation(123L, "janedoe");
        verify(this.reservationRepo).findById((Long) any());
        verify(this.reservationRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#deleteReservation(Long, String)}
     */
    @Test
    void testDeleteReservation2() {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult = Optional.of(reservation);
        doThrow(new ResourceNotFoundException("An error occurred")).when(this.reservationRepo).deleteById((Long) any());
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.deleteReservation(123L, "janedoe"));
        verify(this.reservationRepo).findById((Long) any());
        verify(this.reservationRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#deleteReservation(Long, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteReservation3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.web.client.HttpClientErrorException$Forbidden: Not Allowed : Cannot delete other's reservation
        //       at org.springframework.web.client.HttpClientErrorException.create(HttpClientErrorException.java:109)
        //       at com.soumosir.bookmydream.service.ReservationServiceImplementation.deleteReservation(ReservationServiceImplementation.java:118)
        //   In order to prevent deleteReservation(Long, String)
        //   from throwing HttpClientErrorException$Forbidden, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deleteReservation(Long, String).
        //   See https://diff.blue/R013 to resolve this issue.

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getUsername()).thenReturn("foo");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        Reservation reservation = mock(Reservation.class);
        when(reservation.getAppUser()).thenReturn(appUser1);
        doNothing().when(reservation).setAppUser((AppUser) any());
        doNothing().when(reservation).setColumnId((Long) any());
        doNothing().when(reservation).setId((Long) any());
        doNothing().when(reservation).setRowId((Long) any());
        doNothing().when(reservation).setScreening((Screening) any());
        doNothing().when(reservation).setTimestamp((Timestamp) any());
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        Optional<Reservation> ofResult = Optional.of(reservation);
        doNothing().when(this.reservationRepo).deleteById((Long) any());
        when(this.reservationRepo.findById((Long) any())).thenReturn(ofResult);
        this.reservationServiceImplementation.deleteReservation(123L, "janedoe");
    }

    /**
     * Method under test: {@link ReservationServiceImplementation#deleteReservation(Long, String)}
     */
    @Test
    void testDeleteReservation4() {
        doNothing().when(this.reservationRepo).deleteById((Long) any());
        when(this.reservationRepo.findById((Long) any())).thenReturn(Optional.empty());

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");

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
        AppUser appUser1 = mock(AppUser.class);
        when(appUser1.getUsername()).thenReturn("janedoe");
        doNothing().when(appUser1).setEmail((String) any());
        doNothing().when(appUser1).setId((Long) any());
        doNothing().when(appUser1).setName((String) any());
        doNothing().when(appUser1).setPassword((String) any());
        doNothing().when(appUser1).setRoles((java.util.Collection<Role>) any());
        doNothing().when(appUser1).setUsername((String) any());
        appUser1.setEmail("jane.doe@example.org");
        appUser1.setId(123L);
        appUser1.setName("Name");
        appUser1.setPassword("iloveyou");
        appUser1.setRoles(new ArrayList<>());
        appUser1.setUsername("janedoe");
        Reservation reservation = mock(Reservation.class);
        when(reservation.getAppUser()).thenReturn(appUser1);
        doNothing().when(reservation).setAppUser((AppUser) any());
        doNothing().when(reservation).setColumnId((Long) any());
        doNothing().when(reservation).setId((Long) any());
        doNothing().when(reservation).setRowId((Long) any());
        doNothing().when(reservation).setScreening((Screening) any());
        doNothing().when(reservation).setTimestamp((Timestamp) any());
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(mock(Timestamp.class));
        assertThrows(ResourceNotFoundException.class,
                () -> this.reservationServiceImplementation.deleteReservation(123L, "janedoe"));
        verify(this.reservationRepo).findById((Long) any());
        verify(reservation).setAppUser((AppUser) any());
        verify(reservation).setColumnId((Long) any());
        verify(reservation).setId((Long) any());
        verify(reservation).setRowId((Long) any());
        verify(reservation).setScreening((Screening) any());
        verify(reservation).setTimestamp((Timestamp) any());
        verify(appUser1).setEmail((String) any());
        verify(appUser1).setId((Long) any());
        verify(appUser1).setName((String) any());
        verify(appUser1).setPassword((String) any());
        verify(appUser1).setRoles((java.util.Collection<Role>) any());
        verify(appUser1).setUsername((String) any());
    }
}

