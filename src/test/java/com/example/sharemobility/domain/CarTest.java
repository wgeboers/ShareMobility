package com.example.sharemobility.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarTest {

    //rewrite maybe....
//    @Test
//    void calculateCostsTestFunctionality() {
//        Car myCar = mock(BevCar.class);
//
//        assertNotNull(myCar);
//        when(myCar.calculateUsageCostsPerKilometer()).thenReturn(0.10);
//        when(myCar.calculateTCO()).thenReturn(10000.0);
//        assertEquals(myCar.calculateTCO(), 10000.0);
//        assertEquals(myCar.calculateUsageCostsPerKilometer(), 0.10);
//
//    }

    @Test
    void calculateCostTestActualCalculations() {
        IceCar myIceCar = new IceCar();
        double expectedValue = 100000 + (10000 / 5) * (0.10 * 2.20);

        myIceCar.setEfficiency(0.10);
        myIceCar.setFuelType(FuelType.LPG);
        assertEquals(myIceCar.calculateUsageCostsPerKilometer(), 0.10 * 1.05);
        myIceCar.setFuelType(FuelType.GASOLINE);
        assertEquals(myIceCar.calculateUsageCostsPerKilometer(), 0.10 * 2.20);

        myIceCar.setPurchasePrice(100000);
        myIceCar.setMileage(10000);
        myIceCar.setAmountOfYearsOwned(5);
        assertEquals(myIceCar.calculateTCO(), expectedValue);
    }

}
