package com.example.sharemobility.controller;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarRepository carRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {

        List<Car> found = new ArrayList<>(carRepository.findAll());

        if(found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Car> newCar(@RequestBody Car newCar) {
        try {
            Car car  = carRepository.save(newCar);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch(IllegalArgumentException e) {
            logger.error("Error during creation of: " + newCar, e);
            return ResponseEntity.badRequest().build();
        }
    }
}
