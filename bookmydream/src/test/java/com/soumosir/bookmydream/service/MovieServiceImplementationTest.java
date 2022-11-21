package com.soumosir.bookmydream.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.repo.MovieRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import javax.validation.ValidationException;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MovieServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class MovieServiceImplementationTest {
    @MockBean
    private MovieRepo movieRepo;

    @Autowired
    private MovieServiceImplementation movieServiceImplementation;

    /**
     * Method under test: {@link MovieServiceImplementation#saveMovie(Movie)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveMovie() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.validation.ValidationException: Movie title cannot be  less than 3 letters
        //       at com.soumosir.bookmydream.model.Movie.validate(Movie.java:44)
        //       at com.soumosir.bookmydream.service.MovieServiceImplementation.saveMovie(MovieServiceImplementation.java:25)
        //   In order to prevent saveMovie(Movie)
        //   from throwing ValidationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveMovie(Movie).
        //   See https://diff.blue/R013 to resolve this issue.

        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        this.movieServiceImplementation.saveMovie(movie);
    }

    /**
     * Method under test: {@link MovieServiceImplementation#saveMovie(Movie)}
     */
    @Test
    void testSaveMovie2() throws ValidationException {
        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        when(this.movieRepo.save((Movie) any())).thenReturn(movie);
        Movie movie1 = mock(Movie.class);
        when(movie1.getTitle()).thenReturn("Dr");
        doNothing().when(movie1).setCast((String) any());
        doNothing().when(movie1).setDescription((String) any());
        doNothing().when(movie1).setDirector((String) any());
        doNothing().when(movie1).setDuration((Long) any());
        doNothing().when(movie1).setId((Long) any());
        doNothing().when(movie1).setTitle((String) any());
        doNothing().when(movie1).validate();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        assertSame(movie, this.movieServiceImplementation.saveMovie(movie1));
        verify(this.movieRepo).save((Movie) any());
        verify(movie1).getTitle();
        verify(movie1).setCast((String) any());
        verify(movie1).setDescription((String) any());
        verify(movie1).setDirector((String) any());
        verify(movie1).setDuration((Long) any());
        verify(movie1).setId((Long) any());
        verify(movie1).setTitle((String) any());
        verify(movie1).validate();
    }

    /**
     * Method under test: {@link MovieServiceImplementation#updateMovie(Long, Movie)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateMovie() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.validation.ValidationException: Movie title cannot be  less than 3 letters
        //       at com.soumosir.bookmydream.model.Movie.validate(Movie.java:44)
        //       at com.soumosir.bookmydream.service.MovieServiceImplementation.updateMovie(MovieServiceImplementation.java:34)
        //   In order to prevent updateMovie(Long, Movie)
        //   from throwing ValidationException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateMovie(Long, Movie).
        //   See https://diff.blue/R013 to resolve this issue.

        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        this.movieServiceImplementation.updateMovie(123L, movie);
    }

    /**
     * Method under test: {@link MovieServiceImplementation#updateMovie(Long, Movie)}
     */
    @Test
    void testUpdateMovie2() throws ValidationException {
        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        when(this.movieRepo.findById((Long) any())).thenReturn(ofResult);
        Movie movie1 = mock(Movie.class);
        when(movie1.getCast()).thenReturn("Cast");
        when(movie1.getDescription()).thenReturn("The characteristics of someone or something");
        when(movie1.getDirector()).thenReturn("Director");
        when(movie1.getTitle()).thenReturn("Dr");
        doNothing().when(movie1).setCast((String) any());
        doNothing().when(movie1).setDescription((String) any());
        doNothing().when(movie1).setDirector((String) any());
        doNothing().when(movie1).setDuration((Long) any());
        doNothing().when(movie1).setId((Long) any());
        doNothing().when(movie1).setTitle((String) any());
        doNothing().when(movie1).validate();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        Movie actualUpdateMovieResult = this.movieServiceImplementation.updateMovie(123L, movie1);
        assertSame(movie, actualUpdateMovieResult);
        assertEquals("Cast", actualUpdateMovieResult.getCast());
        assertEquals("Dr", actualUpdateMovieResult.getTitle());
        assertEquals("Director", actualUpdateMovieResult.getDirector());
        assertEquals("The characteristics of someone or something", actualUpdateMovieResult.getDescription());
        verify(this.movieRepo).findById((Long) any());
        verify(movie1).getCast();
        verify(movie1).getDescription();
        verify(movie1).getDirector();
        verify(movie1).getTitle();
        verify(movie1).setCast((String) any());
        verify(movie1).setDescription((String) any());
        verify(movie1).setDirector((String) any());
        verify(movie1).setDuration((Long) any());
        verify(movie1).setId((Long) any());
        verify(movie1).setTitle((String) any());
        verify(movie1).validate();
    }

    /**
     * Method under test: {@link MovieServiceImplementation#updateMovie(Long, Movie)}
     */
    @Test
    void testUpdateMovie3() throws ValidationException {
        Movie movie = mock(Movie.class);
        when(movie.getId()).thenReturn(123L);
        when(movie.getTitle()).thenReturn("Dr");
        doNothing().when(movie).setCast((String) any());
        doNothing().when(movie).setDescription((String) any());
        doNothing().when(movie).setDirector((String) any());
        doNothing().when(movie).setDuration((Long) any());
        doNothing().when(movie).setId((Long) any());
        doNothing().when(movie).setTitle((String) any());
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        when(this.movieRepo.findById((Long) any())).thenReturn(ofResult);
        Movie movie1 = mock(Movie.class);
        when(movie1.getCast()).thenReturn("Cast");
        when(movie1.getDescription()).thenReturn("The characteristics of someone or something");
        when(movie1.getDirector()).thenReturn("Director");
        when(movie1.getTitle()).thenReturn("Dr");
        doNothing().when(movie1).setCast((String) any());
        doNothing().when(movie1).setDescription((String) any());
        doNothing().when(movie1).setDirector((String) any());
        doNothing().when(movie1).setDuration((Long) any());
        doNothing().when(movie1).setId((Long) any());
        doNothing().when(movie1).setTitle((String) any());
        doNothing().when(movie1).validate();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        this.movieServiceImplementation.updateMovie(123L, movie1);
        verify(this.movieRepo).findById((Long) any());
        verify(movie).getId();
        verify(movie).getTitle();
        verify(movie, atLeast(1)).setCast((String) any());
        verify(movie, atLeast(1)).setDescription((String) any());
        verify(movie, atLeast(1)).setDirector((String) any());
        verify(movie).setDuration((Long) any());
        verify(movie).setId((Long) any());
        verify(movie, atLeast(1)).setTitle((String) any());
        verify(movie1).getCast();
        verify(movie1).getDescription();
        verify(movie1).getDirector();
        verify(movie1).getTitle();
        verify(movie1).setCast((String) any());
        verify(movie1).setDescription((String) any());
        verify(movie1).setDirector((String) any());
        verify(movie1).setDuration((Long) any());
        verify(movie1).setId((Long) any());
        verify(movie1).setTitle((String) any());
        verify(movie1).validate();
    }

    /**
     * Method under test: {@link MovieServiceImplementation#updateMovie(Long, Movie)}
     */
    @Test
    void testUpdateMovie4() throws ValidationException {
        when(this.movieRepo.findById((Long) any())).thenReturn(Optional.empty());
        Movie movie = mock(Movie.class);
        when(movie.getId()).thenReturn(123L);
        when(movie.getTitle()).thenReturn("Dr");
        doNothing().when(movie).setCast((String) any());
        doNothing().when(movie).setDescription((String) any());
        doNothing().when(movie).setDirector((String) any());
        doNothing().when(movie).setDuration((Long) any());
        doNothing().when(movie).setId((Long) any());
        doNothing().when(movie).setTitle((String) any());
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        Movie movie1 = mock(Movie.class);
        when(movie1.getCast()).thenReturn("Cast");
        when(movie1.getDescription()).thenReturn("The characteristics of someone or something");
        when(movie1.getDirector()).thenReturn("Director");
        when(movie1.getTitle()).thenReturn("Dr");
        doNothing().when(movie1).setCast((String) any());
        doNothing().when(movie1).setDescription((String) any());
        doNothing().when(movie1).setDirector((String) any());
        doNothing().when(movie1).setDuration((Long) any());
        doNothing().when(movie1).setId((Long) any());
        doNothing().when(movie1).setTitle((String) any());
        doNothing().when(movie1).validate();
        movie1.setCast("Cast");
        movie1.setDescription("The characteristics of someone or something");
        movie1.setDirector("Director");
        movie1.setDuration(1L);
        movie1.setId(123L);
        movie1.setTitle("Dr");
        assertThrows(ResourceNotFoundException.class, () -> this.movieServiceImplementation.updateMovie(123L, movie1));
        verify(this.movieRepo).findById((Long) any());
        verify(movie).setCast((String) any());
        verify(movie).setDescription((String) any());
        verify(movie).setDirector((String) any());
        verify(movie).setDuration((Long) any());
        verify(movie).setId((Long) any());
        verify(movie).setTitle((String) any());
        verify(movie1).setCast((String) any());
        verify(movie1).setDescription((String) any());
        verify(movie1).setDirector((String) any());
        verify(movie1).setDuration((Long) any());
        verify(movie1).setId((Long) any());
        verify(movie1).setTitle((String) any());
        verify(movie1).validate();
    }

    /**
     * Method under test: {@link MovieServiceImplementation#deleteMovie(Long)}
     */
    @Test
    void testDeleteMovie() {
        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        doNothing().when(this.movieRepo).deleteById((Long) any());
        when(this.movieRepo.findById((Long) any())).thenReturn(ofResult);
        this.movieServiceImplementation.deleteMovie(123L);
        verify(this.movieRepo).findById((Long) any());
        verify(this.movieRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link MovieServiceImplementation#deleteMovie(Long)}
     */
    @Test
    void testDeleteMovie2() {
        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        doThrow(new ResourceNotFoundException("An error occurred")).when(this.movieRepo).deleteById((Long) any());
        when(this.movieRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> this.movieServiceImplementation.deleteMovie(123L));
        verify(this.movieRepo).findById((Long) any());
        verify(this.movieRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link MovieServiceImplementation#deleteMovie(Long)}
     */
    @Test
    void testDeleteMovie3() {
        Movie movie = mock(Movie.class);
        when(movie.getId()).thenReturn(123L);
        when(movie.getTitle()).thenReturn("Dr");
        doNothing().when(movie).setCast((String) any());
        doNothing().when(movie).setDescription((String) any());
        doNothing().when(movie).setDirector((String) any());
        doNothing().when(movie).setDuration((Long) any());
        doNothing().when(movie).setId((Long) any());
        doNothing().when(movie).setTitle((String) any());
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        doNothing().when(this.movieRepo).deleteById((Long) any());
        when(this.movieRepo.findById((Long) any())).thenReturn(ofResult);
        this.movieServiceImplementation.deleteMovie(123L);
        verify(this.movieRepo).findById((Long) any());
        verify(this.movieRepo).deleteById((Long) any());
        verify(movie).getId();
        verify(movie).getTitle();
        verify(movie).setCast((String) any());
        verify(movie).setDescription((String) any());
        verify(movie).setDirector((String) any());
        verify(movie).setDuration((Long) any());
        verify(movie).setId((Long) any());
        verify(movie).setTitle((String) any());
    }

    /**
     * Method under test: {@link MovieServiceImplementation#deleteMovie(Long)}
     */
    @Test
    void testDeleteMovie4() {
        doNothing().when(this.movieRepo).deleteById((Long) any());
        when(this.movieRepo.findById((Long) any())).thenReturn(Optional.empty());
        Movie movie = mock(Movie.class);
        when(movie.getId()).thenReturn(123L);
        when(movie.getTitle()).thenReturn("Dr");
        doNothing().when(movie).setCast((String) any());
        doNothing().when(movie).setDescription((String) any());
        doNothing().when(movie).setDirector((String) any());
        doNothing().when(movie).setDuration((Long) any());
        doNothing().when(movie).setId((Long) any());
        doNothing().when(movie).setTitle((String) any());
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        assertThrows(ResourceNotFoundException.class, () -> this.movieServiceImplementation.deleteMovie(123L));
        verify(this.movieRepo).findById((Long) any());
        verify(movie).setCast((String) any());
        verify(movie).setDescription((String) any());
        verify(movie).setDirector((String) any());
        verify(movie).setDuration((Long) any());
        verify(movie).setId((Long) any());
        verify(movie).setTitle((String) any());
    }

    /**
     * Method under test: {@link MovieServiceImplementation#getMovie(String)}
     */
    @Test
    void testGetMovie() {
        Movie movie = new Movie();
        movie.setCast("Cast");
        movie.setDescription("The characteristics of someone or something");
        movie.setDirector("Director");
        movie.setDuration(1L);
        movie.setId(123L);
        movie.setTitle("Dr");
        when(this.movieRepo.findByTitle((String) any())).thenReturn(movie);
        assertSame(movie, this.movieServiceImplementation.getMovie("Name"));
        verify(this.movieRepo).findByTitle((String) any());
    }

    /**
     * Method under test: {@link MovieServiceImplementation#getMovie(String)}
     */
    @Test
    void testGetMovie2() {
        when(this.movieRepo.findByTitle((String) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.movieServiceImplementation.getMovie("Name"));
        verify(this.movieRepo).findByTitle((String) any());
    }

    /**
     * Method under test: {@link MovieServiceImplementation#getMovies()}
     */
    @Test
    void testGetMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        when(this.movieRepo.findAll()).thenReturn(movieList);
        List<Movie> actualMovies = this.movieServiceImplementation.getMovies();
        assertSame(movieList, actualMovies);
        assertTrue(actualMovies.isEmpty());
        verify(this.movieRepo).findAll();
    }

    /**
     * Method under test: {@link MovieServiceImplementation#getMovies()}
     */
    @Test
    void testGetMovies2() {
        when(this.movieRepo.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.movieServiceImplementation.getMovies());
        verify(this.movieRepo).findAll();
    }
}

