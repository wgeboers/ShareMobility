package com.example.sharemobility.domain;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST})
    @JoinColumn(name = "car_id")
    private Set<Car> cars;

    public Reservation(User user, Car car) {
        users.add(user);
        cars.add(car);
    }

}
