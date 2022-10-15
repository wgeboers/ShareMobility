package com.example.sharemobility.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("BEV")
public class BevCar extends Car {
    private double rechargeTimeInMinutes;
    private int maxRangeInKilometers;
    private int maxCapacityOfBattery;

    public double calculateUsageCostsPerKilometer() {
        return (double) (this.maxRangeInKilometers / maxCapacityOfBattery) * 0.75;
    }

}
