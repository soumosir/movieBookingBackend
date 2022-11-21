package com.soumosir.bookmydream.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.service.MovieService;

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

@ContextConfiguration(classes = {MovieController.class})
@ExtendWith(SpringExtension.class)
class MovieControllerTest {
    @Autowired
    private MovieController movieController;

    @MockBean
    private MovieService movieService;

    /**
     * Method under test: {@link MovieController#getMovies()}
     */
    @Test
    void testGetMovies() throws Exception {
        when(this.movieService.getMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/movie");
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MovieController#getMovies()}
     */
    @Test
    void testGetMovies2() throws Exception {
        when(this.movieService.getMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/movie");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MovieController#saveMovie(Movie)}
     */
    @Test
    void testSaveMovie() throws Exception {
        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        when(this.movieService.saveMovie((Movie) any())).thenReturn(movie);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(movie1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"director\":\"Director\",\"cast\":\"Cast\",\"description\":\"The characteristics of someone"
                                        + " or something\",\"duration\":1}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/movie"));
    }

    /**
     * Method under test: {@link MovieController#updateMovie(long, Movie)}
     */
    @Test
    void testUpdateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        when(this.movieService.updateMovie((Long) any(), (Movie) any())).thenReturn(movie);

        Movie movie1 = new Movie();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(movie1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/movie/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"director\":\"Director\",\"cast\":\"Cast\",\"description\":\"The characteristics of someone"
                                        + " or something\",\"duration\":1}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/movie/123"));
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(long)}
     */
    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(this.movieService).deleteMovie((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/movie/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(long)}
     */
    @Test
    void testDeleteMovie2() throws Exception {
        doNothing().when(this.movieService).deleteMovie((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/movie/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

