package com.example.sharemobility.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void checkGettersCarOwner() {
        CarOwner owner = new CarOwner("lotr", "letmein", "Bilbo" ,"Baggins", "The Shire");

        // Testing the getters
        assertEquals("lotr", owner.getUsername());
        assertEquals("letmein", owner.getPassword());
        assertEquals("Bilbo", owner.getFirstname());
        assertEquals("Baggins", owner.getLastname());
        assertEquals("The Shire", owner.getAddress());
    }

    @Test
    void checkGettersCarUser() {
        CarUser carUser = new CarUser("lotr", "letmein", "Bilbo" ,"Baggins", "The Shire", 0);

        // Testing the getters
        assertEquals("lotr", carUser.getUsername());
        assertEquals("letmein", carUser.getPassword());
        assertEquals("Bilbo", carUser.getFirstname());
        assertEquals("Baggins", carUser.getLastname());
        assertEquals("The Shire", carUser.getAddress());
        assertEquals(0, carUser.getBonusPoints());
    }

    @Test
    void checkCarAmountCarOwner() {
        CarOwner owner = new CarOwner("lotr", "letmein", "Bilbo" ,"Baggins", "The Shire");

        assertEquals(0, owner.getCars().size());

        owner.addCar(new IceCar());

        assertEquals(1, owner.getCars().size());

    }
}
