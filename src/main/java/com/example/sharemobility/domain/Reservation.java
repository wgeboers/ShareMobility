package com.example.sharemobility.domain;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
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
