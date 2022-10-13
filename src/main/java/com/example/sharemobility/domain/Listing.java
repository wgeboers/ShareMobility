package com.example.sharemobility.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //annotatie
@Getter
@Setter
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime startDateRent;
    private LocalDateTime endDateRent;
    private int distance;
    private int accelerationStart;
    private int accelerationEnd;

    public Listing() {

    }

    public int calculateBonuspoints(){
        //waarop gebaseerd?
        return 0;
    }
}
