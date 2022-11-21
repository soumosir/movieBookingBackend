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

import com.soumosir.bookmydream.dbkeys.SeatKey;
import com.soumosir.bookmydream.exceptions.ResourceNotFoundException;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.model.Seat;
import com.soumosir.bookmydream.repo.HallRepo;
import com.soumosir.bookmydream.repo.SeatRepo;

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

@ContextConfiguration(classes = {HallServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class HallServiceImplementationTest {
    @MockBean
    private HallRepo hallRepo;

    @Autowired
    private HallServiceImplementation hallServiceImplementation;

    @MockBean
    private SeatRepo seatRepo;

    /**
     * Method under test: {@link HallServiceImplementation#saveHall(Hall)}
     */
    @Test
    void testSaveHall() {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallRepo.save((Hall) any())).thenReturn(hall);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        assertSame(hall, this.hallServiceImplementation.saveHall(hall1));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.hallRepo).save((Hall) any());
        assertEquals(1, hall1.getSeats().size());
    }

    /**
     * Method under test: {@link HallServiceImplementation#saveHall(Hall)}
     */
    @Test
    void testSaveHall2() {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);
        when(this.hallRepo.save((Hall) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.saveHall(hall));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.hallRepo).save((Hall) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#saveHall(Hall)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveHall3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.soumosir.bookmydream.service.HallServiceImplementation.addSeatsToHall(HallServiceImplementation.java:32)
        //       at com.soumosir.bookmydream.service.HallServiceImplementation.saveHall(HallServiceImplementation.java:45)
        //   In order to prevent saveHall(Hall)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveHall(Hall).
        //   See https://diff.blue/R013 to resolve this issue.

        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(null);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallRepo.save((Hall) any())).thenReturn(hall);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        this.hallServiceImplementation.saveHall(hall1);
    }

    /**
     * Method under test: {@link HallServiceImplementation#saveHall(Hall)}
     */
    @Test
    void testSaveHall4() {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(Optional.empty());

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallRepo.save((Hall) any())).thenReturn(hall);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        assertSame(hall, this.hallServiceImplementation.saveHall(hall1));
        verify(this.seatRepo).save((Seat) any());
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.hallRepo).save((Hall) any());
        assertEquals(1, hall1.getSeats().size());
    }

    /**
     * Method under test: {@link HallServiceImplementation#saveHall(Hall)}
     */
    @Test
    void testSaveHall5() throws ValidationException {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallRepo.save((Hall) any())).thenReturn(hall);
        Hall hall1 = mock(Hall.class);
        when(hall1.getTotalColumns()).thenReturn(1L);
        when(hall1.getTotalRows()).thenReturn(1L);
        when(hall1.getName()).thenReturn("Name");
        doNothing().when(hall1).setId((Long) any());
        doNothing().when(hall1).setName((String) any());
        doNothing().when(hall1).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall1).setTotalColumns((Long) any());
        doNothing().when(hall1).setTotalRows((Long) any());
        doNothing().when(hall1).validate();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        assertSame(hall, this.hallServiceImplementation.saveHall(hall1));
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.hallRepo).save((Hall) any());
        verify(hall1, atLeast(1)).getTotalColumns();
        verify(hall1, atLeast(1)).getTotalRows();
        verify(hall1).getName();
        verify(hall1).setId((Long) any());
        verify(hall1).setName((String) any());
        verify(hall1, atLeast(1)).setSeats((java.util.Collection<Seat>) any());
        verify(hall1).setTotalColumns((Long) any());
        verify(hall1).setTotalRows((Long) any());
        verify(hall1).validate();
    }

    /**
     * Method under test: {@link HallServiceImplementation#saveHall(Hall)}
     */
    @Test
    void testSaveHall6() throws ValidationException {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallRepo.save((Hall) any())).thenReturn(hall);
        Hall hall1 = mock(Hall.class);
        when(hall1.getTotalColumns()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(hall1.getTotalRows()).thenReturn(1L);
        when(hall1.getName()).thenReturn("Name");
        doNothing().when(hall1).setId((Long) any());
        doNothing().when(hall1).setName((String) any());
        doNothing().when(hall1).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall1).setTotalColumns((Long) any());
        doNothing().when(hall1).setTotalRows((Long) any());
        doNothing().when(hall1).validate();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.saveHall(hall1));
        verify(hall1).getTotalColumns();
        verify(hall1).getTotalRows();
        verify(hall1).setId((Long) any());
        verify(hall1).setName((String) any());
        verify(hall1).setSeats((java.util.Collection<Seat>) any());
        verify(hall1).setTotalColumns((Long) any());
        verify(hall1).setTotalRows((Long) any());
        verify(hall1).validate();
    }

    /**
     * Method under test: {@link HallServiceImplementation#saveHall(Hall)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveHall7() throws ValidationException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.soumosir.bookmydream.service.HallServiceImplementation.addSeatsToHall(HallServiceImplementation.java:29)
        //       at com.soumosir.bookmydream.service.HallServiceImplementation.saveHall(HallServiceImplementation.java:45)
        //   In order to prevent saveHall(Hall)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveHall(Hall).
        //   See https://diff.blue/R013 to resolve this issue.

        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallRepo.save((Hall) any())).thenReturn(hall);
        Hall hall1 = mock(Hall.class);
        when(hall1.getTotalColumns()).thenReturn(null);
        when(hall1.getTotalRows()).thenReturn(1L);
        when(hall1.getName()).thenReturn("Name");
        doNothing().when(hall1).setId((Long) any());
        doNothing().when(hall1).setName((String) any());
        doNothing().when(hall1).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall1).setTotalColumns((Long) any());
        doNothing().when(hall1).setTotalRows((Long) any());
        doNothing().when(hall1).validate();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        this.hallServiceImplementation.saveHall(hall1);
    }

    /**
     * Method under test: {@link HallServiceImplementation#getHall(String)}
     */
    @Test
    void testGetHall() {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallRepo.findByName((String) any())).thenReturn(hall);
        assertSame(hall, this.hallServiceImplementation.getHall("Name"));
        verify(this.hallRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#getHall(String)}
     */
    @Test
    void testGetHall2() {
        when(this.hallRepo.findByName((String) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.getHall("Name"));
        verify(this.hallRepo).findByName((String) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#getHalls()}
     */
    @Test
    void testGetHalls() {
        ArrayList<Hall> hallList = new ArrayList<>();
        when(this.hallRepo.findAll()).thenReturn(hallList);
        List<Hall> actualHalls = this.hallServiceImplementation.getHalls();
        assertSame(hallList, actualHalls);
        assertTrue(actualHalls.isEmpty());
        verify(this.hallRepo).findAll();
    }

    /**
     * Method under test: {@link HallServiceImplementation#getHalls()}
     */
    @Test
    void testGetHalls2() {
        when(this.hallRepo.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.getHalls());
        verify(this.hallRepo).findAll();
    }

    /**
     * Method under test: {@link HallServiceImplementation#updateHall(Long, Hall)}
     */
    @Test
    void testUpdateHall() {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult1 = Optional.of(hall);
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        Hall actualUpdateHallResult = this.hallServiceImplementation.updateHall(123L, hall1);
        assertSame(hall, actualUpdateHallResult);
        assertEquals(1L, actualUpdateHallResult.getTotalRows().longValue());
        assertEquals(1L, actualUpdateHallResult.getTotalColumns().longValue());
        assertEquals(1, actualUpdateHallResult.getSeats().size());
        assertEquals("Name", actualUpdateHallResult.getName());
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.hallRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#updateHall(Long, Hall)}
     */
    @Test
    void testUpdateHall2() {
        when(this.seatRepo.save((Seat) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(this.seatRepo.findById((com.soumosir.bookmydream.dbkeys.SeatKey) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult = Optional.of(hall);
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.updateHall(123L, hall1));
        verify(this.seatRepo).findById((com.soumosir.bookmydream.dbkeys.SeatKey) any());
        verify(this.hallRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#updateHall(Long, Hall)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateHall3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   In order to prevent updateHall(Long, Hall)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateHall(Long, Hall).
        //   See https://diff.blue/R013 to resolve this issue.

        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(null);

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult = Optional.of(hall);
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        this.hallServiceImplementation.updateHall(123L, hall1);
    }

    /**
     * Method under test: {@link HallServiceImplementation#updateHall(Long, Hall)}
     */
    @Test
    void testUpdateHall4() {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(Optional.empty());

        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult = Optional.of(hall);
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        Hall actualUpdateHallResult = this.hallServiceImplementation.updateHall(123L, hall1);
        assertSame(hall, actualUpdateHallResult);
        assertEquals(1L, actualUpdateHallResult.getTotalRows().longValue());
        assertEquals(1L, actualUpdateHallResult.getTotalColumns().longValue());
        assertEquals(1, actualUpdateHallResult.getSeats().size());
        assertEquals("Name", actualUpdateHallResult.getName());
        verify(this.seatRepo).save((Seat) any());
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.hallRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#updateHall(Long, Hall)}
     */
    @Test
    void testUpdateHall5() {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);
        Hall hall = mock(Hall.class);
        when(hall.getTotalColumns()).thenReturn(1L);
        when(hall.getId()).thenReturn(123L);
        when(hall.getTotalRows()).thenReturn(1L);
        when(hall.getName()).thenReturn("Name");
        doNothing().when(hall).setId((Long) any());
        doNothing().when(hall).setName((String) any());
        doNothing().when(hall).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall).setTotalColumns((Long) any());
        doNothing().when(hall).setTotalRows((Long) any());
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult1 = Optional.of(hall);
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        this.hallServiceImplementation.updateHall(123L, hall1);
        verify(this.seatRepo).findById((SeatKey) any());
        verify(this.hallRepo).findById((Long) any());
        verify(hall).getId();
        verify(hall, atLeast(1)).getTotalColumns();
        verify(hall, atLeast(1)).getTotalRows();
        verify(hall).getName();
        verify(hall).setId((Long) any());
        verify(hall, atLeast(1)).setName((String) any());
        verify(hall, atLeast(1)).setSeats((java.util.Collection<Seat>) any());
        verify(hall, atLeast(1)).setTotalColumns((Long) any());
        verify(hall, atLeast(1)).setTotalRows((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#updateHall(Long, Hall)}
     */
    @Test
    void testUpdateHall6() {
        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);
        Hall hall = mock(Hall.class);
        when(hall.getTotalColumns()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(hall.getId()).thenReturn(123L);
        when(hall.getTotalRows()).thenReturn(1L);
        when(hall.getName()).thenReturn("Name");
        doNothing().when(hall).setId((Long) any());
        doNothing().when(hall).setName((String) any());
        doNothing().when(hall).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall).setTotalColumns((Long) any());
        doNothing().when(hall).setTotalRows((Long) any());
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult1 = Optional.of(hall);
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.updateHall(123L, hall1));
        verify(this.hallRepo).findById((Long) any());
        verify(hall).getTotalColumns();
        verify(hall).getTotalRows();
        verify(hall).setId((Long) any());
        verify(hall, atLeast(1)).setName((String) any());
        verify(hall).setSeats((java.util.Collection<Seat>) any());
        verify(hall, atLeast(1)).setTotalColumns((Long) any());
        verify(hall, atLeast(1)).setTotalRows((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#updateHall(Long, Hall)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateHall7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   In order to prevent updateHall(Long, Hall)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateHall(Long, Hall).
        //   See https://diff.blue/R013 to resolve this issue.

        SeatKey seatKey = new SeatKey();
        seatKey.setColumnId(123L);
        seatKey.setRowId(123L);

        Seat seat = new Seat();
        seat.setSeatKey(seatKey);

        SeatKey seatKey1 = new SeatKey();
        seatKey1.setColumnId(123L);
        seatKey1.setRowId(123L);

        Seat seat1 = new Seat();
        seat1.setSeatKey(seatKey1);
        Optional<Seat> ofResult = Optional.of(seat1);
        when(this.seatRepo.save((Seat) any())).thenReturn(seat);
        when(this.seatRepo.findById((SeatKey) any())).thenReturn(ofResult);
        Hall hall = mock(Hall.class);
        when(hall.getTotalColumns()).thenReturn(null);
        when(hall.getId()).thenReturn(123L);
        when(hall.getTotalRows()).thenReturn(1L);
        when(hall.getName()).thenReturn("Name");
        doNothing().when(hall).setId((Long) any());
        doNothing().when(hall).setName((String) any());
        doNothing().when(hall).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall).setTotalColumns((Long) any());
        doNothing().when(hall).setTotalRows((Long) any());
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult1 = Optional.of(hall);
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult1);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        this.hallServiceImplementation.updateHall(123L, hall1);
    }

    /**
     * Method under test: {@link HallServiceImplementation#deleteHall(Long)}
     */
    @Test
    void testDeleteHall() {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult = Optional.of(hall);
        doNothing().when(this.hallRepo).deleteById((Long) any());
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult);
        this.hallServiceImplementation.deleteHall(123L);
        verify(this.hallRepo).findById((Long) any());
        verify(this.hallRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#deleteHall(Long)}
     */
    @Test
    void testDeleteHall2() {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult = Optional.of(hall);
        doThrow(new ResourceNotFoundException("An error occurred")).when(this.hallRepo).deleteById((Long) any());
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.deleteHall(123L));
        verify(this.hallRepo).findById((Long) any());
        verify(this.hallRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#deleteHall(Long)}
     */
    @Test
    void testDeleteHall3() {
        Hall hall = mock(Hall.class);
        when(hall.getId()).thenReturn(123L);
        when(hall.getName()).thenReturn("Name");
        doNothing().when(hall).setId((Long) any());
        doNothing().when(hall).setName((String) any());
        doNothing().when(hall).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall).setTotalColumns((Long) any());
        doNothing().when(hall).setTotalRows((Long) any());
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        Optional<Hall> ofResult = Optional.of(hall);
        doNothing().when(this.hallRepo).deleteById((Long) any());
        when(this.hallRepo.findById((Long) any())).thenReturn(ofResult);
        this.hallServiceImplementation.deleteHall(123L);
        verify(this.hallRepo).findById((Long) any());
        verify(this.hallRepo).deleteById((Long) any());
        verify(hall).getId();
        verify(hall).getName();
        verify(hall).setId((Long) any());
        verify(hall).setName((String) any());
        verify(hall).setSeats((java.util.Collection<Seat>) any());
        verify(hall).setTotalColumns((Long) any());
        verify(hall).setTotalRows((Long) any());
    }

    /**
     * Method under test: {@link HallServiceImplementation#deleteHall(Long)}
     */
    @Test
    void testDeleteHall4() {
        doNothing().when(this.hallRepo).deleteById((Long) any());
        when(this.hallRepo.findById((Long) any())).thenReturn(Optional.empty());
        Hall hall = mock(Hall.class);
        when(hall.getId()).thenReturn(123L);
        when(hall.getName()).thenReturn("Name");
        doNothing().when(hall).setId((Long) any());
        doNothing().when(hall).setName((String) any());
        doNothing().when(hall).setSeats((java.util.Collection<Seat>) any());
        doNothing().when(hall).setTotalColumns((Long) any());
        doNothing().when(hall).setTotalRows((Long) any());
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        assertThrows(ResourceNotFoundException.class, () -> this.hallServiceImplementation.deleteHall(123L));
        verify(this.hallRepo).findById((Long) any());
        verify(hall).setId((Long) any());
        verify(hall).setName((String) any());
        verify(hall).setSeats((java.util.Collection<Seat>) any());
        verify(hall).setTotalColumns((Long) any());
        verify(hall).setTotalRows((Long) any());
    }
}

