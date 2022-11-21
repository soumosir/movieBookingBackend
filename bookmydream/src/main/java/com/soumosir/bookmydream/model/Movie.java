package com.soumosir.bookmydream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(unique=true)
    @NotEmpty
    @NotNull
    private String title;
    @NotEmpty
    @NotNull
    private String director;
    @NotEmpty
    @NotNull
    private String cast;
    private String description;
    @NotEmpty
    @NotNull
    private Long duration;

    public void validate() throws ValidationException {


        if(title==null || (title!=null && title.length()<3)){
            throw new ValidationException("Movie title cannot be  less than 3 letters");
        }

        if(title.length()>20){
            throw new ValidationException("title cannot be  more than  20 letters");
        }

        if(duration==null){
            throw new ValidationException("Total duration cannot be empty");
        }

        if(duration<5 || duration>400){
            throw new ValidationException("Movie duration has tp be more than 4 mins and less than 400 mins");
        }

        if(cast==null || (cast!=null && cast.length()<3)){
            throw new ValidationException("Movie cast cannot be  less than 3 letters "+cast);
        }

        if(cast!=null && cast.length()>200){
            throw new ValidationException("Cast word limit exceeded");
        }

        if(director==null || (director!=null && director.length()<3)){
            throw new ValidationException("Movie director cannot be  less than 3 letters "+cast);
        }
        if(director!=null && director.length()>200){
            throw new ValidationException("director word limit exceeded");
        }


        if(description!=null && description.length()>200){
            throw new ValidationException("description word limit exceeded");
        }



    }
}