package com.example.sharemobility.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservationTest {
    @Mock
    Car car;
    @Mock
    User user;
    @InjectMocks
    Reservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetId() {
        reservation.setId(1L);
        assertEquals(1L, reservation.getId());
    }

    @Test
    void testSetCar() {
        reservation.setCar(new BevCar());
    }

    @Test
    void testSetUser() {
        reservation.setUser(new CarOwner("username", "password", "firstname", "lastname", "address"));
        assertEquals("username", reservation.getUser().getUsername());
    }

    @Test
    void testSetStartReservation() {
        reservation.setStartReservation(LocalDateTime.of(2022, Month.OCTOBER, 22, 21, 48, 4));
        assertEquals(LocalDateTime.of(2022, Month.OCTOBER, 22, 21, 48, 4), reservation.getStartReservation());
    }

    @Test
    void testSetEndReservation() {
        reservation.setEndReservation(LocalDateTime.of(2022, Month.OCTOBER, 22, 21, 48, 4));
        assertEquals(LocalDateTime.of(2022, Month.OCTOBER, 22, 21, 48, 4), reservation.getEndReservation());
    }

    @Test
    void checkGettersReservation() {
        Reservation reservation = new Reservation(car,user,LocalDateTime.of(2022,10,23,10,00,00), LocalDateTime.of(2022,10,23,15,00,00));

        // Testing the getters
        assertEquals(car, reservation.getCar());
        assertEquals(user, reservation.getUser());
        assertEquals(LocalDateTime.of(2022,10,23,10,00,00), reservation.getStartReservation());
        assertEquals(LocalDateTime.of(2022,10,23,15,00,00), reservation.getEndReservation());
    }
}