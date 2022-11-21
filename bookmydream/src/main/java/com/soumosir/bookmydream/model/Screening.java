package com.soumosir.bookmydream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Screening {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty
    @NotNull
    private Timestamp startTime;
    @OneToOne(fetch = EAGER)
    private Movie movie;
    @OneToOne(fetch = EAGER)
    private Hall hall;
    private Timestamp endTime;

    public void validate() throws ValidationException {


        if(startTime==null){
            throw new ValidationException(" Start time cannot be empty");
        }

        try{
            Timestamp time = startTime;
            if(time.compareTo(Timestamp.valueOf(LocalDateTime.now()))<0){
                throw new ValidationException(" Start time cannot be less than time now ");
            }
        }
        catch (Exception e){
            throw new ValidationException(" Start time is not valid ");
        }


        if(movie==null){
            throw new ValidationException(" Movie cannot be empty");
        }

        if(movie.getId()==null || !movie.getId().getClass().equals(Long.class)){
            throw new ValidationException(" Movie id should be present inside Movie object and must have id as a number");
        }

        if(hall==null){
            throw new ValidationException(" hall cannot be empty");
        }

        if(hall.getId()==null || !hall.getId().getClass().equals(Long.class)){
            throw new ValidationException(" hall id should be present inside hall object and must have id as a number");
        }


    }

}

