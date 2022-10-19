package com.example.sharemobility.repository;

import com.example.sharemobility.domain.Car;
import com.example.sharemobility.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
