package com.example.sharemobility.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CarOwner extends User{

    public CarOwner(String username, String password, String firstname, String lastname, String address) {
        super(username, password, firstname, lastname, address);
    }
}
