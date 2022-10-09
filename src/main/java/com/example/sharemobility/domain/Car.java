package com.example.sharemobility.domain;

import com.example.sharemobility.Calculator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Car implements Calculator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 8)
    private String licensePlate;
    private String make;
    private String model;
    private int kilometers;
    private float hourlyRate;
    private String pickupTerms;
    private String returnTerms;
    private float longitude;
    private float latitude;

    public Car() {
    }

    public boolean requestRent(LocalDateTime startTime, LocalDateTime endTime) {
        return true;
    }

    public boolean isAccepted(LocalDateTime startTime, LocalDateTime endTime) {
        return true;
    }

    public float calculateTCO() {
        return 0.0f;
    }

    public float calculateUsageCostsPerKilometer() {
        return 0.0f;
    }
}
