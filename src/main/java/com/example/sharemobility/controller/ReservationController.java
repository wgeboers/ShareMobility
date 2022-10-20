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
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reservation>> getById(@PathVariable Long id) {
        Optional<Reservation> found = reservationRepository.findById(id);

        if(found.isPresent()) {
            return ResponseEntity.ok(found);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byCar/{id}")
    public ResponseEntity<List<Reservation>> getByCarId(@PathVariable Long id) {
        List<Reservation> found = new ArrayList<>(reservationRepository.getByCarId(id));

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<Reservation>> getByUserId(@PathVariable Long id) {
        List<Reservation> found = new ArrayList<>(reservationRepository.getByUserId(id));

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Reservation> reserve(@RequestBody ReservationDto reservationDto) {
        try{
            Reservation reservation = reservationService.addReservation(reservationDto.getUserId(), reservationDto.getCarId(), reservationDto.getStartReservation(), reservationDto.getEndReservation());
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);

            if (optionalReservation.isPresent()) {
                Reservation reservationOld = optionalReservation.get();
                reservationOld.setStartReservation(reservationDto.getStartReservation());
                reservationOld.setEndReservation(reservationDto.getEndReservation());

                return ResponseEntity.ok(reservationRepository.save(reservationOld));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        if((!reservationRepository.existsById(id))) {
           return ResponseEntity.notFound().build();
        } else {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
