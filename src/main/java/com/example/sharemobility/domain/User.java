package com.example.sharemobility.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarUser.class, name="CAR_USER"),
        @JsonSubTypes.Type(value = CarOwner.class, name="CAR_OWNER")
})
@Table(name = "user_table") // The name user is a keyword in SQL
@NoArgsConstructor
@Setter
@Getter

public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable =  false)
    private Long id;


    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String address;

    public User(String username, String password, String firstname, String lastname, String address) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }
}
