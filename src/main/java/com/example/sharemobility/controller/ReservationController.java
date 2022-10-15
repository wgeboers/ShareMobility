package com.example.sharemobility.controller;

import com.example.sharemobility.controller.dto.ReservationDto;
import com.example.sharemobility.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    ResponseEntity<ReservationDto> reservation(@RequestBody ReservationDto reservationDto) {
        boolean canReserve = reservationService.addReservation(reservationDto.getUserId(), reservationDto.getCarId());

        if (canReserve) {
            return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
