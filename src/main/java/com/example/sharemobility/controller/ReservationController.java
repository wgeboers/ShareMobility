package com.example.sharemobility.controller;

import com.example.sharemobility.controller.dto.ReservationDto;
import com.example.sharemobility.domain.Reservation;
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
    ResponseEntity<ReservationDto> reserve(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.addReservation(reservationDto.getUserId(), reservationDto.getCarId(), reservationDto.getStartReservation(), reservationDto.getEndReservation());
        return null;
    }
}
