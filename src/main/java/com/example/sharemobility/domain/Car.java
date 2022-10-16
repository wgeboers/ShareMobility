package com.example.sharemobility.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carowner_id", nullable = false)
    private CarOwner carOwner;
    private String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
