package com.example.sharemobility.service;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.CarOwner;
import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.CarRepository;
import com.example.sharemobility.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarRegistrationService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarRegistrationService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public boolean addCarRegistration(Long carId, Long ownerId) {
        Optional<Car> maybeCar = carRepository.findById(carId);
        Optional<User> maybeUser = userRepository.findById(ownerId);

        if(maybeUser.isPresent() && maybeCar.isPresent()) {
            CarOwner carOwner = (CarOwner) maybeUser.get();
            carOwner.addCar(maybeCar.get());
            Car car = maybeCar.get();
            car.setCarOwner(carOwner);
            carRepository.save(car);
            userRepository.save(carOwner);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeCarRegistration(Long carId, Long ownerId) {
        Optional<User> maybeUser = userRepository.findById(ownerId);
        Optional<Car> maybeCar = carRepository.findById(carId);

        if(maybeUser.isPresent() && maybeCar.isPresent()) {
            CarOwner carOwner = (CarOwner) maybeUser.get();
            carOwner.removeCar(maybeCar.get());
            userRepository.save(maybeUser.get());
            carRepository.delete(maybeCar.get());
            return true;
        } else {
            return false;
        }
    }

    public List<Car> getRegisteredCarForCarOwner(Long ownerId) {
        Optional<User> maybeCarOwner = userRepository.findById(ownerId);

        if(maybeCarOwner.isPresent()) {
            CarOwner carOwner = (CarOwner) maybeCarOwner.get();
            return carOwner.getCars();
        } else {
            return Collections.emptyList();
        }
    }
}
