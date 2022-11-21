package com.soumosir.bookmydream.dbkeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class SeatKey implements Serializable {
    private Long rowId;
    private Long columnId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatKey seatKey = (SeatKey) o;
        return rowId.equals(seatKey.rowId) && columnId.equals(seatKey.columnId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowId, columnId);
    }
}