package com.example.sharemobility.service;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.Reservation;
import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.CarRepository;
import com.example.sharemobility.repository.ReservationRepository;
import com.example.sharemobility.repository.UserRepository;
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
        Optional<User> user = userRepository.findById(userId);
        Optional<Car> car = carRepository.findById(carId);

        Reservation reservation = new Reservation(car.get(), user.get(), start, end);
        if (reservation != null) {
            reservationRepository.save(reservation);
        }

        return reservation;
    }
}
