package com.example.kamamans;

import entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class APILayer {

    private final ServiceLayer service;

    @Autowired
    public APILayer(ServiceLayer service) {
        this.service = service;
    }


    @RequestMapping(path = "api/v1/GetAirport")
    @GetMapping
    public Airport[] getAirport() {
        return service.getAirport();
    }

    @RequestMapping(path = "api/v1/GetAirport2")
    @GetMapping
    public Airport[] getAirport2() {
        return service.getAirport2();
    }
}
