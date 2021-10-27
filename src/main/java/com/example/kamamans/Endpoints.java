package com.example.kamamans;

import entities.Airport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Endpoints {
    @RequestMapping(path = "api/v1/GetAirport")
    @GetMapping
    public Airport[] getAirport() {
        Airport[] output = new Airport[2];
        output[0] = new Airport("Toronto", "567-11-11");
        output[1] = new Airport("Candyland", "delectable");
        return output;
    }

    @RequestMapping(path = "api/v1/GetAirport2")
    @GetMapping
    public Airport[] getAirport2() {
        Airport[] output = new Airport[2];
        output[0] = new Airport("6ix God", "567-11-11");
        output[1] = new Airport("Candyland", "delectable");
        return output;
    }
}
