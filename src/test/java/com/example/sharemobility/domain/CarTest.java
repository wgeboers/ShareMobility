package com.example.sharemobility.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CarTest {

    @Test
    void calculateCostsTests() {
        Car fcevCar = mock(FcevCar.class);
        assertNotNull(fcevCar);
        when(fcevCar.calculateUsageCostsPerKilometer()).thenReturn(0.10);
        when(fcevCar.calculateTCO()).thenReturn(10000.0);
        assertEquals(fcevCar.calculateTCO(), 10000.0);
        assertEquals(fcevCar.calculateUsageCostsPerKilometer(), 0.10);

    }
}
