package com.example.kamamans;

import entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * APILayer is aggregated proxy of service offerings
 * Consists of functions that bridge client and application
 */

@RestController

public class APILayer {

    private final ServiceLayer service;

    @Autowired
    public APILayer(ServiceLayer service) {
        this.service = service;
    }


    @RequestMapping(path = "api/v1/getAirport")
    @GetMapping
    public Airport[] getAirport() {
        return service.getAirport();
    }

    @RequestMapping(path = "api/v1/getAirport2")
    @GetMapping
    public Airport[] getAirport2() {
        return service.getAirport2();
    }

    @RequestMapping(path = "api/v1/postAirport")
    @PostMapping
    public void newAirport(@RequestBody Airport toAdd) {
        System.out.println("post api layer");
        service.addNewAirport(toAdd);
    }

    @RequestMapping(path = "api/v1/deleteAirport/{iataCode}")
    @DeleteMapping(path = "{iataCode}")
    public void deleteAirport(@PathVariable("iataCode") String iataCode) {
        System.out.println("delete api layer");
        service.deleteAirport(iataCode);
    }

}
