package com.example.sharemobility.domain;

import com.example.sharemobility.Calculator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type" , discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IceCar.class, name = "ICE"),
        @JsonSubTypes.Type(value = BevCar.class, name = "BEV"),
        @JsonSubTypes.Type(value = FcevCar.class, name = "FCEV")
})
@Getter
@Setter
public abstract class Car implements Calculator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id", nullable = false)
    private Long id;



    @Column(nullable = false, length = 8, unique = true, updatable = false)
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "carowner_id")
    private CarOwner carOwner;

    private String make;
    private String model;
    private int mileage;
    private double hourlyRate;
    private double longitude;
    private double latitude;
    private String termsOfPickup;
    private String termsOfReturn;
    private int purchasePrice;
    private int amountOfYearsOwned;

    public Car() {
    }

    public double calculateTCO() {
        return this.getPurchasePrice() + (this.getMileage() / this.getAmountOfYearsOwned()) * calculateUsageCostsPerKilometer();
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", carOwner=" + carOwner +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                ", hourlyRate=" + hourlyRate +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", termsOfPickup='" + termsOfPickup + '\'' +
                ", termsOfReturn='" + termsOfReturn + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", amountOfYearsOwned=" + amountOfYearsOwned +
                '}';
    }
}
