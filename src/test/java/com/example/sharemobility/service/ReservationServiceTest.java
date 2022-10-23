package com.example.sharemobility.service;

import com.example.sharemobility.domain.*;
import com.example.sharemobility.repository.CarRepository;
import com.example.sharemobility.repository.ReservationRepository;
import com.example.sharemobility.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationService reservationService;

    private Car car;
    private User user;
    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        reservationRepository.deleteAll();
        reservationRepository.flush();
        carRepository.deleteAll();
        carRepository.flush();
        userRepository.deleteAll();
        userRepository.flush();

        car = new IceCar();
        car.setLicensePlate("TP-444-N");
        car.setMake("Audi");
        car.setModel("A4");
        carRepository.save(car);

        user = new CarUser();
        user.setFirstname("Wesley");
        userRepository.save(user);
    }

    @Test
    void addReservationTest() {
        reservation = reservationService.addReservation(user.getId(), car.getId(), LocalDateTime.of(2022,10,23,10,00,00), LocalDateTime.of(2022,10,23,15,00,00));
        assertEquals(3L, reservation.getId());
    }
}
