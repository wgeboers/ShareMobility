package com.example.sharemobility.service;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.Reservation;
import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.UserRepository;
import com.example.sharemobility.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public ReservationService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public boolean addReservation(Long userId, Long carId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        Optional<Car> maybeCar = carRepository.findById(carId);

        if (maybeUser.isPresent() && maybeCar.isPresent()) {
            //new Reservation(maybeUser, maybeCar);
            return true;
        } else {
            return false;
        }
    }
}
