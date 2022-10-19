package com.example.sharemobility.repository;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getByCarId(Long carId);
    List<Reservation> getByUserId(Long userId);
}
