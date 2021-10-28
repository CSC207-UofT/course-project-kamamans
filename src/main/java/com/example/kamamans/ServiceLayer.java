package com.example.kamamans;

import entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
