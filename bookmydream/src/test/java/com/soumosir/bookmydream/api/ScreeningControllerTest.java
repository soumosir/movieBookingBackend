package com.soumosir.bookmydream.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.service.ScreeningService;

import java.sql.Timestamp;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ScreeningController.class})
@ExtendWith(SpringExtension.class)
class ScreeningControllerTest {
    @Autowired
    private ScreeningController screeningController;

    @MockBean
    private ScreeningService screeningService;

    /**
     * Method under test: {@link ScreeningController#getScreenings()}
     */
    @Test
    void testGetScreenings() throws Exception {
        when(this.screeningService.getScreenings()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/screening");
        MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ScreeningController#getScreenings()}
     */
    @Test
    void testGetScreenings2() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("?");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);

        Movie movie = new Movie();
        movie.setCast("?");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("?");
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

        ArrayList<Screening> screeningList = new ArrayList<>();
        screeningList.add(screening);
        when(this.screeningService.getScreenings()).thenReturn(screeningList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/screening");
        MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"startTime\":10,\"movieId\":123,\"movieTitle\":\"Dr\",\"hallId\":123,\"hallName\":\"?\",\"endTime\":10}]"));
    }

    /**
     * Method under test: {@link ScreeningController#getScreenings()}
     */
    @Test
    void testGetScreenings3() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("?");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);

        Movie movie = new Movie();
        movie.setCast("?");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("?");
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

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("?");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("?");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("?");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        Timestamp timestamp3 = mock(Timestamp.class);
        when(timestamp3.getTime()).thenReturn(10L);

        Screening screening1 = new Screening();
        screening1.setEndTime(timestamp2);
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(timestamp3);

        ArrayList<Screening> screeningList = new ArrayList<>();
        screeningList.add(screening1);
        screeningList.add(screening);
        when(this.screeningService.getScreenings()).thenReturn(screeningList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/screening");
        MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"startTime\":10,\"movieId\":123,\"movieTitle\":\"Dr\",\"hallId\":123,\"hallName\":\"?\",\"endTime\":10},"
                                        + "{\"id\":123,\"startTime\":10,\"movieId\":123,\"movieTitle\":\"Dr\",\"hallId\":123,\"hallName\":\"?\",\"endTime\":10}]"));
    }

    /**
     * Method under test: {@link ScreeningController#getScreenings()}
     */
    @Test
    void testGetScreenings4() throws Exception {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("?");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);

        Movie movie = new Movie();
        movie.setCast("?");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("?");
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

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("?");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        Movie movie1 = new Movie();
        movie1.setCast("?");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("?");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        Timestamp timestamp3 = mock(Timestamp.class);
        when(timestamp3.getTime()).thenReturn(10L);

        Screening screening1 = new Screening();
        screening1.setEndTime(timestamp2);
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(timestamp3);

        ArrayList<Screening> screeningList = new ArrayList<>();
        screeningList.add(screening1);
        screeningList.add(screening);
        when(this.screeningService.getScreenings()).thenReturn(screeningList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/screening");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"startTime\":10,\"movieId\":123,\"movieTitle\":\"Dr\",\"hallId\":123,\"hallName\":\"?\",\"endTime\":10},"
                                        + "{\"id\":123,\"startTime\":10,\"movieId\":123,\"movieTitle\":\"Dr\",\"hallId\":123,\"hallName\":\"?\",\"endTime\":10}]"));
    }

    /**
     * Method under test: {@link ScreeningController#saveScreening(Screening, javax.servlet.http.HttpServletResponse)}
     */
    @Test
    void testSaveScreening() throws Exception {
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
        when(this.screeningService.saveScreening((Screening) any())).thenReturn(screening);
        Timestamp timestamp2 = mock(Timestamp.class);
        when(timestamp2.getTime()).thenReturn(10L);

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
        Timestamp timestamp3 = mock(Timestamp.class);
        when(timestamp3.getTime()).thenReturn(10L);

        Screening screening1 = new Screening();
        screening1.setEndTime(timestamp2);
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(timestamp3);
        String content = (new ObjectMapper()).writeValueAsString(screening1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/screening")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"startTime\":10,\"movieId\":123,\"movieTitle\":\"Dr\",\"hallId\":123,\"hallName\":\"Name\","
                                        + "\"endTime\":10}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/hall"));
    }

    /**
     * Method under test: {@link ScreeningController#updateScreening(long, Screening)}
     */
    @Test
    void testUpdateScreening() throws Exception {
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
        when(this.screeningService.updateScreening((Long) any(), (Screening) any())).thenReturn(screening);
        Timestamp timestamp2 = mock(Timestamp.class);
        when(timestamp2.getTime()).thenReturn(10L);

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
        Timestamp timestamp3 = mock(Timestamp.class);
        when(timestamp3.getTime()).thenReturn(10L);

        Screening screening1 = new Screening();
        screening1.setEndTime(timestamp2);
        screening1.setHall(hall1);
        screening1.setId(123L);
        screening1.setMovie(movie1);
        screening1.setStartTime(timestamp3);
        String content = (new ObjectMapper()).writeValueAsString(screening1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/screening/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"startTime\":10,\"movieId\":123,\"movieTitle\":\"Dr\",\"hallId\":123,\"hallName\":\"Name\","
                                        + "\"endTime\":10}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/screening/123"));
    }

    /**
     * Method under test: {@link ScreeningController#deleteScreening(long)}
     */
    @Test
    void testDeleteScreening() throws Exception {
        doNothing().when(this.screeningService).deleteScreening((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/screening/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ScreeningController#deleteScreening(long)}
     */
    @Test
    void testDeleteScreening2() throws Exception {
        doNothing().when(this.screeningService).deleteScreening((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/screening/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.screeningController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

