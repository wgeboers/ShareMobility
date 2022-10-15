package com.example.sharemobility.repository;

import com.example.sharemobility.domain.BevCar;
import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.FcevCar;
import com.example.sharemobility.domain.IceCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;
    private Car myCar1;
    private Car myCar2;
    private Car myCar3;

    @BeforeEach
    public void initEach() {
        carRepository.deleteAll();
        carRepository.flush();
        myCar1 = new IceCar();
        myCar2 = new BevCar();
        myCar3 = new FcevCar();
        myCar1.setLicensePlate("XX-XX-XX");
        myCar2.setLicensePlate("XX-XY-XX");
        myCar1.setMake("Volvo");
        myCar2.setMake("BMW");
        myCar1.setModel("XC-40");
        myCar2.setHourlyRate(40.0);
        carRepository.save(myCar1);
        carRepository.save(myCar2);
    }


    @Test
    void findByMakeTest() {
        List<Car> actualResult = carRepository.findByMake("Volvo");
        assertEquals(actualResult.size(), 1);
    }

    @Test
    void findByModelTest() {
        List<Car> actualResult = carRepository.findByModel("XC-40");
        assertEquals(actualResult.size(), 1);
    }

    @Test
    void findByHourlyRate() {
        List<Car> actualResult = carRepository.findByHourlyRate(40.0);
        assertEquals(actualResult.size(), 1);
    }

    @Test
    void saveCarTestAssertException() {
        Exception e = assertThrows(DataIntegrityViolationException.class, () -> {
            carRepository.save(myCar3);
        });

        String expectedMessage = "not-null property references";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
