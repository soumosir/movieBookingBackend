package com.soumosir.bookmydream.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.Reservation;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.repo.AppUserRepo;
import com.soumosir.bookmydream.repo.RoleRepo;
import com.soumosir.bookmydream.service.ReservationService;
import com.sun.security.auth.UserPrincipal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ReservationController.class})
@ExtendWith(SpringExtension.class)
class ReservationControllerTest {
    @MockBean
    private AppUserRepo appUserRepo;

    @Autowired
    private ReservationController reservationController;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private RoleRepo roleRepo;

    /**
     * Method under test: {@link ReservationController#deleteReservation(long, java.security.Principal)}
     */
    @Test
    void testDeleteReservation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/reservation/{id}",
                "Uri Variables", "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reservationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ReservationController#deleteReservation(long, java.security.Principal)}
     */
    @Test
    void testDeleteReservation2() throws Exception {
        doNothing().when(this.reservationService).deleteReservation((Long) any(), (String) any());

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/reservation/{id}", 123L);
        deleteResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(this.reservationController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ReservationController#deleteReservation(long, java.security.Principal)}
     */
    @Test
    void testDeleteReservation3() throws Exception {
        doNothing().when(this.reservationService).deleteReservation((Long) any(), (String) any());

        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepo.findByUsername((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/reservation/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        deleteResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(this.reservationController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ReservationController#getReservations(org.springframework.security.core.Authentication)}
     */
    @Test
    void testGetReservations() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reservationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ReservationController#saveScreening(Reservation, javax.servlet.http.HttpServletResponse, java.security.Principal)}
     */
    @Test
    void testSaveScreening() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

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
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Screening screening = new Screening();
        screening.setEndTime(timestamp);
        screening.setHall(hall);
        screening.setId(123L);
        screening.setMovie(movie);
        screening.setStartTime(timestamp1);
        Timestamp timestamp2 = mock(Timestamp.class);
        when(timestamp2.getTime()).thenReturn(10L);

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(timestamp2);
        String content = (new ObjectMapper()).writeValueAsString(reservation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reservationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }

    /**
     * Method under test: {@link ReservationController#updateReservation(long, Reservation, javax.servlet.http.HttpServletResponse, java.security.Principal)}
     */
    @Test
    void testUpdateReservation() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(123L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRoles(new ArrayList<>());
        appUser.setUsername("janedoe");
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

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
        Timestamp timestamp1 = mock(Timestamp.class);
        when(timestamp1.getTime()).thenReturn(10L);

        Screening screening = new Screening();
        screening.setEndTime(timestamp);
        screening.setHall(hall);
        screening.setId(123L);
        screening.setMovie(movie);
        screening.setStartTime(timestamp1);
        Timestamp timestamp2 = mock(Timestamp.class);
        when(timestamp2.getTime()).thenReturn(10L);

        Reservation reservation = new Reservation();
        reservation.setAppUser(appUser);
        reservation.setColumnId(123L);
        reservation.setId(123L);
        reservation.setRowId(123L);
        reservation.setScreening(screening);
        reservation.setTimestamp(timestamp2);
        String content = (new ObjectMapper()).writeValueAsString(reservation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/reservation/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reservationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error_message\":null}"));
    }
}

