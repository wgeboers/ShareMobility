package com.example.sharemobility.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //annotatie
@Getter
@Setter
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Reservation_id", nullable =  false, unique = true)
    private Long id;

    @ManyToOne//associatie unidirectioneel
    private Car car;

    @ManyToOne //associatie unidirectioneel
    private User user;

    private LocalDateTime startReservation;
    private LocalDateTime endReservation;

    public Reservation() {
    }

    public Reservation(Car car, User user, LocalDateTime startReservation, LocalDateTime endReservation) {
        this.car = car;
        this.user = user;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
    }
}
