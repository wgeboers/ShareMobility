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
import java.util.Optional;

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

    @GetMapping("/{carId}")
    public ResponseEntity<Optional<Car>> getById(@PathVariable Long carId) {
        Optional<Car> found = carRepository.findById(carId);

        if(found.isPresent()) {
            return ResponseEntity.ok(found);
        }

        return ResponseEntity.noContent().build();

    }

    @PostMapping
    public ResponseEntity<Car> newCar(@RequestBody Car newCar) {
        try {
            Car car  = carRepository.save(newCar);
            car.setUsageCostsPerKm(car.calculateUsageCostsPerKilometer());
            car.setTotalCostOfOwnership(car.calculateTCO());
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch(IllegalArgumentException e) {
            logger.error("Error during creation of: " + newCar, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        try {
            Optional<Car> optionalCar = carRepository.findById(id);

            if(optionalCar.isPresent()) {
                Car carOld = optionalCar.get();
                carOld.setMake(updatedCar.getMake());
                carOld.setModel(updatedCar.getModel());
                carOld.setMileage(updatedCar.getMileage());
                carOld.setHourlyRate(updatedCar.getHourlyRate());
                carOld.setLongitude(updatedCar.getLongitude());
                carOld.setLatitude(updatedCar.getLatitude());
                carOld.setTermsOfPickup(updatedCar.getTermsOfPickup());
                carOld.setTermsOfReturn(updatedCar.getTermsOfReturn());
                carOld.setPurchasePrice(updatedCar.getPurchasePrice());
                carOld.setAmountOfYearsOwned(updatedCar.getAmountOfYearsOwned());
                carOld.setUsageCostsPerKm(carOld.calculateUsageCostsPerKilometer());
                carOld.setTotalCostOfOwnership(carOld.calculateTCO());

                return ResponseEntity.ok(carRepository.save(carOld));
            } else {
                return ResponseEntity.notFound().build();
            }


        } catch(IllegalArgumentException e) {
            logger.error("Error during editing of: " + updatedCar, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        if((!carRepository.existsById(id))) {
            return ResponseEntity.notFound().build();
        } else {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
