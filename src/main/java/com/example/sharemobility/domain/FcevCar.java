package com.example.sharemobility.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("FCEV")
public class FcevCar extends Car {
    private int maxRangeInKilometers;
    private int amountOfRefuellingStationsInArea;

    public double calculateUsageCostsPerKilometer() {
        return 0.10;
    }
}
