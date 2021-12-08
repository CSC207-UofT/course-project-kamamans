package controller;

import entities.*;
import usecases.AllPossibleFlights;

import java.util.*;

/**
 * A class responsible for booking a user a flight
 */

public class PlanFlight {
    private Flight selectedFlight;
    private final String user;

    public PlanFlight(String user) {
        this.user = user;
    }

    public static SearchResults EnterSearchRequirements(Calendar departureDate, Airport departure, Airport destination) {

        AllPossibleFlights output = new AllPossibleFlights(departureDate);
        return output.getRoutes(departure, destination);

    }

    public void selectFlight(Flight flightToBeSelected) {
        this.selectedFlight = flightToBeSelected;
    }

    public Flight getSelectedFlight() {
        return this.selectedFlight;
    }

    public String getUser() {
        return this.user;
    }

}