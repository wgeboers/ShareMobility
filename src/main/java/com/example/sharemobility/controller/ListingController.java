package com.example.sharemobility.controller;

import com.example.sharemobility.domain.Listing;
import com.example.sharemobility.repository.ListingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/listing")
public class ListingController {
    private final ListingRepository listingRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ListingController(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    //post komen voor het maken van een huur actie tussen user en Car
    //get komen voor het opvragen van de gehuurde acties van een user
    //get komen voor het opvragen van de gehuurde dagen en tijden van een auto. dit om ervoor te zorgen dat de gebruiker niet dubbel boekt.

    //Listing hernoemen naar Booking?????
    }
}
