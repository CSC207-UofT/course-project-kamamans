package com.example.kamamans;

import entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceLayer {

    private final DatabaseRepository airportRepository;

    @Autowired
    public ServiceLayer(DatabaseRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport[] getAirport() {
//        Airport[] output = new Airport[2];
//        output[0] = new Airport("Toronto", "6ix");
//        output[1] = new Airport("Paris", "tower");
//        return output;
        return  airportRepository.findAll().toArray(new Airport[0]);
    }

    public Airport[] getAirport2() {
        Airport[] output = new Airport[2];
        output[0] = new Airport("6ix God", "567-11-11");
        output[1] = new Airport("Candyland", "delectable");
        return output;
    }

    public void addNewAirport(Airport airport) {
        Optional<Airport> airportOptional = airportRepository
                .findAirportsByIataCode(airport.getIataCode());
        if (airportOptional.isPresent()) {
            throw new IllegalStateException("IATA Code Taken");
        }
        airportRepository.save(airport);
    }

    public void deleteAirport(String iataCode) {
        boolean exists = airportRepository.existsById(iataCode);
        if (!exists) {
            throw new IllegalStateException("Airport with IATA Code " + iataCode + " does not exist");
        }
        airportRepository.deleteById(iataCode);
    }
}
