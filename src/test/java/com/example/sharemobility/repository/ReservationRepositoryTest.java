package com.example.sharemobility.repository;

import com.example.sharemobility.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ReservationRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
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

        reservation = new Reservation();
        reservation.setCar(car);
        reservation.setUser(user);
        reservation.setStartReservation(LocalDateTime.of(2022,10,23,21,00,00));
        reservation.setEndReservation(LocalDateTime.of(2022,10,23,23,00,00));
        reservationRepository.save(reservation);
    }

    @Test
    void findByCarIdTest() {
        List<Reservation> actualResult = reservationRepository.getByCarId(car.getId());
        assertEquals(actualResult.size(),1);
    }

    @Test
    void findByUserIdTest() {
        List<Reservation> actualResult = reservationRepository.getByUserId(user.getId());
        assertEquals(actualResult.size(),1);
    }
}
