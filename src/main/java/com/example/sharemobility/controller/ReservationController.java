package com.example.sharemobility.controller;

import com.example.sharemobility.controller.dto.ReservationDto;
import com.example.sharemobility.domain.Reservation;
import com.example.sharemobility.repository.ReservationRepository;
import com.example.sharemobility.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAll() {
        List<Reservation> found = new ArrayList<>(reservationRepository.findAll());

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> reserve(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.addReservation(reservationDto.getUserId(), reservationDto.getCarId(), reservationDto.getStartReservation(), reservationDto.getEndReservation());
        return null;
    }
}
