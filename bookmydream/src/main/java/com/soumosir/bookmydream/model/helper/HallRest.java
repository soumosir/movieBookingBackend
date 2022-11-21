package com.soumosir.bookmydream.model.helper;

import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HallRest {

    private Long id;
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private Long totalRows;
    @NotEmpty
    @NotNull
    private Long totalColumns;

    public HallRest(Hall hall){
        this.id = hall.getId();
        this.name = hall.getName();
        this.totalColumns = hall.getTotalColumns();
        this.totalRows = hall.getTotalRows();
    }

}

