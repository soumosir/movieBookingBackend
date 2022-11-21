package com.soumosir.bookmydream.model.helper;

import com.soumosir.bookmydream.model.AppUser;
import com.soumosir.bookmydream.model.Reservation;
import com.soumosir.bookmydream.model.Screening;
import com.soumosir.bookmydream.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservationRest {

    private Long id;
    private Timestamp timestamp;
    private Long rowNumber;
    private Long columnNumber;
    private String username;
    private Long screeningId;

    public ReservationRest(Reservation reservation){
        this.id = reservation.getId();
        this.timestamp = reservation.getTimestamp();
        this.username = reservation.getAppUser().getUsername();
        this.screeningId = reservation.getScreening().getId();
        this.rowNumber = reservation.getRowId();
        this.columnNumber = reservation.getColumnId();
    }
}