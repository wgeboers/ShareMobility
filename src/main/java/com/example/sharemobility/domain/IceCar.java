package com.example.sharemobility.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ICE")
public class IceCar extends Car {
    FuelType fuelType;
    private double efficiency;

    public double calculateUsageCostsPerKilometer() {
        if(fuelType == FuelType.LPG) {
            return (efficiency * 1.05);
        } else {
            return (efficiency * 2.20);
        }
    }
}
