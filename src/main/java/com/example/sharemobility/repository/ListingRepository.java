package com.example.sharemobility.repository;

import com.example.sharemobility.domain.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    //List voor Tenant.
    //List voor Car.
}
