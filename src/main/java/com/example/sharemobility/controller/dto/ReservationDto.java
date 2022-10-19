package com.example.sharemobility.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationDto {
    private Long userId;
    private Long carId;
    private LocalDateTime startReservation;
    private LocalDateTime endReservation;
}
