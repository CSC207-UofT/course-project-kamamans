package com.example.kamamans;

import entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatabaseRepository extends JpaRepository<Airport, String> {

    @Query("SELECT a FROM Airport a WHERE a.iataCode = ?1")
    Optional<Airport> findAirportsByIataCode(String iataCode);
}
