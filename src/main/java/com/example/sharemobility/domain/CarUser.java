package com.example.sharemobility.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class CarUser extends User{
    private int bonusPoints;

    public CarUser(String username, String password, String firstname, String lastname, String address, int bonusPoints) {
       super(username, password, firstname, lastname, address);
       this.bonusPoints = bonusPoints;
    }
}
