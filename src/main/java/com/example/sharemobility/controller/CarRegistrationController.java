package com.example.sharemobility.controller;

import com.example.sharemobility.controller.dto.CarRegistrationDto;
import com.example.sharemobility.domain.Car;
import com.example.sharemobility.service.CarRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carsByOwner")
public class CarRegistrationController {
    private final CarRegistrationService carRegistrationService;

    public CarRegistrationController(CarRegistrationService carRegistrationService) {
        this.carRegistrationService = carRegistrationService;
    }

    @GetMapping("/cars_owned/{ownerId}")
    public ResponseEntity<List<Car>> getById(@PathVariable Long ownerId) {
        List<Car> found = carRegistrationService.getRegisteredCarForCarOwner(ownerId);

        if(found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    ResponseEntity<CarRegistrationDto> registerCarWithOwner(@RequestBody CarRegistrationDto carRegistrationDto) {
        boolean canRegister = carRegistrationService.addCarRegistration(carRegistrationDto.getCarId(), carRegistrationDto.getCarOwnerId());

        if(canRegister) {
            return new ResponseEntity<>(carRegistrationDto, HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    ResponseEntity<CarRegistrationDto> remove(@RequestBody CarRegistrationDto carRegistrationDto) {
        boolean canRemove = carRegistrationService.removeCarRegistration(carRegistrationDto.getCarId(), carRegistrationDto.getCarOwnerId());

        if(canRemove) {
            return new ResponseEntity<>(carRegistrationDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
