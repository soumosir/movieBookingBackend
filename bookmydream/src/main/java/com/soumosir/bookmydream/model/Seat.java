package com.soumosir.bookmydream.model;

import com.soumosir.bookmydream.dbkeys.SeatKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @EmbeddedId
    private SeatKey seatKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return seatKey.equals(seat.seatKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatKey);
    }
}


