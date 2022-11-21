package com.soumosir.bookmydream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hall {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(unique=true,length = 50)
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private Long totalRows;
    @NotEmpty
    @NotNull
    private Long totalColumns;
    @ManyToMany(fetch = EAGER)
    private Collection<Seat> seats = new ArrayList<>();


    public void validate() throws ValidationException {


        if(name==null || (name!=null && name.length()<3)){
            throw new ValidationException("Hall name is less than 3 letters");
        }

        if(name.length()>30){
            throw new ValidationException("name cannot be  more than  30 letters");
        }

        if(totalRows==null || totalColumns==null){
            throw new ValidationException("Total rows or column cannot be empty");
        }

        if(totalRows<1 || totalRows>200){
            throw new ValidationException("Total rows should be between 1 and 200");
        }

        if(totalColumns<1 || totalColumns>200){
            throw new ValidationException("Total columns should be between 1 and 200");
        }

    }

}

