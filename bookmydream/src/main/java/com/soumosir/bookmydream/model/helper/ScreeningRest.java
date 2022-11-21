package com.soumosir.bookmydream.model.helper;

import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Movie;
import com.soumosir.bookmydream.model.Screening;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ScreeningRest {
    @NotEmpty
    @NotNull
    private Long id;
    @NotEmpty
    @NotNull
    private Timestamp startTime;
    @NotEmpty
    @NotNull
    private Long movieId;
    @NotEmpty
    @NotNull
    private String movieTitle;
    @NotEmpty
    @NotNull
    private Long hallId;
    @NotEmpty
    @NotNull
    private String hallName;
    private Timestamp endTime;

    public ScreeningRest(Screening screening){
        this.id = screening.getId();
        this.startTime = screening.getStartTime();
        this.endTime = screening.getEndTime();
        this.hallId = screening.getHall().getId();
        this.hallName = screening.getHall().getName();
        this.movieId = screening.getMovie().getId();
        this.movieTitle = screening.getMovie().getTitle();
        this.endTime = screening.getEndTime();
    }
}


