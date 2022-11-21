package com.soumosir.bookmydream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.sql.Timestamp;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private Timestamp timestamp;
    private Long rowId;
    private Long columnId;
    @OneToOne(fetch = EAGER)
    private AppUser appUser;
    @OneToOne(fetch = EAGER)
    private Screening screening;

    public void validate() throws ValidationException {


        if(rowId==null || columnId==null){
            throw new ValidationException(" row or column cannot be empty");
        }

        if(rowId<1 || rowId>200){
            throw new ValidationException(" row should be between 1 and 200");
        }

        if(columnId<1 || columnId>200){
            throw new ValidationException(" column should be between 1 and 200");
        }

        if(screening==null){
            throw new ValidationException(" Screening cannot be empty");
        }

        if(screening.getId()==null || !screening.getId().getClass().equals(Long.class)){
            throw new ValidationException(" Screening id should be present inside Screening object and must have id as a number");
        }


    }
}


