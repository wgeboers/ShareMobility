package com.example.sharemobility.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarTest {

    private IceCar iceCar;
    private BevCar bevCar;
    private FcevCar fcevCar;
    private double expectedValueIce;
    private double expectedValueBev;
    private double expectedValueFcev;
    @BeforeEach
    void setUp() {
        iceCar = new IceCar(FuelType.GASOLINE, 0.10);
        iceCar.setPurchasePrice(100000);
        iceCar.setMileage(10000);
        iceCar.setAmountOfYearsOwned(5);
        iceCar.setEfficiency(0.10);
        expectedValueIce = 100000 + (10000 / 5) * (0.10 * 2.20);

        bevCar = new BevCar(100.0, 100, 1000);
        bevCar.setPurchasePrice(100000);
        bevCar.setMileage(10000);
        bevCar.setAmountOfYearsOwned(2);
        expectedValueBev = 100000 + (10000 / 2) * (100 / 1000) * 0.65;

        fcevCar = new FcevCar();
        fcevCar.setPurchasePrice(200000);
        fcevCar.setMileage(1000);
        fcevCar.setAmountOfYearsOwned(1);
        fcevCar.setUsageCostsPerKm(0.10);
        expectedValueFcev = 200000 + 1000 * 0.10;
    }
    @Test
    void calculateCostTest_Calculations_IceCar() {
        iceCar.setUsageCostsPerKm(iceCar.calculateUsageCostsPerKilometer());
        assertEquals(iceCar.calculateTCO(), expectedValueIce);
    }

    @Test
    void calculateCostTest_Calculations_BevCar() {
        bevCar.setUsageCostsPerKm(bevCar.calculateUsageCostsPerKilometer());
        assertEquals(iceCar.calculateTCO(), expectedValueBev);
    }

    @Test
    void calculateCostTest_Calculations_FcevCar() {
        assertEquals(fcevCar.calculateTCO(), expectedValueFcev);
    }
    @Test
    void calculateCostPerKm_LPG() {
        iceCar.setFuelType(FuelType.LPG);
        assertEquals(iceCar.calculateUsageCostsPerKilometer(), 0.10 * 1.05);
    }

    @Test
    void calculateCostPerKm_GASOLINE() {
        assertEquals(iceCar.calculateUsageCostsPerKilometer(), 0.10 * 2.20);
    }

    @Test
    void calculateCostPerKm_BEV() {
        assertEquals(bevCar.calculateUsageCostsPerKilometer(), 0.065);
    }

}
