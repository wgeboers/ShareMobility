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
    @Column(name = "id", nullable = false)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "carowner_id", nullable = false)
    //private CarOwner carOwner;

    @Column(nullable = false, length = 8, unique = true, updatable = false)
    private String licensePlate;
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

}
