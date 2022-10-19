package com.example.sharemobility.service;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.Reservation;
import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.ReservationRepository;
import com.example.sharemobility.repository.UserRepository;
import com.example.sharemobility.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public Reservation addReservation(Long userId, Long carId, LocalDateTime start, LocalDateTime end){
        User user = userRepository.findUserById(userId);
        Car car = carRepository.findCarById(carId);

        Reservation reservation = new Reservation(car, user, start, end);
        if (reservation != null) {
            reservationRepository.save(reservation);
        }

        return reservation;
    }
}
