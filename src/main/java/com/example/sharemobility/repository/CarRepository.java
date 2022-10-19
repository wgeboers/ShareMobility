package com.example.sharemobility.repository;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByMake(String make);
    List<Car> findByModel(String model);
    List<Car> findByHourlyRate(double hourlyRate);
}
