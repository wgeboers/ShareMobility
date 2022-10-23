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
@DiscriminatorValue("BEV")
public class BevCar extends Car {
    private double rechargeTimeInMinutes;
    private double maxRangeInKilometers;
    private double maxCapacityOfBattery;

    public double calculateUsageCostsPerKilometer() {
        return (maxRangeInKilometers / maxCapacityOfBattery) * 0.65;
    }

}
