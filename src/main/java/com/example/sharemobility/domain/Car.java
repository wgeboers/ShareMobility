package com.example.sharemobility.domain;

import com.example.sharemobility.utilities.Calculator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

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
    private double usageCostsPerKm;
    private double totalCostOfOwnership;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Image> carImages;

    public Car() {
    }

    public double calculateTCO() {
        return (this.getPurchasePrice() + (double) this.getMileage() / this.getAmountOfYearsOwned() * usageCostsPerKm);
    }

    public void addImage(Image image) {
        carImages.add(image);
    }

    public void removeImage(Image image) {
        carImages.remove(image);
    }
}
